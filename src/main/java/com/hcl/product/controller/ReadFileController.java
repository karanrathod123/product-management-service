package com.hcl.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.service.ReadDataService;

@RestController
@RequestMapping("/product")
public class ReadFileController {
	
	@Autowired
	private ReadDataService readDataService;
	
	@GetMapping("/read/{fileName}")
	public ResponseEntity<Object> readData(@PathVariable("fileName") String filePath){
		ResponseData responseData = readDataService.readData(filePath);
		return new ResponseEntity<>(responseData.getData(), responseData.getHttpStatus());
	}

}
