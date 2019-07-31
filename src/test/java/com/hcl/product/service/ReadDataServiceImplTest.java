package com.hcl.product.service;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.product.entity.Product;
import com.hcl.product.exception.InvalidInputException;
import com.hcl.product.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ReadDataServiceImplTest {
	
	@InjectMocks
	private ReadDataServiceImpl readDataServiceImpl;
	
	@Mock
	private ProductRepository productRepository;
	
	private Product product;
	
	@Before
	public void setUp() {
		product = new Product();
		product.setManufacturedBy("IBM");
		product.setQuantity(100);
		product.setProductVersion(1.0);
	}
	
	@Test
	public void testReadData() throws InvalidInputException {
		when(productRepository.findLatestProduct(Long.valueOf(1))).thenReturn(product);
		Assert.assertNotNull(readDataServiceImpl.readData("Data.xlsx"));
	}

}
