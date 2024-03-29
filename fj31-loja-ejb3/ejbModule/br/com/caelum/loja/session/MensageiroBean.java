package br.com.caelum.loja.session;

import java.io.StringWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.util.Livros;


@Stateless
@Local(Mensageiro.class)
public class MensageiroBean implements Mensageiro{

	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory fabrica;
	
	@Resource(mappedName="java:/queue/loja")
	private Destination destination;
	
	@Override
	public void enviaMensage(List<Livro> livros) {
		try {
			Connection conexao = this.fabrica.createConnection("jms", "caelum");
			Session sessao = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Livros wrapper = new Livros();
			wrapper.adicionaLivros(livros);
			
			Marshaller marshaller = JAXBContext.newInstance(Livros.class).createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(wrapper, stringWriter);
			
			TextMessage message = sessao.createTextMessage(stringWriter.toString());
			
			MessageProducer produtor = sessao.createProducer(this.destination);
			produtor.send(message);
			produtor.close();
			conexao.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
	}

}
