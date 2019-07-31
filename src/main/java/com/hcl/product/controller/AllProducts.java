package com.hcl.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.product.service.AllProductServiceImpl;

@RestController
public class AllProducts {

	@Autowired
	private AllProductServiceImpl allProductServiceImpl;
	
	@GetMapping("/product/getall")
	public ResponseEntity<Object> getAllProduct(Long productId)
	{
		return new ResponseEntity<>(allProductServiceImpl.getAllProduct(productId),HttpStatus.OK);
	}
	
}
