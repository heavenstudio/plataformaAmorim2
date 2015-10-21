	package br.com.muranodesign.resources;

import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.test.framework.AppDescriptor;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;


public class Usuario extends JerseyTest  {
	@Override
	protected AppDescriptor configure() {
		return new WebAppDescriptor.Builder().build();
	}
	/*
	// Teste com professor nulo
	@Test
	public void insert() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Usuario/recuperarSenha");
		webResource.post("email=kevyn.m.ads@gmail.com");
		 
	}
	
	
	// teste com todos os campos
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8082/plataformaAmorim/Usuario");
		 webResource.post("login=TESTE1&senha=TEXTOABERTO&email=aluno%40hotmail.com&moderador=&action=create&aluno=&perfil=7&professor=5");
		 
				
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/ProducaoAluno");
		 webResource.post("action=create&anoLetivo=8&texto=1&data=21-09-2014&aluno="+918+"&tipo=6&categoria=1&roteiro=20");
	
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/PlanejamentoRoteiro/StatusPlanejamento");
		 webResource.post("id=2&status=3");
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/ProducaoAluno/");
		 webResource.post("action=create&anoLetivo=80&texto=1&aluno=918&tipo=6&categoria=1");
		 
	}*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/ProducaoAluno/");
		 webResource.post("action=create&anoLetivo=80&texto=1&aluno=918&tipo=6&categoria=1&arquivo=https://www.youtube.com/watch?v=AQM4PsWAb8s&imagem");
		 
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/PlanoEstudo/");
		 webResource.post("action=create&dataInicio=11/05/2015&objetivoTutoria=&objetivoPessoal=&tarefaDeCasa=&autoAvaliacao=&observacoesTutor=&observacoesPais=&status=1&dataFim=15/05/2015&aluno=918");
		 
		 
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/PlanoEstudo/");
		 webResource.post("id=367&dataInicio=11/05/2015&action=update&objetivoTutoria=&objetivoPessoal=teste&tarefaDeCasa=teste&autoAvaliacao=teste&observacoesTutor=&observacoesPais=teste&status=1&dataFim=13/05/2015&aluno=918");
			 
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/ProfessorFuncionario/");
		webResource.post("id=52&action=update&foto_professor_funcionario=1&dataEntradaEscola=2001-01-01&dataEntradaPrefeitura=2001-01-01&perfil=24&nome=ALEXANDRE+JOS%C3%89ddd&naturalidadePais=n%C3%A3o+fornecido&rua=n%C3%A3o+fornecido&bairro=n%C3%A3o+fornecido&numero=n%C3%A3o+fornecido&complemento=n%C3%A3o+fornecido&cep=n%C3%A3o+forn&observacao=");
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/PlanoEstudo/");
		webResource.post("id=373&dataInicio=28-05-2015&action=update&objetivoTutoria=&objetivoPessoal=ffffff&tarefaDeCasa=ffffff&autoAvaliacao=ffffffffffff&observacoesTutor=&observacoesPais=ffffffffffffffffffff&status=1&dataFim=01-06-2015&aluno=644");
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel/Relatorio");
		webResource.post("");
	}
	*/
	//	dataInicio=2016-10-10&dataFim=2016-11-10&evento=10&descricao=&tipoEvento=46&ano=2015&aula=1&feriado=0&hora=08:35
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel/alunoGrupo");
		webResource.post("alunos=489&grupo=206");
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel/alunoGrupo");
		webResource.post("alunos=489;504;506&grupo=221");
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Grupo");
	webResource.post("action=create&idProfessor=60&anoEstudo=1&periodo=T&idPerido=9&lider=0");
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Logar/AlterarSenhaFull");
	webResource.post("senha=TESTE&id=674");
	
	}*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/HistoricoEventos");
	webResource.post("action=update&ano=");
	}
	*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel/listarAlunoAgrupamento");
	webResource.post("Tutoria=3&Ano=0&Periodo=0");
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AlunoVariavel/Relatorio");
	webResource.post("Ano=6&email=email");
	}
	*/
	
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Oficina");
	webResource.post("action=create&tipo=matematica&nome=Matematica&periodo=8&ciclo=1&anoLetivo=60");
	
	
	
	}
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/PlanoAula");
	webResource.post("action=create&idProfessor=52&data_ini=25-05-1994&data_fim=25-05-1995&objetivos=sgsdg&tarefa_casa=lbbj&registro_atividade=ashfhhf");
	
	}*/
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/AgendamentoSala");
	webResource.post("action=create&idrotina=");
	}*/
	
	/*
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Oficina");
	webResource.post("action=update&nome=Fisica&id=1");
	}
	*/
	/*
	@Test
	public void insert3() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Oficina");
	webResource.post("action=delete&id=1");
	}*/
	
			
}
