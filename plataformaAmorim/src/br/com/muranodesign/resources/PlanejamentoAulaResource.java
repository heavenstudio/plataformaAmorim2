package br.com.muranodesign.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.ObjetivoAulaService;
import br.com.muranodesign.business.PlanejamentoAulaService;
import br.com.muranodesign.business.PlanoAulaService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.model.PlanejamentoAula;

@Path("PlanejamentoAula")
public class PlanejamentoAulaResource {
	private Logger logger = Logger.getLogger(PlanejamentoAulaResource.class.getName());
	
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("idPlanoAula") int idPlanoAula,
			@FormParam("idObjetivoAula") int idObjetivoAula,
			@FormParam("status") String status,
			@FormParam("idProfessor") int idProfessor) throws ParseException{
		
		//Date data = null;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		String k = new SimpleDateFormat("dd/MM/yyyy").format(new Date()).toString();  
		Date date = (Date)formatter.parse(k);
		
		PlanejamentoAula resultado = new PlanejamentoAula();
		
		if(action.equals("delete")){
			resultado = new PlanejamentoAulaService().deletarPlanejamentoAula(new PlanejamentoAulaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			PlanejamentoAula planejamento = new PlanejamentoAula();
			planejamento.setPlanoAula(new PlanoAulaService().listarkey(idPlanoAula).get(0));
			planejamento.setObjetivoAula(new ObjetivoAulaService().listarkey(idObjetivoAula).get(0));
			planejamento.setStatus(status);
			planejamento.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			planejamento.setData(date);
			
			resultado = new PlanejamentoAulaService().criarPlanejamentoAula(planejamento);
			
		}else if(action.equals("update")){
			PlanejamentoAula planejamento = new  PlanejamentoAulaService().listarkey(id).get(0);
			planejamento.setPlanoAula(new PlanoAulaService().listarkey(idPlanoAula).get(0));
			planejamento.setObjetivoAula(new ObjetivoAulaService().listarkey(idObjetivoAula).get(0));
			planejamento.setStatus(status);
			planejamento.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
			planejamento.setData(date);
			
			
			resultado = new PlanejamentoAulaService().atualizarPlanejamentoAula(planejamento);
		}
		
		return Integer.toString(resultado.getIdplanejamento_aula());
	}
	
	
	@Path("listarProfessor/{idProfessor}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getlistarProfessor(@PathParam("idProfessor") int idProfessor){
		logger.debug("Listar PlanejamentoAula ...");
		List<PlanejamentoAula> resultado;
		 resultado = new PlanejamentoAulaService().listarProfessor(idProfessor);
		 logger.debug("QTD PlanejamentoAula: " +  resultado.size());
		return resultado;
	}
	
	@Path("listarProfessorObjetivoAula/{idProfessor}/{idObjetivoAula}")
	@GET
	@Produces("application/json")
	public String getlistarProfessorObjetivoAula(@PathParam("idProfessor") int idProfessor,@PathParam("idObjetivoAula") int idObjetivoAula){
		List<PlanejamentoAula> planejamnto = new PlanejamentoAulaService().listarProfessorObjetivoAula(idProfessor, idObjetivoAula);
		String status;
		if(!planejamnto.isEmpty()){
			status = planejamnto.get(0).getStatus();
		}else{
			status = "0";
		}
		
		return status;
	}
	
	
	@Path("listarPlanoAula/{idPlanoAula}")
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getlistarPlanoAula(@PathParam("idPlanoAula") int idPlanoAula){
		logger.debug("Listar PlanejamentoAula ...");
		List<PlanejamentoAula> resultado;
		resultado = new PlanejamentoAulaService().listarPlanoAula(idPlanoAula);
		 logger.debug("QTD PlanejamentoAula: " +  resultado.size());
		return resultado;
	}
	
	
	@GET
	@Produces("application/json")
	public List<PlanejamentoAula> getPlanejamentoAula() {
		logger.debug("Listar PlanejamentoAula ...");
		List<PlanejamentoAula> resultado;
		 resultado = new PlanejamentoAulaService().listarTodos();
		 logger.debug("QTD OficinaProfessor : " +  resultado.size());
		return resultado;
	}
	
	

}
