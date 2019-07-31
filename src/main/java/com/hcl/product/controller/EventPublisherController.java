package com.hcl.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.service.PublisherService;

@RestController
@RequestMapping("/product")
public class EventPublisherController {

	@Autowired
	private PublisherService publisherServiceImpl;

	@PostMapping("/publish")
	public ResponseEntity<Object> publishEvent(@RequestBody List<Product> products) {
		ResponseData response = publisherServiceImpl.publishEvent(products);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}
}
