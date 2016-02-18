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
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/ProfessorFuncionario");
			webResource.post("id=0&action=create&foto_professor_funcionario=1&ativo=S&dataEntradaEscola=2010-10-10&dataEntradaPrefeitura=2010-10-10&nome=Ewerton+Donizet&naturalidadePais=Brasil&naturalidadeEstado=AC&rua=Rua+Capit%C3%A3o+Rezende+da+Costa%2C+15&bairro=Centro&numero=1&complemento=ccc&cep=23232-323&estado=AC&cidade=Acrel%C3%A2ndia&observacao=Pega+a+metralhadora+e+tra+rarARAW+DSR+EDGFJSRCGFHVJ+BSD");
			}
	
}
