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
	
	@Test
	public void insert2() {
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Alunos");
			webResource.post("action=create&cidadeMae=&cidadePai=&cidadeResponsavel=&complementoMae=&complementoPai=&complementoResponsavel=&email2Mae=undefined&email2Pai=undefined&email2Responsavel=undefined&numeroMae=&numeroPai=&numeroResponsavel=&pais=&responsaveisResponsavel=&enderecoMae=&enderecoPai=&enderecoResponsavel=&uf=PE&ufMae=0&ufPai=0&ufResponsavel=0&fotoAluno=1&ativo=s&parentescoResponsavel=&enecessidadeEspecial=N&eresponsavelLegalMae=N&eresponsavelLegalPai=N&eresponsavelLegalResponsavel=N&cepMae=&cepPai=&cepResponsavel=undefined&bairro=Ceroulas&cep=46454565&dataMatricula=2016-04-26&dataNascimento=1996-06-18&email1Mae=&email1Pai=&email1Responsavel=&complemento=&email=&cidade=Abreu e Lima&endereco=Avenida das Ceroulas&etnia=Preta&naturalCidade=null&naturalEstado=0&naturalPais=undefined&nome=Gustavo Silva&nomeMae=&nomePai=&nomeResponsavel=&numero=10&rg=&telefoneCelularResponsavel=&telefoneComercialResponsavel=&telefoneResidencialResponsavel=&telefoneCelularMae=&telefoneComercialMae=&telefoneResidencialMae=&telefoneCelularPai=&telefoneResidencialPai=&telefoneComercialPai=&necessidadeEspecial=&sexo=&numeroEol=&numeroRA=&celular=&cpf=&observacao=undefined");
			}
}
