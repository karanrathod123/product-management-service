package com.hcl.product.event;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class Consumer {

	@JmsListener(destination = "product.queue")
	public String receiveMessage(final Message jsonMessage) throws JMSException {
		
		String messageData = null;
		System.out.println("Received message " + jsonMessage);
		String response = null;
		
		System.out.println(jsonMessage.toString());
		
		//TextMessage textMessage1 = (TextMessage)jsonMessage;
		//messageData = textMessage1.getText();
		//System.out.println(messageData);
		if(jsonMessage instanceof TextMessage) {
			System.out.println("inside loop");
			TextMessage textMessage = (TextMessage)jsonMessage;
			messageData = textMessage.getText();
			System.out.println("MessageData:");
			System.out.println(messageData);
			Map map = new Gson().fromJson(messageData, Map.class);
			response  = "Hello " + map.get("name");
			
			System.out.println(response);
			
		}
		return response;
		
	}
	
}
