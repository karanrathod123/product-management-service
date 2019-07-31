package com.hcl.product.service;

import java.util.List;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.exception.InvalidInputException;

public interface ReadDataService {
	
	public ResponseData readData(String filePath) throws InvalidInputException;
	
	public List<Product> saveDatetoDatabase(List<Product> productList);

}
