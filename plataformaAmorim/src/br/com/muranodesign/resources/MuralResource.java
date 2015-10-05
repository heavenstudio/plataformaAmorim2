package br.com.muranodesign.resources;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import br.com.muranodesign.business.AgrupamentoService;
import br.com.muranodesign.business.AlunoAgrupamentoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoEstudoService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.MuralService;
import br.com.muranodesign.business.ProfessorFuncionarioService;
import br.com.muranodesign.business.RotinaService;
import br.com.muranodesign.business.TutoriaService;
import br.com.muranodesign.model.Agrupamento;
import br.com.muranodesign.model.AlunoAgrupamento;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Mural;
import br.com.muranodesign.model.ProfessorFuncionario;
import br.com.muranodesign.model.Rotina;
import br.com.muranodesign.model.Tutoria;


@Path("Mural")
public class MuralResource {
	
	private Logger logger = Logger.getLogger(MuralResource.class.getName());
	
	/**
	 * create, update e delete de mural
	 * @param action
	 * @param id
	 * @param mensagem
	 * @param idProfessor
	 * @param idAgrupamento
	 * @param idOficina
	 * @param tutor
	 * @param ano
	 * @param idAluno
	 * @return
	 * @throws ParseException
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			@FormParam("action") String action,
			@FormParam("id") int id,
			@FormParam("mensagem") String mensagem,
			@FormParam("idProfessor") int idProfessor,
			@FormParam("idAgrupamento") int idAgrupamento,
			@FormParam("idOficina") int idOficina,
			@FormParam("tutor") int tutor,
			@FormParam("ano") String ano,
			//@FormParam("data") String data,
			@FormParam("idAluno") int idAluno) throws ParseException{
		
		
		if(action.equals("delete")){
			
			new MuralService().deletarMural(new MuralService().listarkey(id).get(0));
			return "ok";
			
		}else if(action.equals("create")){
			
			Date dataT = new Date();
			
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			String dataS = formatter.format(dataT);
			
			Date data = (Date) formatter.parse(dataS);
		
			
			Mural mural = new Mural();
			
			
			ProfessorFuncionario verificarCoordena = new ProfessorFuncionarioService().listarkey(idProfessor).get(0);
			if(verificarCoordena.getPerfil() == 26){
				
				  String [] arrayAnos = ano.split(";");
				  List<AlunoVariavel> listAluno = new ArrayList<AlunoVariavel>();
				  
				  
				  for(int j = 0; j < arrayAnos.length; j++){
					  listAluno.addAll(new AlunoVariavelService().listaAnoEstudo(new AnoEstudoService().listarkey(Integer.parseInt(arrayAnos[j])).get(0)));
					  
					  
				  }
				  
				  for(int k = 0; k < listAluno.size(); k++){
					mural.setMensagem(mensagem);
					mural.setAluno(listAluno.get(k));
					mural.setData(data);
					//mural.setData(new Date());
					
					mural.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
					
					//new MuralService().criarMural(mural);
					new MuralService().atualizarMural(mural);
				  }
				
				  return "ok";
				
			}else {
			
			if(tutor == 1  && idOficina == 0){
				Tutoria profTutor = new TutoriaService().listarProfessorId(idProfessor).get(0);
				
				List<Grupo> grupos = new GrupoService().listarTutor(profTutor.getIdtutoria());
				
				List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
				
				for (Grupo grupo : grupos) {
					alunos.addAll(new AlunoVariavelService().listaGrupo(grupo.getIdgrupo()));
				}
				
				for (AlunoVariavel alunoVariavel : alunos) {
					
					mural.setMensagem(mensagem);
					mural.setAluno(alunoVariavel);
					mural.setData(data);
					//mural.setData(new Date());
					mural.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
					
					//new MuralService().criarMural(mural);
					new MuralService().atualizarMural(mural);
				}
				
				return "ok";
				
			}else if(tutor == 0 && idOficina != 0){
				List<Rotina> rotina = new RotinaService().listarPorOficina(idOficina);
				
				List<Agrupamento> agrupamento = new ArrayList<Agrupamento>();
				
				List<AlunoAgrupamento> alunoAgrupamento = new ArrayList<AlunoAgrupamento>();

				List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
				
				
				for (Rotina rotina2 : rotina) {
					agrupamento.addAll(new AgrupamentoService().listarkey(rotina2.getAgrupamento().getIdagrupamento()));
				}
				
				for (Agrupamento agrupamento2 : agrupamento) {
					
					alunoAgrupamento.addAll(new AlunoAgrupamentoService().listarAgrupamento(agrupamento2.getIdagrupamento()));
				}
				
				for (AlunoAgrupamento alunoAgrupamento2 : alunoAgrupamento) {
					
					alunos.addAll(new AlunoVariavelService().listarkey(alunoAgrupamento2.getAluno().getIdalunoVariavel()));
				}
				
				for (AlunoVariavel alunoVariavel : alunos) {
					
					mural.setMensagem(mensagem);
					mural.setAluno(alunoVariavel);
					mural.setData(data);
					//mural.setData(new Date());
					mural.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
					
					new MuralService().criarMural(mural);

				}
				
				return "ok";
				
			}else if(tutor == 1 && idOficina != 0){
				Tutoria profTutor = new TutoriaService().listarProfessorId(idProfessor).get(0);
				
				List<Grupo> grupos = new GrupoService().listarTutor(profTutor.getIdtutoria());
				
				List<Rotina> rotina = new RotinaService().listarPorOficina(idOficina);
				
				List<Agrupamento> agrupamento = new ArrayList<Agrupamento>();
				
				List<AlunoAgrupamento> alunoAgrupamento = new ArrayList<AlunoAgrupamento>();
				
				List<AlunoVariavel> alunosComTutor = new ArrayList<AlunoVariavel>();
				List<AlunoVariavel> alunosComOficina = new ArrayList<AlunoVariavel>();
				List<AlunoVariavel> alunosConsolidados = new ArrayList<AlunoVariavel>();
				
				
				for (Grupo grupo : grupos) {
					alunosComTutor.addAll(new AlunoVariavelService().listaGrupo(grupo.getIdgrupo()));
				}
				
				
				for (Rotina rotina2 : rotina) {
					agrupamento.addAll(new AgrupamentoService().listarkey(rotina2.getAgrupamento().getIdagrupamento()));
				}
				
				for (Agrupamento agrupamento2 : agrupamento) {
					
					alunoAgrupamento.addAll(new AlunoAgrupamentoService().listarAgrupamento(agrupamento2.getIdagrupamento()));
				}
				
				for (AlunoAgrupamento alunoAgrupamento2 : alunoAgrupamento) {
					
					alunosComOficina.addAll(new AlunoVariavelService().listarkey(alunoAgrupamento2.getAluno().getIdalunoVariavel()));
				}
				
				for(int i = 0; i < alunosComTutor.size(); i++){
					if(!alunosConsolidados.contains(alunosComTutor.get(i))){
						mural.setMensagem(mensagem);
						mural.setAluno(alunosComTutor.get(i));
						mural.setData(data);
						//mural.setData(new Date());
						mural.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
						
						new MuralService().criarMural(mural);
					}
				}
				
				for(int j = 0; j < alunosComOficina.size(); j++){
					if(!alunosConsolidados.contains(alunosComOficina.get(j))){
						mural.setMensagem(mensagem);
						mural.setAluno(alunosComOficina.get(j));
						mural.setData(data);
						//mural.setData(new Date());
						mural.setProfessor(new ProfessorFuncionarioService().listarkey(idProfessor).get(0));
						
						new MuralService().criarMural(mural);
					}
				}
				
				return "ok";
			}
		}
		}
		
		return "erro";
	}
	
	
	/**
	 * Listar all
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<Mural> getMural() {
		logger.debug("Listar Mural ...");
		List<Mural> resultado;
		 resultado = new MuralService().listarTodos();
		 logger.debug("QTD Mural : " +  resultado.size());
		return resultado;
	}
		
	/**
	 * Listar por range de data
	 * @param idAluno
	 * @return
	 */
	@Path("RangeData/{idAluno}")
	@GET
	@Produces("application/json")
	public List<Mural> getRange(@PathParam("idAluno") int idAluno){
		Date dataHoje = new Date();
		SimpleDateFormat formataData = new SimpleDateFormat("yy-MM-dd");
		String data = formataData.format(dataHoje);
		//StringUtil stringUtil = new StringUtil();
		
		Calendar c = Calendar.getInstance();
		int mes = c.get(Calendar.MONTH);
		String data2;
		if(mes > 3){
			mes = mes - 3;
			String quebra[] = data.split("-");
			
			if(mes > 10){
				//data2 = quebra[0]+"-"+quebra[1]+"-"+Integer.toString(dia);
				data2 = quebra[0]+"-"+Integer.toString(mes)+"-"+quebra[2];
			}else{
				//data2 = quebra[0]+"-"+quebra[1]+"-"+"0"+Integer.toString(dia);
				data2 = quebra[0]+"-"+"0"+Integer.toString(mes)+"-"+quebra[2];
			}
			
		}else{
			int aux = 12;
			mes = mes - 3;
			aux = aux + mes;
			
			String quebra[] = data.split("-");
			int ano = Integer.parseInt(quebra[0]);
			ano = ano - 1;
			
			//data2 = Integer.toString(aux)+"/"+Integer.toString(mes)+"/"+quebra[2];
			data2 = Integer.toString(ano)+"-"+Integer.toString(aux)+"-"+quebra[2];
			
		}
		
		return new MuralService().Range(data2,data,idAluno);
	}
	
		
		
	

}
