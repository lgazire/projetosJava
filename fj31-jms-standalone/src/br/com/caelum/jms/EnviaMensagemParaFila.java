package br.com.caelum.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviaMensagemParaFila {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NamingException, JMSException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "jms");
		jndiProperties.put(Context.SECURITY_CREDENTIALS	, "caelum");
		jndiProperties.put("java.naming.factory.initial", "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put("java.naming.provider.url", "remote://localhost:4447");
		InitialContext ic = new InitialContext(jndiProperties);
		QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)ic.lookup("jms/RemoteConnectionFactory");
		QueueConnection queueConnection = queueConnectionFactory.createQueueConnection("jms", "caelum");
		
		QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		TextMessage textMessage = queueSession.createTextMessage();
		textMessage.setText("Mensagem de texto para uma queue");
		
		Queue queue = (Queue)ic.lookup("jms/queue/loja");
		QueueSender queueSender = queueSession.createSender(queue);
		queueSender.send(textMessage);
		queueConnection.close();

	}

}
