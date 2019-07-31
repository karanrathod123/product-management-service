package com.hcl.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.exception.InvalidInputException;
import com.hcl.product.repository.ProductRepository;

@Service
public class AllProductServiceImpl implements AllProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseData getAllProduct(Long productId) {

		ResponseData response = new ResponseData();
		Product p = new Product();
		List<Product> list = new ArrayList<Product>();

	
		
		list = productRepository.findByProductId(productId);

		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("The list of Product");
		response.setData(list);

		return response;
	}

}
