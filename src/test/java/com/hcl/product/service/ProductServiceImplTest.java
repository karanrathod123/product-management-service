package com.hcl.product.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.product.entity.Product;
import com.hcl.product.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@Mock
	ProductRepository productRepository;

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	List<Product> productList;
	Product product;

	@Before
	public void setup() {
		productList = new ArrayList<>();
		product = new Product();
		product.setId(1l);
		product.setManufacturedBy("Birla");
		product.setProductId(1l);
		product.setProductName("Iron");
		product.setProductVersion(1.2);
		product.setQuantity(15);
		productList.add(product);
	}

	@Test
	public void getAllProduct() {
		Mockito.when(productRepository.getAllProduct()).thenReturn(productList);
		List<Product> products = productServiceImpl.getAllProduct();
		assertNotNull(products);
	}
}