package com.hcl.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;
import com.hcl.product.event.EventPublisher;
import com.hcl.product.event.ProductEvent;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private EventPublisher eventPublisher;

	@Override
	public ResponseData publishEvent(List<Product> products) {
		ResponseData response = new ResponseData();
		if (ObjectUtils.isEmpty(products) || products.size() == 0) {
			response.setHttpStatus(HttpStatus.BAD_REQUEST);
			response.setMessage("Event should not be empty");
			return response;
		}

		ProductEvent event = new ProductEvent();
		event.setProductList(products);
		eventPublisher.sendMessage("product.queue", event);
		response.setHttpStatus(HttpStatus.OK);
		response.setMessage("Messege Send Successfully");
		response.setData(event);
		return response;
	}

}
