package com.hcl.product.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.hcl.product.service.ReadDataService;

@Component
public class Consumer {

	@Autowired
	private ReadDataService readDataServiceImpl;

	@JmsListener(destination = "product.queue", containerFactory = "myFactory")
	public void receiveMessage(final ProductEvent event) {
		readDataServiceImpl.saveDatetoDatabase(event.getProductList());
	}

}
