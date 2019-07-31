package com.hcl.product.service;

import java.util.List;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;

public interface ReadDataService {
	
	public ResponseData readData(String filePath);
	
	public List<Product> saveDatetoDatabase(List<Product> productList);

}
