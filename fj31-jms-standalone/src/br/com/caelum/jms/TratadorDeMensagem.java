package br.com.caelum.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TratadorDeMensagem implements MessageListener{

	@Override
	public void onMessage(Message arg0) {
		TextMessage textMessage = (TextMessage)arg0;
		
		try {
			System.out.println("Tratador recebendo mensagem: " + textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
