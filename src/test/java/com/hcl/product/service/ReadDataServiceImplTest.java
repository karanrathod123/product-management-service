package com.hcl.product.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

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
		assertNotNull(readDataServiceImpl.readData("Data.xlsx"));
	}

	@Test
	public void testReadDataWhenManufactureIsDiff() throws InvalidInputException {
		product.setManufacturedBy("Piyush");
		product.setQuantity(50);
		when(productRepository.findLatestProduct(Long.valueOf(1))).thenReturn(product);
		assertNotNull(readDataServiceImpl.readData("Data.xlsx"));
	}

	@Test
	public void testReadDataWhenQuantityIsDiff() throws InvalidInputException {
		product.setManufacturedBy("Piyush");
		product.setQuantity(300);
		when(productRepository.findLatestProduct(Long.valueOf(1))).thenReturn(product);
		assertNotNull(readDataServiceImpl.readData("Data.xlsx"));
	}

	/*
	 * @Test(expected = InvalidInputException.class) public void
	 * testReadDataWhenInvalidInput() throws InvalidInputException {
	 * readDataServiceImpl.readData("abc"); }
	 */

}
