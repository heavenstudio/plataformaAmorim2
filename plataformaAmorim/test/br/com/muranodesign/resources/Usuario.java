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
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Chamada/ChamadaGrupo/");
			webResource.post("stringfiedJson=%7B%22dataDia%22%3A%2228%22%2C%22dataMes%22%3A1%2C%22listaFaltas%22%3A%5B%7B%22alunoId%22%3A%221058%22%2C%22faltas%22%3A%5B%221%22%2C%220%22%2C%221%22%2C%221%22%2C%221%22%2C%220%22%2C%221%22%5D%7D%2C%7B%22alunoId%22%3A%221001%22%2C%22faltas%22%3A%5B%221%22%2C%221%22%2C%221%22%2C%221%22%2C%220%22%2C%221%22%2C%221%22%5D%7D%5D%7D");
			}
	
}
