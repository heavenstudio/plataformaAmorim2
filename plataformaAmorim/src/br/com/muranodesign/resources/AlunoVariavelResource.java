/**
 *   Este codigo é software livre você e pode resdistribuir e/ou modificar ele seguindo os termos da
 *   Creative Commons Attribution 4.0 International Pare visualizar uma copia desta 
 *   licensa em ingles visite http://creativecommons.org/licenses/by/4.0/.
 *   
 *   This code is free software; you can redistribute it and/or modify it
 *   under the terms of Creative Commons Attribution 4.0 International License. 
 *   To view a copy of this license, visit http://creativecommons.org/licenses/by/4.0/.
 */
package br.com.muranodesign.resources;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import br.com.muranodesign.business.AlunoService;
import br.com.muranodesign.business.AlunoVariavelService;
import br.com.muranodesign.business.AnoEstudoService;
import br.com.muranodesign.business.AnoLetivoService;
import br.com.muranodesign.business.GrupoService;
import br.com.muranodesign.business.PeriodoService;
import br.com.muranodesign.model.Aluno;
import br.com.muranodesign.model.AlunoVariavel;
import br.com.muranodesign.model.AnoEstudo;
import br.com.muranodesign.model.AnoLetivo;
import br.com.muranodesign.model.Grupo;
import br.com.muranodesign.model.Periodo;
import br.com.muranodesign.util.StringUtil;




/**
 * Classe tem como objetivo disponibilizar os serviços relacionandos a Aluno Variavel.
 * 
 * @author Rogerio Lima dos Santos
 * @version 1.00
 * @since Release 1 da aplicação
 */
@Path("AlunoVariavel")
public class AlunoVariavelResource {

	/** The logger. */
	private Logger logger = Logger.getLogger(AlunoVariavelResource.class.getName());

	/**
	 * Serviço reponsavel por Lista todos os alunos variaveis.
	 * 
	 * @return Alunos Variavel
	 */
	@Path("html/{ano}/{periodo}")
	@GET
	@Produces("text/plain")
	public /*List<AlunoVariavel>*/String getAlunoVariavelhtml(@PathParam("ano") int ano, @PathParam("periodo") int periodo) {
		logger.info("Listar AlunoVariavel ...");
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listaAnoEstudoPeriodo(new AnoEstudoService().listarkey(ano).get(0), new PeriodoService().listarkey(periodo).get(0));
		logger.info("QTD AlunoVariavel : " + resultado.size());
		int tamanho = resultado.size();
		String HtmlContent = "";
		
		for(int i = 0; i < tamanho; i++){
			//carrega professor na combo
			if(resultado.get(i).getGrupo() == null || resultado.get(i).getGrupo().getStatus().equals("1")){
				HtmlContent += "<option value="+resultado.get(i).getIdalunoVariavel()+">"+resultado.get(i).getAluno().getNome()+"</option>";
			}
		}
	
		return HtmlContent;
	}
	
	/**
	 * Lista todos os alunos variaveis 
	 * @return
	 */
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getAlunoVariavel() {
		logger.info("Listar AlunoVariavel ...");
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listarTodos();
		logger.info("QTD AlunoVariavel : " + resultado.size());
		return resultado;
	}
	
	/*
	@Path("teste")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> teste() {
		List<AlunoVariavel> resultado = new ArrayList<AlunoVariavel>();
		String k = "h";
		 new AlunoVariavelService().update(488,k);
		return resultado;
	}
	*/
	
	/**
	 * Serviço reponsavel por Lista todos os alunos variaveis por status.
	 * 
	 * @return Alunos Variavel
	 */
	@Path("listar/{status}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getAlunoVariavel(@PathParam("status") int status) {
		logger.info("Listar AlunoVariavel ...");
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listarTodos(status);
		logger.info("QTD AlunoVariavel : " + resultado.size());
		return resultado;
	}
	
	@Path("Relatorio")
	@POST
	@Produces("application/json")
	public /*List<*/List<String>/*>*/ relatorio(
			@FormParam("Tutoria") int Tutoria,
			@FormParam("Ano") int Ano,
			@FormParam("Periodo") int Periodo,
			
			@FormParam("Nome") String Nome,
			@FormParam("Sexo") String Sexo	,
			@FormParam("Datanascimento") String Datanascimento,
			@FormParam("Endereco") String Endereco,
			@FormParam("TelefoneResidencial") String TelefoneResidencial,
			@FormParam("TelefoneCelular") String TelefoneCelular,
			@FormParam("email") String email,
			@FormParam("NomeResponsavel") String NomeResponsavel,
			@FormParam("ParentescoResponsavel") String ParentescoResponsavel,
			@FormParam("TelefoneResidencialResponsavel") String TelefoneResidencialResponsavel,
			@FormParam("TelefoneCelularResponsavel") String TelefoneCelularResponsavel,
			@FormParam("TelefoneComercialResponsavel") String TelefoneComercialResponsavel,
			@FormParam("emailResponsavel") String emailResponsavel,
			@FormParam("NomeMae") String NomeMae,
			@FormParam("EnderecoMae") String EnderecoMae,
			@FormParam("TelefoneCelularMae") String TelefoneCelularMae,
			@FormParam("TelefoneResidencialMae") String TelefoneResidencialMae,
			@FormParam("TelefoneComercialMae") String TelefoneComercialMae,
			@FormParam("emailMae") String emailMae//,
		//	@FormParam("Caminho") String Caminho
			
			
			){
		
		
			
				 
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet firstSheet = workbook.createSheet("Relatorio Aluno");
			 
			FileOutputStream fos = null;
			
			String nomeArquivo;
			 
			try {
				
			nomeArquivo = new StringUtil().geraNomeAleatorio("xls", 15);
			fos = new FileOutputStream(new File("/home/tomcat/webapps/relatorio/"+ nomeArquivo));
			 
			// Este trecho obtem uma lista de objetos do tipo CD
			 
			// do banco de dados através de um DAO e itera sobre a lista
			 
			// criando linhas e colunas em um arquivo Excel com o conteúdo
			 
			// dos objetos.
			 
			
			 
			
			/* 
			for (int i = 0; i < 4; i++) {
			HSSFRow row = firstSheet.createRow(i);
			 
			row.createCell((short) 0).setCellValue("primeiro");
			row.createCell((short) 1).setCellValue("segundo");
			row.createCell((short) 2).setCellValue("terceito");
			row.createCell((short) 3).setCellValue("quarto");
			row.createCell((short) 4).setCellValue("teste");
			
			 
			 
			} // fim do for
			 
		*/
		
		
	
		HSSFRow row = firstSheet.createRow(0);
		
		List<String> retorno = new ArrayList<String>();
		List<List<String>> listRetorno = new ArrayList<List<String>>();
		
		List<AlunoVariavel> alunos = new ArrayList<AlunoVariavel>();
		List<Grupo> grupo = new ArrayList<Grupo>();
		
		String html = "";
		String parte = "";
		
		int h = 0;
		row = firstSheet.createRow(h);
		row.createCell((short) 0).setCellValue("Nome");
		row.createCell((short) 1).setCellValue("Sexo");
		row.createCell((short) 2).setCellValue("Datanascimento");
		row.createCell((short) 3).setCellValue("Endereco");
		row.createCell((short) 4).setCellValue("TelefoneResidencial");
		row.createCell((short) 5).setCellValue("TelefoneCelular");
		row.createCell((short) 6).setCellValue("email");
		row.createCell((short) 7).setCellValue("NomeResponsavel");
		row.createCell((short) 8).setCellValue("ParentescoResponsavel");
		row.createCell((short) 9).setCellValue("TelefoneResidencialResponsavel");
		row.createCell((short) 10).setCellValue("TelefoneCelularResponsavel");
		row.createCell((short) 11).setCellValue("TelefoneComercialResponsavel");
		row.createCell((short) 12).setCellValue("emailResponsavel");
		row.createCell((short) 13).setCellValue("NomeMae");
		row.createCell((short) 14).setCellValue("EnderecoMae");
		row.createCell((short) 15).setCellValue("TelefoneCelularMae");
		row.createCell((short) 16).setCellValue("TelefoneResidencialMae");
		row.createCell((short) 17).setCellValue("TelefoneComercialMae");
		row.createCell((short) 18).setCellValue("emailMae");
		
		
		if(Tutoria != 0 && Periodo == 0 & Ano == 0){
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						h++;
						row = firstSheet.createRow(h);
						//row.createCell((short) 0).setCellValue("Relatório");
						
						if(Nome != "" && Nome != null){
							
							
							String valor = alunos.get(k).getAluno().getNome();
							
							if(valor != null){
								retorno.add(valor);
								row.createCell((short) 0).setCellValue(valor);
								
							}else{
								retorno.add("não informado");
							}
						
						}
						if(Sexo != "" && Sexo != null){
							
							String valor = alunos.get(k).getAluno().getSexo();
							
							if(valor != null){
								retorno.add(valor);
								row.createCell((short) 1).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
						}
						if(Datanascimento  != "" && Datanascimento  != null){
							
							Date valor = alunos.get(k).getAluno().getDataNascimento();
							
							if(valor != null){
								retorno.add(valor.toString());
								row.createCell((short) 2).setCellValue(valor);
							}else{
								retorno.add( null);
							}
							
							
						}
						if(Endereco != "" && Endereco != null){
							
							String valor = alunos.get(k).getAluno().getEndereco();
							
							if(valor != null){
								retorno.add(valor);
								row.createCell((short) 3).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneResidencial != "" && TelefoneResidencial != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
							
							if(valor != null){
								retorno.add(valor);
								row.createCell((short) 4).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(TelefoneCelular != "" && TelefoneCelular != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
							
							if(valor != null){
								retorno.add(valor);
		
								row.createCell((short) 5).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(email != "" && email != null){
							
							String valor = alunos.get(k).getAluno().getEmail();
							
							if(valor != null){
								retorno.add(valor);
				
								row.createCell((short) 6).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(NomeResponsavel != "" && NomeResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getNomeResponsavel();	
							
							if(valor != null){
								retorno.add(valor);
			
								row.createCell((short) 7).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getParentescoResponsavel();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 8).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 9).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						
						}
						if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
							
							if(valor != null){
								retorno.add(valor);
				
								row.createCell((short) 10).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
							
							if(valor != null){
								retorno.add(valor);
								
								row.createCell((short) 11).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(emailResponsavel != "" && emailResponsavel != null){
							
							String valor = alunos.get(k).getAluno().getEmail1Responsavel();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 12).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(NomeMae != "" && NomeMae != null){
							
							String valor = alunos.get(k).getAluno().getNomeMae();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 13).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(EnderecoMae != "" && EnderecoMae != null){
							
							String valor = alunos.get(k).getAluno().getEnderecoMae();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 14).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 15).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						
						}
						if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 16).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
							
						}
						if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
							
							String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
							
							if(valor != null){
								retorno.add(valor);
								
								row.createCell((short) 17).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
							
							
						}
						if(emailMae != "" && emailMae != null){
							
							String valor = alunos.get(k).getAluno().getEmail1Mae();
							
							if(valor != null){
								retorno.add(valor);
							
								row.createCell((short) 18).setCellValue(valor);
							}else{
								retorno.add("não informado");
							}
						}
						
						listRetorno.add(retorno);
						
					}
				}
			}
			
			
		}else if(Ano != 0 && Tutoria == 0 && Periodo == 0){
			
			alunos = new AlunoVariavelService().listaAnoEstudo(new AnoEstudoService().listarkey(Ano).get(0));
			if(!alunos.isEmpty()){
				for(int k =0; k < alunos.size(); k++){
					
					h++;
					row = firstSheet.createRow(h);
					row.createCell((short) 0).setCellValue("Relatório");
					if(Nome != "" && Nome != null){
						
						String valor = alunos.get(k).getAluno().getNome();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					
					}
					if(Sexo != "" && Sexo != null){
						
						String valor = alunos.get(k).getAluno().getSexo();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
					}
					if(Datanascimento  != "" && Datanascimento  != null){
						
						Date valor = alunos.get(k).getAluno().getDataNascimento();
						
						if(valor != null){
							retorno.add(valor.toString());
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add( null);
						}
						
						
					}
					if(Endereco != "" && Endereco != null){
						
						String valor = alunos.get(k).getAluno().getEndereco();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencial != "" && TelefoneResidencial != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelular != "" && TelefoneCelular != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(email != "" && email != null){
						
						String valor = alunos.get(k).getAluno().getEmail();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(NomeResponsavel != "" && NomeResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getNomeResponsavel();	
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getParentescoResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(emailResponsavel != "" && emailResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Responsavel();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(NomeMae != "" && NomeMae != null){
						
						String valor = alunos.get(k).getAluno().getNomeMae();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(EnderecoMae != "" && EnderecoMae != null){
						
						String valor = alunos.get(k).getAluno().getEnderecoMae();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(emailMae != "" && emailMae != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Mae();
						
						if(valor != null){
							retorno.add(valor);
							h++;
							row = firstSheet.createRow(h);
							row.createCell((short) 0).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					}
					
					listRetorno.add(retorno);
					
				}
			}
			
		}else if(Periodo != 0 && Ano == 0 && Tutoria == 0){
			
			alunos = new AlunoVariavelService().listaPeriodo(new PeriodoService().listarkey(Periodo).get(0));
			if(!alunos.isEmpty()){
				for(int k =0; k < alunos.size(); k++){
					h++;
					row = firstSheet.createRow(h);
					//row.createCell((short) 0).setCellValue("Relatório");
					
					if(Nome != "" && Nome != null){
						
						
						String valor = alunos.get(k).getAluno().getNome();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 0).setCellValue(valor);
							
						}else{
							retorno.add("não informado");
						}
					
					}
					if(Sexo != "" && Sexo != null){
						
						String valor = alunos.get(k).getAluno().getSexo();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 1).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
					}
					if(Datanascimento  != "" && Datanascimento  != null){
						
						Date valor = alunos.get(k).getAluno().getDataNascimento();
						
						if(valor != null){
							retorno.add(valor.toString());
							row.createCell((short) 2).setCellValue(valor);
						}else{
							retorno.add( null);
						}
						
						
					}
					if(Endereco != "" && Endereco != null){
						
						String valor = alunos.get(k).getAluno().getEndereco();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 3).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencial != "" && TelefoneResidencial != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 4).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelular != "" && TelefoneCelular != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null){
							retorno.add(valor);
	
							row.createCell((short) 5).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(email != "" && email != null){
						
						String valor = alunos.get(k).getAluno().getEmail();
						
						if(valor != null){
							retorno.add(valor);
			
							row.createCell((short) 6).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(NomeResponsavel != "" && NomeResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getNomeResponsavel();	
						
						if(valor != null){
							retorno.add(valor);
		
							row.createCell((short) 7).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getParentescoResponsavel();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 8).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 9).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null){
							retorno.add(valor);
			
							row.createCell((short) 10).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							
							row.createCell((short) 11).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(emailResponsavel != "" && emailResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Responsavel();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 12).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(NomeMae != "" && NomeMae != null){
						
						String valor = alunos.get(k).getAluno().getNomeMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 13).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(EnderecoMae != "" && EnderecoMae != null){
						
						String valor = alunos.get(k).getAluno().getEnderecoMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 14).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 15).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 16).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
						
						if(valor != null){
							retorno.add(valor);
							
							row.createCell((short) 17).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(emailMae != "" && emailMae != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Mae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 18).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					}
					
					listRetorno.add(retorno);
					
				}
			}
			
		}else if(Tutoria != 0 && Ano != 0 && Periodo == 0){
			
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						if(alunos.get(k).getAnoEstudo().getIdanoEstudo() == Ano){
							h++;
							row = firstSheet.createRow(h);
							//row.createCell((short) 0).setCellValue("Relatório");
							
							if(Nome != "" && Nome != null){
								
								
								String valor = alunos.get(k).getAluno().getNome();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 0).setCellValue(valor);
									
								}else{
									retorno.add("não informado");
								}
							
							}
							if(Sexo != "" && Sexo != null){
								
								String valor = alunos.get(k).getAluno().getSexo();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 1).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
							}
							if(Datanascimento  != "" && Datanascimento  != null){
								
								Date valor = alunos.get(k).getAluno().getDataNascimento();
								
								if(valor != null){
									retorno.add(valor.toString());
									row.createCell((short) 2).setCellValue(valor);
								}else{
									retorno.add( null);
								}
								
								
							}
							if(Endereco != "" && Endereco != null){
								
								String valor = alunos.get(k).getAluno().getEndereco();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 3).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencial != "" && TelefoneResidencial != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 4).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelular != "" && TelefoneCelular != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null){
									retorno.add(valor);
			
									row.createCell((short) 5).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(email != "" && email != null){
								
								String valor = alunos.get(k).getAluno().getEmail();
								
								if(valor != null){
									retorno.add(valor);
					
									row.createCell((short) 6).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(NomeResponsavel != "" && NomeResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getNomeResponsavel();	
								
								if(valor != null){
									retorno.add(valor);
				
									row.createCell((short) 7).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getParentescoResponsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 8).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 9).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null){
									retorno.add(valor);
					
									row.createCell((short) 10).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
									
									row.createCell((short) 11).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(emailResponsavel != "" && emailResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Responsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 12).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(NomeMae != "" && NomeMae != null){
								
								String valor = alunos.get(k).getAluno().getNomeMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 13).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(EnderecoMae != "" && EnderecoMae != null){
								
								String valor = alunos.get(k).getAluno().getEnderecoMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 14).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 15).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 16).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
								
								if(valor != null){
									retorno.add(valor);
									
									row.createCell((short) 17).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(emailMae != "" && emailMae != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Mae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 18).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
							}
							
							listRetorno.add(retorno);
							
						}
				}
				}
			}
			
		}else if(Tutoria != 0 && Periodo != 0 && Ano == 0){
			
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						if(alunos.get(k).getPeriodo().getIdperiodo() == Periodo){
							h++;
							row = firstSheet.createRow(h);
							//row.createCell((short) 0).setCellValue("Relatório");
							
							if(Nome != "" && Nome != null){
								
								
								String valor = alunos.get(k).getAluno().getNome();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 0).setCellValue(valor);
									
								}else{
									retorno.add("não informado");
								}
							
							}
							if(Sexo != "" && Sexo != null){
								
								String valor = alunos.get(k).getAluno().getSexo();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 1).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
							}
							if(Datanascimento  != "" && Datanascimento  != null){
								
								Date valor = alunos.get(k).getAluno().getDataNascimento();
								
								if(valor != null){
									retorno.add(valor.toString());
									row.createCell((short) 2).setCellValue(valor);
								}else{
									retorno.add( null);
								}
								
								
							}
							if(Endereco != "" && Endereco != null){
								
								String valor = alunos.get(k).getAluno().getEndereco();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 3).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencial != "" && TelefoneResidencial != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 4).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelular != "" && TelefoneCelular != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null){
									retorno.add(valor);
			
									row.createCell((short) 5).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(email != "" && email != null){
								
								String valor = alunos.get(k).getAluno().getEmail();
								
								if(valor != null){
									retorno.add(valor);
					
									row.createCell((short) 6).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(NomeResponsavel != "" && NomeResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getNomeResponsavel();	
								
								if(valor != null){
									retorno.add(valor);
				
									row.createCell((short) 7).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getParentescoResponsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 8).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 9).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null){
									retorno.add(valor);
					
									row.createCell((short) 10).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
									
									row.createCell((short) 11).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(emailResponsavel != "" && emailResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Responsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 12).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(NomeMae != "" && NomeMae != null){
								
								String valor = alunos.get(k).getAluno().getNomeMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 13).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(EnderecoMae != "" && EnderecoMae != null){
								
								String valor = alunos.get(k).getAluno().getEnderecoMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 14).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 15).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 16).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
								
								if(valor != null){
									retorno.add(valor);
									
									row.createCell((short) 17).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(emailMae != "" && emailMae != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Mae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 18).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
							}
							
							listRetorno.add(retorno);
							
						}
					}
				}
			}
			
		}else if(Periodo != 0 && Ano != 0 && Tutoria == 0){
			alunos = new AlunoVariavelService().listaAnoEstudoPeriodoComgrupo(new AnoEstudoService().listarkey(Ano).get(0), new PeriodoService().listarkey(Periodo).get(0));
			if(!alunos.isEmpty()){
				for(int k =0; k < alunos.size(); k++){
					h++;
					row = firstSheet.createRow(h);
					//row.createCell((short) 0).setCellValue("Relatório");
					
					if(Nome != "" && Nome != null){
						
						
						String valor = alunos.get(k).getAluno().getNome();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 0).setCellValue(valor);
							
						}else{
							retorno.add("não informado");
						}
					
					}
					if(Sexo != "" && Sexo != null){
						
						String valor = alunos.get(k).getAluno().getSexo();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 1).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
					}
					if(Datanascimento  != "" && Datanascimento  != null){
						
						Date valor = alunos.get(k).getAluno().getDataNascimento();
						
						if(valor != null){
							retorno.add(valor.toString());
							row.createCell((short) 2).setCellValue(valor);
						}else{
							retorno.add( null);
						}
						
						
					}
					if(Endereco != "" && Endereco != null){
						
						String valor = alunos.get(k).getAluno().getEndereco();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 3).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencial != "" && TelefoneResidencial != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							row.createCell((short) 4).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelular != "" && TelefoneCelular != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null){
							retorno.add(valor);
	
							row.createCell((short) 5).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(email != "" && email != null){
						
						String valor = alunos.get(k).getAluno().getEmail();
						
						if(valor != null){
							retorno.add(valor);
			
							row.createCell((short) 6).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(NomeResponsavel != "" && NomeResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getNomeResponsavel();	
						
						if(valor != null){
							retorno.add(valor);
		
							row.createCell((short) 7).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getParentescoResponsavel();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 8).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 9).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
						
						if(valor != null){
							retorno.add(valor);
			
							row.createCell((short) 10).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
						
						if(valor != null){
							retorno.add(valor);
							
							row.createCell((short) 11).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(emailResponsavel != "" && emailResponsavel != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Responsavel();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 12).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(NomeMae != "" && NomeMae != null){
						
						String valor = alunos.get(k).getAluno().getNomeMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 13).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(EnderecoMae != "" && EnderecoMae != null){
						
						String valor = alunos.get(k).getAluno().getEnderecoMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 14).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 15).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					
					}
					if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 16).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
						
					}
					if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
						
						String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
						
						if(valor != null){
							retorno.add(valor);
							
							row.createCell((short) 17).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
						
						
					}
					if(emailMae != "" && emailMae != null){
						
						String valor = alunos.get(k).getAluno().getEmail1Mae();
						
						if(valor != null){
							retorno.add(valor);
						
							row.createCell((short) 18).setCellValue(valor);
						}else{
							retorno.add("não informado");
						}
					}
					
					listRetorno.add(retorno);
					
				}
			}
		}else if(Tutoria != 0 && Ano != 0 && Periodo != 0){
			grupo = new GrupoService().listarTutor(Tutoria);
			int qtd = grupo.size();
			
			for(int i = 0; i < qtd; i++){
				alunos = new AlunoVariavelService().listaGrupo(grupo.get(i).getIdgrupo());
				if(!alunos.isEmpty()){
					for(int k =0; k < alunos.size(); k++){
						if(alunos.get(k).getAnoEstudo().getIdanoEstudo() == Ano && alunos.get(k).getPeriodo().getIdperiodo() == Periodo){
							h++;
							row = firstSheet.createRow(h);
							//row.createCell((short) 0).setCellValue("Relatório");
							
							if(Nome != "" && Nome != null){
								
								
								String valor = alunos.get(k).getAluno().getNome();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 0).setCellValue(valor);
									
								}else{
									retorno.add("não informado");
								}
							
							}
							if(Sexo != "" && Sexo != null){
								
								String valor = alunos.get(k).getAluno().getSexo();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 1).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
							}
							if(Datanascimento  != "" && Datanascimento  != null){
								
								Date valor = alunos.get(k).getAluno().getDataNascimento();
								
								if(valor != null){
									retorno.add(valor.toString());
									row.createCell((short) 2).setCellValue(valor);
								}else{
									retorno.add( null);
								}
								
								
							}
							if(Endereco != "" && Endereco != null){
								
								String valor = alunos.get(k).getAluno().getEndereco();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 3).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencial != "" && TelefoneResidencial != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
									row.createCell((short) 4).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelular != "" && TelefoneCelular != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null){
									retorno.add(valor);
			
									row.createCell((short) 5).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(email != "" && email != null){
								
								String valor = alunos.get(k).getAluno().getEmail();
								
								if(valor != null){
									retorno.add(valor);
					
									row.createCell((short) 6).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(NomeResponsavel != "" && NomeResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getNomeResponsavel();	
								
								if(valor != null){
									retorno.add(valor);
				
									row.createCell((short) 7).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(ParentescoResponsavel != "" && ParentescoResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getParentescoResponsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 8).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneResidencialResponsavel != "" && TelefoneResidencialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 9).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneCelularResponsavel != "" && TelefoneCelularResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularResponsavel();
								
								if(valor != null){
									retorno.add(valor);
					
									row.createCell((short) 10).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialResponsavel != "" && TelefoneComercialResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialResponsavel();
								
								if(valor != null){
									retorno.add(valor);
									
									row.createCell((short) 11).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(emailResponsavel != "" && emailResponsavel != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Responsavel();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 12).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(NomeMae != "" && NomeMae != null){
								
								String valor = alunos.get(k).getAluno().getNomeMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 13).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(EnderecoMae != "" && EnderecoMae != null){
								
								String valor = alunos.get(k).getAluno().getEnderecoMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 14).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(TelefoneCelularMae != "" && TelefoneCelularMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneCelularMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 15).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							
							}
							if(TelefoneResidencialMae != "" && TelefoneResidencialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneResidencialMae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 16).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
								
							}
							if(TelefoneComercialMae != "" && TelefoneComercialMae != null){
								
								String valor = alunos.get(k).getAluno().getTelefoneComercialMae();
								
								if(valor != null){
									retorno.add(valor);
									
									row.createCell((short) 17).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
								
								
							}
							if(emailMae != "" && emailMae != null){
								
								String valor = alunos.get(k).getAluno().getEmail1Mae();
								
								if(valor != null){
									retorno.add(valor);
								
									row.createCell((short) 18).setCellValue(valor);
								}else{
									retorno.add("não informado");
								}
							}
							
							listRetorno.add(retorno);
							
						}
					}
				}
			}
		}
		if(listRetorno.isEmpty()){
		   retorno.add("não há valores");
	       listRetorno.add(retorno);
		}
		
			workbook.write(fos);
			
			
			retorno.add("http://177.55.99.90/relatorio/"+nomeArquivo);
			listRetorno.add(retorno);
			return listRetorno.get(0);
			 
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Erro ao exportar arquivo");
			} finally {
				try {
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;

		
		
	}
	

	/**
	 * Lista Aluno Variavel especifico.
	 *
	 * @param id do  Aluno Variavel
	 * @return the Aluno Variavel
	 */
	@Path("{id}")
	@GET
	@Produces("application/json")
	public AlunoVariavel getEvento(@PathParam("id") int id) {
		logger.info("Lista AlunoVariavel  por id " + id);
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listarkey(id);
		AlunoVariavel evento = resultado.get(0);

		return evento;
	}
	
	/**
	 * Listar Aluno variavel
	 * @param id
	 * @return
	 */
	@Path("aluno/{id}")
	@GET
	@Produces("application/json")
	public AlunoVariavel getAluno(@PathParam("id") int id) {
		logger.info("Lista AlunoVariavel  por id " + id);
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listaAluno(id);
		AlunoVariavel evento = resultado.get(0);

		return evento;
	}
	
	/**
	 * Listar grupo
	 * @param id
	 * @return
	 */
	@Path("grupo/{id}")
	@GET
	@Produces("application/json")
	public List<AlunoVariavel> getGrupo(@PathParam("id") int id) {
		logger.info("Lista AlunoVariavel  por grupo " + id);
		List<AlunoVariavel> resultado;
		resultado = new AlunoVariavelService().listaGrupo(id);
		///AlunoVariavel evento = resultado.get(0);

		return resultado;
	}
	
	
	
	/**
	 * Removes o aluno variavel.
	 *
	 * @param action  Metodo a ser executado create ou update
	 * @param id  id do aluno variavel
	 * @return true se for removido e false se der erro.
	 */
	@Path("{action}/{id}")
	@Produces("text/plain")
	public String removeAlunoVariavel(@PathParam("action") String action,
			@PathParam("id") int id) {

		logger.info("AlunoVariavel  " + action);
		if ( action.equals("delete")) {
			List<AlunoVariavel> resultado;
			resultado = new AlunoVariavelService().listarkey(id);
			AlunoVariavel res = resultado.get(0);
			new AlunoVariavelService().deletarAlunoVariavel(res);
			return "true";
		} else {
			return "false";
		}
	}
	

	/**
	 * Serviço reponsavel por cadastra e atualizar dados do aluno variavel
	 *
	 * @param action Metodo a ser executado create ou update
	 * @param strid id para update
	 * @param inicio data inicio
	 * @param programaSocial faz parte de algum programa social
	 * @param anoEstudo  ano estudo
	 * @param anoLetivo  ano letivo
	 * @param aluno id do aluno aluno
	 * @param periodo  periodo
	 * @param grupo  grupo
	 * @param ativo  status do aluno 0 deletado 1 valido
	 * @return identificador de controle interno da aplicação
	 */
	@POST
	@Produces("text/plain")
	public String eventoAction(
			
			@FormParam("action") String action,
			@FormParam("id") String strid,
			
			@FormParam("inicio") String inicio,
			@FormParam("programaSocial") String programaSocial,
			
			@FormParam("anoEstudo") String anoEstudo,
			@FormParam("anoLetivo") String anoLetivo,
			@FormParam("aluno") String aluno,
			@FormParam("periodo") String periodo,
			@FormParam("grupo") String grupo,
			@FormParam("ativo") int ativo
			
			
			) {
		AlunoVariavel objAlunoVariavel = new AlunoVariavel();
		logger.info("eventoAction ...");
		AlunoVariavel resultado;

		
		List<AnoEstudo> rsAnoEstudo;
		rsAnoEstudo = new AnoEstudoService().listarkey(Integer.parseInt(anoEstudo));
		AnoEstudo objAnoEstudo= rsAnoEstudo.get(0);
		
		List<AnoLetivo> rsAnoLetivo;
		rsAnoLetivo = new AnoLetivoService().listarkey(Integer.parseInt(anoLetivo));
		AnoLetivo objAnoLetivo= rsAnoLetivo.get(0);
		
		
		List<Periodo> rsPeriodo;
		rsPeriodo= new PeriodoService().listarkey(Integer.parseInt(periodo));
		Periodo objPeriodo= rsPeriodo.get(0);
		
		
		List<Aluno> rsAluno;
		rsAluno = new AlunoService().listarkey(Integer.parseInt(aluno));
		Aluno objAluno= rsAluno.get(0);
		
		Grupo objGrupo = new Grupo();
		
		if (!grupo.isEmpty()){
			List<Grupo> rsGrupo;
			rsGrupo = new GrupoService().listarkey(Integer.parseInt(grupo));
			 if(rsGrupo.isEmpty()){
				 objGrupo = null;
			 }else{
				 objGrupo= rsGrupo.get(0);
			 }
		} else {
			objGrupo = null;
		}
		
		
		StringUtil stringUtil = new StringUtil();

		
		if (action.equals("create")) {
			
			objAlunoVariavel.setAluno(objAluno);
			objAlunoVariavel.setInicio(stringUtil.converteStringData(inicio));
			
			objAlunoVariavel.setAnoEstudo(objAnoEstudo);
			objAlunoVariavel.setAnoLetivo(objAnoLetivo);
			objAlunoVariavel.setPeriodo(objPeriodo);
			objAlunoVariavel.setAluno(objAluno);
			objAlunoVariavel.setGrupo(objGrupo);
			objAlunoVariavel.setProgramaSocial(programaSocial);
			objAlunoVariavel.setAtivo(ativo);
			
			
			
			resultado = new AlunoVariavelService().criarAlunoVariavel(objAlunoVariavel);
			
		}  else if (action.equals("update")) {
			
			int id=Integer.parseInt(strid);
			List<AlunoVariavel> rsAlunoVariavel;
			rsAlunoVariavel= new AlunoVariavelService().listarkey(id);
			objAlunoVariavel= rsAlunoVariavel.get(0);
		
			objAlunoVariavel.setAluno(objAluno);
			objAlunoVariavel.setInicio(stringUtil.converteStringData(inicio));
			
			objAlunoVariavel.setAnoEstudo(objAnoEstudo);
			objAlunoVariavel.setAnoLetivo(objAnoLetivo);
			objAlunoVariavel.setPeriodo(objPeriodo);
			objAlunoVariavel.setAluno(objAluno);
			objAlunoVariavel.setGrupo(objGrupo);
			objAlunoVariavel.setAtivo(ativo);
			objAlunoVariavel.setProgramaSocial(programaSocial);
			
			
			
			
			 resultado =  new AlunoVariavelService().atualizarAlunoVariavel(objAlunoVariavel);
			
		} else {
			return "0";
		}
	    return Integer.toString(resultado.getIdalunoVariavel());
	
		}
	
	
	/**
	 * Servico de divine grupo para uma lista de alunos
	 *
	 * @param lista de alunos separados por ";"
	 * @param id do grupo 
	 * @return retorna true se foi alterado o grupo com sucesso.
	 */
	@Path("alunoGrupo")
	@POST
	@Produces("text/plain")
	public String eventoAction2( @FormParam("alunos") String alunos,
			@FormParam("grupo") String grupo){
		
		
        logger.info("Buscando Grupo ...");
		
        Grupo objGrupo = new Grupo();
        
        if (!grupo.isEmpty()) {
        	List<Grupo> rsGrupo;
    		rsGrupo = new GrupoService().listarkey(Integer.parseInt(grupo));
    		objGrupo= rsGrupo.get(0);
    		
        }else {
        	objGrupo = null;
        }
        
        String [] arrayAlunos = alunos.split(";");
		logger.info("QTD Alunos " +  arrayAlunos.length);
		
		
		List<AlunoVariavel> rsAluno;
		rsAluno = new AlunoVariavelService().listaGrupo(Integer.parseInt(grupo));
		
		if(arrayAlunos.length < rsAluno.size()){
			if(rsAluno.isEmpty()){
				for(int t = 0; t < arrayAlunos.length; t++){
					int id = Integer.parseInt(arrayAlunos[t]);
					new AlunoVariavelService().update(id, objGrupo.getIdgrupo());
				}
			}else{
				int diferenca =  arrayAlunos.length;
				 for(int m = diferenca; m < rsAluno.size(); m++){
					   rsAluno.get(m).setGrupo(null);
					   new AlunoVariavelService().atualizarAlunoVariavel(rsAluno.get(m));
				   }
				 return "true";
			}
		}
		
		if(rsAluno.size() < arrayAlunos.length){
			int diferenca =  rsAluno.size();
			   for(int k = diferenca; k <  arrayAlunos.length; k++){
				   int id = Integer.parseInt(arrayAlunos[k]);
				   new AlunoVariavelService().update(id, objGrupo.getIdgrupo());
			   }
		}
		
		
		for(int i = 0; i < rsAluno.size(); i++){
			
			logger.info("Buscando aluno ..." + arrayAlunos[i] );
			AlunoVariavel objAlunoVariavel;
			int id=Integer.parseInt(arrayAlunos[i]);
			List<AlunoVariavel> rsAlunoVariavel;
			
			objAlunoVariavel= new AlunoVariavelService().getAluno(id); 
			
			if (objAlunoVariavel == null ) {
				
				logger.info("Não foi possivel completar a operacao buscando aluno ..." + arrayAlunos[i] );
				
			}else {
				
				if(Integer.parseInt(arrayAlunos[i]) != rsAluno.get(i).getIdalunoVariavel()){
					
					rsAluno.get(i).setGrupo(null);
					new AlunoVariavelService().atualizarAlunoVariavel(rsAluno.get(i));
					
					new AlunoVariavelService().update(Integer.parseInt(arrayAlunos[i]), objGrupo.getIdgrupo());

				}
			}
		}

		return "true";
	}
}
