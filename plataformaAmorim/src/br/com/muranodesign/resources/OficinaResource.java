package br.com.muranodesign.resources;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.CiclosService;
import br.com.muranodesign.business.CoresService;
import br.com.muranodesign.business.OficinaService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.model.Oficina;


@Path("Oficina")
public class OficinaResource {
	
	private Logger logger = Logger.getLogger(OficinaResource.class.getName());
	

	/**
	 * Deletar, alterar e criar oficina
	 * @param action
	 * @param id
	 * @param nome
	 * @return id
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("cor") int cor,
			@FormParam("ciclo") int ciclo,
			@FormParam("anoLetivo") int anoLetivo,
			@FormParam("periodo") int periodo,
			@FormParam("nome") String nome){
		
		Oficina resultado = new Oficina();
		
		if(action.equals("delete")){
			resultado = new OficinaService().deletarOficina(new OficinaService().listarkey(id).get(0));
		}
		else if(action.equals("create")){
			Oficina oficina = new Oficina();
			
			oficina.setNome(nome);
			oficina.setCor(new CoresService().listarkey(cor).get(0));
			oficina.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
			oficina.setCiclo(new CiclosService().listarkey(ciclo).get(0));
			oficina.setPeriodo(new PeriodoService().listarkey(periodo).get(0));
			resultado = new OficinaService().criarOficina(oficina);
			
		}else if(action.equals("update")){
			Oficina oficina = new OficinaService().listarkey(id).get(0);
			
			oficina.setNome(nome);
			oficina.setCor(new CoresService().listarkey(cor).get(0));
			oficina.setAnoLetivo(new AnoLetivoService().listarkey(anoLetivo).get(0));
			oficina.setCiclo(new CiclosService().listarkey(ciclo).get(0));
			oficina.setPeriodo(new PeriodoService().listarkey(periodo).get(0));
			resultado = new OficinaService().atualizarOficina(oficina);
		}
		
		return Integer.toString(resultado.getIdoficina());
	}
	
	/**
	 * Listar todas as oficinas
	 * @return list
	 */
	@GET
	@Produces("application/json")
	public List<Oficina> getOficina() {
		logger.debug("Listar Oficina ...");
		List<Oficina> resultado;
		 resultado = new OficinaService().listarTodos();
		 logger.debug("QTD Oficina : " +  resultado.size());
		return resultado;
	}

}
