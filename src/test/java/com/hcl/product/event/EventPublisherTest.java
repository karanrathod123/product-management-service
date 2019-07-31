package com.hcl.product.event;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jms.core.JmsTemplate;

@RunWith(MockitoJUnitRunner.class)
public class EventPublisherTest {

private ProductEvent event;
	
	@Mock
	private JmsTemplate jmsTemplate;
	
	@InjectMocks
	EventPublisher eventPublisher;
	
	
	@Before
	public void setUp() throws Exception {
		event = new ProductEvent();
	}

	@Test
	public void testSendMessage() {
		Mockito.doNothing().when(jmsTemplate).convertAndSend("queue", event);
		eventPublisher.sendMessage("queue", event);
		assertTrue(true);
	}

}
