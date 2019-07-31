package com.hcl.product.service;

import java.util.List;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;

interface AllProductService {

	public ResponseData getAllProduct(Long productId);

}
