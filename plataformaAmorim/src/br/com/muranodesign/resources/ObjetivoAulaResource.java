package br.com.muranodesign.resources;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.ObjetivoAulaService;
import br.com.muranodesign.business.OficinaProfessorService;
import br.com.muranodesign.business.RoteiroAulaService;
import br.com.muranodesign.model.ObjetivoAula;
import br.com.muranodesign.model.RoteiroAula;

@Path("ObjetivoAula")
public class ObjetivoAulaResource {
	
	private Logger logger = Logger.getLogger(ObjetivoAulaResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("status") int status,
			@FormParam("roteiro") int roteiro,
			@FormParam("objetivo") String objetivo){
		
		ObjetivoAula resultado = new ObjetivoAula();
		
		if(action.equals("delete")){
			resultado = new  ObjetivoAulaService().deletarObjetivoAula(new ObjetivoAulaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			ObjetivoAula obj = new ObjetivoAula();
			obj.setObjetivo(objetivo);
			obj.setStatus(status);
			obj.setRoteiro(new RoteiroAulaService().listarkey(roteiro).get(0));

			resultado = new ObjetivoAulaService().criarObjetivoAula(obj);
			
		}else if(action.equals("update")){
			ObjetivoAula obj = new ObjetivoAulaService().listarkey(id).get(0);
			obj.setObjetivo(objetivo);
			obj.setStatus(status);
			obj.setRoteiro(new RoteiroAulaService().listarkey(roteiro).get(0));
			
			resultado = new ObjetivoAulaService().atualizarObjetivoAula(obj);
		}
		
		return Integer.toString(resultado.getIdobjetivo_aula());
	}

	
	@GET
	@Produces("application/json")
	public List<ObjetivoAula> getObjetivoAula() {
		logger.debug("Listar ObjetivoAula ...");
		List<ObjetivoAula> resultado;
		 resultado = new ObjetivoAulaService().listarTodos();
		 logger.debug("QTD ObjetivoAula : " +  resultado.size());
		return resultado;
	}

	@Path("ListarPorRoteiro/{id}")
	@GET
	@Produces("application/json")
	public List<ObjetivoAula> getRoteiro(@PathParam("id") int id){
		
		return new ObjetivoAulaService().listarPorRoteiro(id);
	}
	
	@Path("ListarPorRoteiroHash/{id}")
	@GET
	@Produces("application/json")
	public List<Hashtable<String, String>> getListarPorRoteiroHash(@PathParam("id") int id){
		List<Hashtable<String, String>> list = new ArrayList<Hashtable<String,String>>();
		
		List<ObjetivoAula> objetivos = new ObjetivoAulaService().listarPorRoteiro(id);
		
		for (ObjetivoAula objetivoAula : objetivos) {
			Hashtable<String, String> hash = new Hashtable<String, String>();
			
			hash.put("idRoteiro_aula", Integer.toString(objetivoAula.getRoteiro().getIdroteiro_aula()));
			hash.put("descricao", objetivoAula.getRoteiro().getDescricao());
			hash.put("roteiro", objetivoAula.getRoteiro().getRoteiro());
			
			hash.put("idObjetivo_aula", Integer.toString(objetivoAula.getIdobjetivo_aula()));
			hash.put("objetivo", objetivoAula.getObjetivo());
			
			list.add(hash);
			
		}
		
		
		return list;		
		
	}
	
	
	@Path("ListarPorRoteiroLazy/{id}")
	@GET
	@Produces("application/json")
	public List<ObjetivoAula> getListarPorRoteiroLazy(@PathParam("id") int id){
		
		return new ObjetivoAulaService().listarPorRoteiroLazy(id);
	}
	
	
	@Path("ClonarObjetivo/")
	@POST
	@Produces("application/json")
	public String ClonarObjetivo(@FormParam("idOficinaProfessor") int idOficinaProfessor,@FormParam("idRoteiroAula") int idRoteiroAula){
		
		RoteiroAula resultado = new RoteiroAula();
		RoteiroAula roteiro = new RoteiroAulaService().listarkey(idRoteiroAula).get(0);
		List<ObjetivoAula> objs = new ObjetivoAulaService().listarPorRoteiro(idRoteiroAula);
		
		
		RoteiroAula roteiroAula = new RoteiroAula();
		
		roteiroAula.setDescricao(roteiro.getDescricao());
		roteiroAula.setRoteiro(roteiro.getRoteiro());
		roteiroAula.setOficinaprofessor(new OficinaProfessorService().listarkey(idOficinaProfessor).get(0));
		
		resultado = new RoteiroAulaService().criarRoteiroAula(roteiroAula);
		
		
		for (ObjetivoAula objetivoAula : objs) {
			ObjetivoAula obj = new ObjetivoAula();
			
			obj.setObjetivo(objetivoAula.getObjetivo());
			obj.setStatus(objetivoAula.getStatus());
			obj.setRoteiro(resultado);
			
			new ObjetivoAulaService().criarObjetivoAula(obj);
		}
		
		
		return "ok";
	}
	
}
