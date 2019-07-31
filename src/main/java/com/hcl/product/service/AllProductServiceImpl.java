package com.hcl.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.repository.ProductRepository;

@Service
public class AllProductServiceImpl implements AllProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ResponseData getAllProduct(Long productId) {

		ResponseData response = new ResponseData();
		List<Product> productList = productRepository.findByProductId(productId);
		if (ObjectUtils.isEmpty(productList)) {
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			response.setMessage("Incorrect Product ID");
			return response;
		}
		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("The list of Product");
		response.setData(productList);

		return response;
	}

}
