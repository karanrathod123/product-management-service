package com.hcl.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.product.dto.ResponseData;

import com.hcl.product.repository.ProductRepository;

@Service
public class AllProductServiceImpl implements AllProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseData getAllProduct(Long productId) {

		ResponseData response = new ResponseData();

		productRepository.findByProductId(productId);

		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("The list of Product");
		response.setData(productRepository.findByProductId(productId));

		return response;
	}

}
