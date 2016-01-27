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
		WebResource webResource = client().resource("http://localhost:8888/plataformaAmorim/Blog/");
		webResource.post("action=create&id=&oficina=38&agrupamento=35&titulo=Titulo+TEste+LALALALALALALALAL&Descricao=Os+filmes+%22Dheepan%22%2C+de+Jacques+Audiard%2C+%22Fatima%22%2C+de+Philippe+Faucon%2C+%22Cinco+gra%C3%A7as%22%2C+de+Dniz+Gamze+Erg%C3%BCven%2C+%22La+loi+du+march%C3%A9%22%2C+de+St%C3%A9phane+Briz%C3%A9%2C+e+%22Marguerite%22%2C+de+Xavier+Giannoli%2C+est%C3%A3o+entre+os+longas-metragens+indicados+ao+C%C3%A9sar%2C+a+grande+premia%C3%A7%C3%A3o+do+cinema+franc%C3%AAs.%0D%0AO+an%C3%BAncio+dos+concorrentes+aconteceu+nesta+quarta-feira+(27)%2C+em+Paris.%0D%0A%22Dheepan%22%2C+que+conta+a+odisseia+de+tr%C3%AAs+refugiados+cingaleses+na+Fran%C3%A7a%2C+recebeu+a+Palma+de+Ouro+em+2015+no+Festival+de+Cannes%2C+enquanto+%22Cinco+gra%C3%A7as%22%2C+uma+ode+%C3%A0+liberdade+da+diretora+franco-turca+Deniz+Gamze+Erguven%2C+foi+escolhido+como+o+representante+da+Fran%C3%A7a+e+indicado+ao+Oscar+de+filme+em+l%C3%ADngua+estrangeiro.");
	}
	
}
