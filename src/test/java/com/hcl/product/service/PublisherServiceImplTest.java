package com.hcl.product.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.event.EventPublisher;

@RunWith(MockitoJUnitRunner.class)
public class PublisherServiceImplTest {

	@InjectMocks
	private PublisherServiceImpl publisherServiceImpl;

	@Mock
	private EventPublisher eventPublisher;

	private List<Product> products = new ArrayList<Product>();

	@Before
	public void setUp() {

		Product product = new Product();
		products.add(product);

	}

	@Test
	public void testPublishEventForPositive() {
		doNothing().when(eventPublisher).sendMessage(Mockito.anyString(), Mockito.any());
		ResponseData response = publisherServiceImpl.publishEvent(products);
		assertNotNull(response);
	}
	
	@Test
	public void testPublishEventForNegative() {
		ResponseData response = publisherServiceImpl.publishEvent(new ArrayList<Product>());
		assertNotNull(response);
	}
}
