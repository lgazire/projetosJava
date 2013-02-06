package br.com.caelum.loja.message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.*;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/loja") })
public class Financeiro implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			String text = ((TextMessage)message).getText();
			System.out.println("Financeiro processando informações: " + text);
			
		} catch (JMSException e) {
			throw new EJBException(e);
		}
		
	}

}
