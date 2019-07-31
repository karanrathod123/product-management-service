package com.hcl.product.service;

import java.util.List;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;

public interface PublisherService {

	public ResponseData publishEvent(List<Product> products);
}
