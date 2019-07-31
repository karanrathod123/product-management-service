package com.hcl.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ReadFileController {
	
	@GetMapping("/read/{filePath}")
	public ResponseEntity<?> readData(@PathVariable("filePath") String filePath){
		return null;
	}

}
