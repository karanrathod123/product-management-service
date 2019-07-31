package com.hcl.product.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(final String queueName, final ProductEvent event) {
		jmsTemplate.convertAndSend(queueName, event);
	}

}
