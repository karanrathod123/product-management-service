package com.hcl.product.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

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
import com.hcl.product.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.class)
public class AllProductServiceImplTest {

	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	AllProductServiceImpl allProductServiceImpl;
	
	Product product1=new Product();
	Product product=new Product();
	List<Product> productlist=new ArrayList<>();
	
	@Before
	public void setUp()
	{
		
		
		product1.setManufacturedBy("hcl");
        product1.setProductId(101l);	
        product1.setProductName("laptop");
        product1.setProductVersion(1.0);
        product1.setQuantity(20);
        
        product.setManufacturedBy("intel");
        product.setProductId(102l);	
        product.setProductName("processor");
        product.setProductVersion(2.0);
        product.setQuantity(10);
        
        productlist.add(product);
        productlist.add(product1);
        
	}
	
	@Test
	public void getAllProductTest()
	{
		Mockito.when(productRepository.findByProductId(product.getProductId())).thenReturn(productlist);
		ResponseData response= allProductServiceImpl.getAllProduct(product.getProductId());
		assertNotNull(response);
	}
	
}
