package br.com.muranodesign.resources;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.BlogService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.Blog;
import br.com.muranodesign.util.StringUtil;
import br.com.muranodesign.util.Upload;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * 
 * @author Kevyn
 *
 */
@Path("Blog")
public class BlogResource {
	
	private Logger logger = Logger.getLogger(BlogResource.class.getName());
	
	
	@Path("Teste")
	@GET
	@Produces("text/plain")
	public String teste() throws UnsupportedEncodingException{
		RsaJsonWebKey rsaJsonWebKey;
		try {
			String secret = "O8zBTpnHhl\ngJuTimlEhN\niq7ZWhitG0";
			HmacKey key = new HmacKey(secret.getBytes("UTF-8"));
			JwtClaims claims = new JwtClaims();
			claims.setIssuer("Issuer");  // who creates the token and signs it
		    claims.setAudience("Audience"); // to whom the token is intended to be sent
		    claims.setExpirationTimeMinutesInTheFuture(10); // time when the token will expire (10 minutes from now)
		    claims.setGeneratedJwtId(); // a unique identifier for the token
		    claims.setIssuedAtToNow();  // when the token was issued/created (now)
		    claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
		    claims.setSubject("subject"); // the subject/principal is whom the token is about
		    claims.setClaim("email","mail@example.com");
			JsonWebSignature jws = new JsonWebSignature();
			jws.setPayload(claims.toJson());
			jws.setKey(key);
			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
			String jwt = jws.getCompactSerialization();
			System.out.println("JWT: " + jwt);
			
			JwtConsumer jwtConsumer = new JwtConsumerBuilder()
            .setRequireExpirationTime() // the JWT must have an expiration time
            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
            .setRequireSubject() // the JWT must have a subject claim
            .setExpectedIssuer("Issuer") // whom the JWT needs to have been issued by
            .setExpectedAudience("Audience") // to whom the JWT is intended for
            .setVerificationKey(key) // verify the signature with the public key
            .build(); // create the JwtConsumer instance

			try
			{
				//  Validate the JWT and process it to the Claims
				JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
				System.out.println("JWT validation succeeded! " + jwtClaims);
			}
			catch (InvalidJwtException e)
			{
				// InvalidJwtException will be thrown, if the JWT failed processing or validation in anyway.
				// Hopefully with meaningful explanations(s) about what went wrong.
				System.out.println("Invalid JWT! " + e);
			}
			
		} catch (JoseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Criar, deltera e alterar blog
	 * @param action
	 * @param id
	 * @param titulo
	 * @param Descricao
	 * @return
	 * @throws ParseException 
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("autor") int autor,
			@FormParam("oficina") int oficina,
			@FormParam("agrupamento") int agrupamento,
			@FormParam("titulo") String titulo,
			@FormParam("Descricao") String Descricao) throws ParseException{
		
		
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String k = formatter.format(new Date()).toString();  
		Date date = (Date)formatter.parse(k);
		
		
		Blog resultado = new Blog();
		
		
		if(action.equals("delete")){
			resultado = new BlogService().deletarBlog(new BlogService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			Blog blog = new Blog();
			
			blog.setDescricao(Descricao);
			blog.setTitulo(titulo);
			blog.setData(date);
			if (agrupamento != 0)
				blog.setAgrupamento(new AgrupamentoService().listarkey(agrupamento).get(0));
			blog.setAutor(new ProfessorFuncionarioService().listarkey(autor).get(0));
			if (oficina != 0)
				blog.setOficina(new OficinaService().listarkey(oficina).get(0));
			else
				blog.setOficina(null);
			
			resultado = new BlogService().criarBlog(blog);
			
		}else if(action.equals("update")){
			Blog blog = new BlogService().listarkey(id).get(0);
			
			blog.setDescricao(Descricao);
			blog.setTitulo(titulo);
			resultado = new BlogService().atualizarBloga(blog);
		}
		
		return Integer.toString(resultado.getIdblog());
	}
	
	
	
	@POST
	@Path("upload/Blog/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Blog eventoAction(
			@PathParam("id") String strId,
			@FormDataParam("fotoAluno") InputStream uploadedInputStream,
			@FormDataParam("fotoAluno") FormDataContentDisposition fileDetail
			){
		
		int id = Integer.parseInt(strId);
		Blog blog = new BlogService().listarkey(id).get(0);
		
		// TODO: Criar uma configiracao para o caminho
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		//String uploadedFileLocation = "C:/Users/murano/Desktop/" + arquivo;
		//String uploadedFileLocationMed = "C:/Users/murano/Desktop/Med/" + arquivo;
		//String uploadedFileLocationMin = "C:/Users/murano/Desktop/Min/" + arquivo;
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;
		String uploadedFileLocationMed = "/home/tomcat/webapps/files/Med/" + arquivo;
		String uploadedFileLocationMin = "/home/tomcat/webapps/files/Min/" + arquivo;

		Upload upload = new Upload();
		// save it
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);
		
		File fileToResize = new File(uploadedFileLocation);
		
		try {
			upload.resizeAndWriteToFile(fileToResize, uploadedFileLocationMed, 350);
			upload.resizeAndWriteToFile(fileToResize, uploadedFileLocationMin, 64);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String anexo = arquivo;
		if (blog.getImagem() != null)
		{
			upload.deleteFile("/home/tomcat/webapps/files/" + blog.getImagem());
			upload.deleteFile("/home/tomcat/webapps/files/Med" + blog.getImagem());
			upload.deleteFile("/home/tomcat/webapps/files/Min" + blog.getImagem());
		}
		blog.setImagem(anexo);
		Blog result = new BlogService().atualizarBloga(blog);
		
		return result;
	}
	
	@GET
	@Path("DeletarImagem/{id}")
	@Produces("text/plain")
	public String deletarImagem(@PathParam("id") int id){
		Blog blog = new BlogService().listarkey(id).get(0);
		
		if (blog.getImagem() != null)
		{
			Upload upload = new Upload();
			upload.deleteFile("/home/tomcat/webapps/files/" + blog.getImagem());
			upload.deleteFile("/home/tomcat/webapps/files/Med" + blog.getImagem());
			upload.deleteFile("/home/tomcat/webapps/files/Min" + blog.getImagem());
			blog.setImagem(null);
			new BlogService().atualizarBloga(blog);
		}
		
		return Integer.toString(id);
	}
	
	@GET
	@Path("Imagem/{id}")
	@Produces("text/plain")
	public String getImagem(@PathParam("id") int id){
		
		Blog blog = new BlogService().listarkey(id).get(0);
		
		if(blog.getImagem().isEmpty())
			return "";
		
		return "http://177.55.99.90/files/" + blog.getImagem();
	}
	
	@GET
	@Path("ImagemMed/{id}")
	@Produces("text/plain")
	public String getImagemMed(@PathParam("id") int id){
		
		Blog blog = new BlogService().listarkey(id).get(0);
		
		if(blog.getImagem().isEmpty())
			return "";
		
		return "http://177.55.99.90/files/Med/" + blog.getImagem();
	}
	
	@GET
	@Path("ImagemMin/{id}")
	@Produces("text/plain")
	public String getImagemMin(@PathParam("id") int id){
		
		Blog blog = new BlogService().listarkey(id).get(0);
		
		if(blog.getImagem().isEmpty())
			return "";
		
		return "http://177.55.99.90/files/Min/" + blog.getImagem();
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<Blog> getBlog() {
		logger.debug("Listar Blog ...");
		List<Blog> resultado;
		 resultado = new BlogService().listarTodos();
		 logger.debug("QTD Oficina : " +  resultado.size());
		return resultado;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Path("BlogOficina/{id}")
	@GET
	@Produces("application/json")
	public List<Blog> getBlogOficina(@PathParam("id") int id) {
		logger.debug("Lista Blog por Oficina " + id);
		List<Blog> obj = new BlogService().listarOficina(id);
		return obj;
	}
	
	@Path("BlogProfessor/{id}")
	@GET
	@Produces("application/json")
	public List<Blog> getBlogProfessor(@PathParam("id") int id){
		logger.debug("Lista Blog por Professor " + id);
		return new BlogService().listarAutor(id);
	}
	
	@Path("BlogTutoria/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<Blog> listarTutoria (@PathParam("idProfessor") int idProfessor){
		return new BlogService().listarTutoria(idProfessor);
	}
	
	@Path("BlogAgrupamento/{idAgrupamento}")
	@GET
	@Produces("application/json")
	public List<Blog> getBlogAgrupamento(@PathParam("idAgrupamento") int idAgrupamento){
		logger.debug("Lista Blog por Agrupamento " + idAgrupamento);
		
		return new BlogService().listarAgrupamento(idAgrupamento);
	}

	/**
	 * 
	 * @param strId
	 * @param uploadedInputStream
	 * @param fileDetail
	 * @return
	 */
	@POST
	@Path("upload/imagem/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Blog upload(

	@PathParam("id") String strId,
			@FormDataParam("fotoAluno") InputStream uploadedInputStream,
			@FormDataParam("fotoAluno") FormDataContentDisposition fileDetail

	) {
		
		Blog blog = new Blog();
		int id = Integer.parseInt(strId);
		List<Blog> rsblog;
		rsblog = new BlogService().listarkey(id);
		blog = rsblog.get(0);

		// TODO: Criar uma configiracao para o caminho
		StringUtil stringUtil = new StringUtil();
		String arquivo = stringUtil.geraNomeAleatorio(fileDetail.getFileName(),
				50);
		String uploadedFileLocation = "/home/tomcat/webapps/files/" + arquivo;

		Upload upload = new Upload();
		
		upload.writeToFile(uploadedInputStream, uploadedFileLocation);

		String anexo = "http://177.55.99.90/files/" + arquivo;

		logger.info("anexo" + anexo);

		blog.setImagem(anexo);
		
		Blog resultado = new BlogService().atualizarBloga(blog); 

		return resultado;

	}
	
	@Path("ListarOficinaProfessor/{idOficina}/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<Blog> listarOficinaProfessor(@PathParam("idOficina") int idOficina, @PathParam("idProfessor") int idProfessor){
		return new BlogService().listarOficinaProfessor(idOficina, idProfessor);
	}
	
}
