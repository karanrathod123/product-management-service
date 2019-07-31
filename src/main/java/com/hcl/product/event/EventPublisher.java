package com.hcl.product.event;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String queueName, final ProductEvent event) {
		
		System.out.println("Sending message " + event + "to queue - " + queueName);
		jmsTemplate.send(queueName, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage objectMessage = session.createObjectMessage();
				objectMessage.setObject(event);
				return objectMessage;
			}
		});
	}

}
