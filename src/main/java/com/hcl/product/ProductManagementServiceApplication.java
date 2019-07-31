package com.hcl.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ProductManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementServiceApplication.class, args);
	}

}
