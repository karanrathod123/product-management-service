package com.hcl.product.event;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.product.entity.Product;
import com.hcl.product.service.ReadDataService;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerTest {
	
	@Mock
	private ReadDataService readDataServiceImpl;

	@InjectMocks
	private Consumer consumer;
	
	List<Product> productList;
	Product product;
	ProductEvent event;
	
	@Before
	public void setUp() throws Exception {
		product = new Product();
		product.setId(1L);
		product.setManufacturedBy("TATA");
		product.setProductId(100L);
		product.setProductName("Rolls Royce");
		product.setProductVersion(1.1);
		product.setQuantity(1);
		
		productList = new ArrayList<>();
		productList.add(product);
		
		event = new ProductEvent();
	}

	@Test
	public void testReceiveMessage() throws JMSException {
		consumer.receiveMessage(event);
		assertTrue(true);
	}

}
