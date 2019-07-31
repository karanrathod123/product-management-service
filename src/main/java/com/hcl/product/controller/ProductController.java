package com.hcl.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.product.entity.Product;
import com.hcl.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/getall")
	public ResponseEntity<Object> getAllProduct() {
		List<Product> productList=productService.getAllProduct();
		return new ResponseEntity<>(productList, HttpStatus.OK);

	}
}
