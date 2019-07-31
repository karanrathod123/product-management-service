package com.hcl.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.exception.InvalidInputException;
import com.hcl.product.service.AllProductServiceImpl;
import com.hcl.product.service.ProductService;
import com.hcl.product.service.PublisherService;
import com.hcl.product.service.ReadDataService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	private ReadDataService readDataService;

	@Autowired
	private PublisherService publisherServiceImpl;

	@Autowired
	private AllProductServiceImpl allProductServiceImpl;

	@GetMapping("/latest")
	public ResponseEntity<Object> getAllProduct() {
		List<Product> productList = productService.getAllProduct();
		if (productList.isEmpty()) {
			return new ResponseEntity<>("No Products Available", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);

	}

	@GetMapping("/read/{fileName}")
	public ResponseEntity<Object> readData(@PathVariable("fileName") String filePath) throws InvalidInputException {
		ResponseData responseData = readDataService.readData(filePath);
		return new ResponseEntity<>(responseData.getData(), responseData.getHttpStatus());
	}

	@PostMapping("/publish")
	public ResponseEntity<Object> publishEvent(@RequestBody List<Product> products) {
		ResponseData response = publisherServiceImpl.publishEvent(products);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	@GetMapping()
	public ResponseEntity<Object> getAllProduct(Long productId) {
		ResponseData response = allProductServiceImpl.getAllProduct(productId);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

}
