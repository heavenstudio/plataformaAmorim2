package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.FichaInscricaoService;
import br.com.muranodesign.business.RoteiroService;
import br.com.muranodesign.model.FichaInscricao;

@Path("FichaFinalizacao")
public class FichaInscricaoResource {
	
	private Logger logger = Logger.getLogger(FichaInscricao.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("local") String local,
			@FormParam("ano_letivo") int ano_letivo,
			@FormParam("roteiro") int roteiro
			){
		
		FichaInscricao ficha = new FichaInscricao();
		logger.info("eventoAction ...");
		FichaInscricao resultado = null;
		
		if (action.equals("create")) {
			ficha.setLocal(local);
			ficha.setAnoLetivo(new AnoLetivoService().listarkey(ano_letivo).get(0));
			ficha.setRoteiro(new RoteiroService().listarkey(roteiro).get(0));
			
			resultado = new FichaInscricaoService().criarFichaInscricao(ficha);
			
		}else if(action.equals("update")){
			ficha.setIdfichainscricao(id);
			ficha.setLocal(local);
			ficha.setAnoLetivo(new AnoLetivoService().listarkey(ano_letivo).get(0));
			ficha.setRoteiro(new RoteiroService().listarkey(roteiro).get(0));
			
		} else{
			return "0";
		}
		
		
		
		return Integer.toString(resultado.getIdfichainscricao());
	}
	
	
	@GET
	@Produces("application/json")
	public List<FichaInscricao> getFichaInscricao() {
		logger.info("Listar AnoEstudo ...");
		List<FichaInscricao> resultado;
		resultado = new FichaInscricaoService().listarTodos();
		logger.info("QTD Ficha Inscricao : " + resultado.size());
		return resultado;
	}
	
	
	@Path("{id}")
	@GET
	@Produces("application/json")
	public List<FichaInscricao> getFichaInscricao(@PathParam("id") int id) {
		logger.info("Lista Ficha Inscricao  por id de roteiro" + id);
		List<FichaInscricao> resultado;
		resultado = new FichaInscricaoService().listarkey(id);
		//FichaInscricao evento = resultado.get(0);

		return resultado;

	}
	

}
