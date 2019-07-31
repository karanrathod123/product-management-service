package com.hcl.product.event;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hcl.product.entity.Product;

import lombok.Data;

@Data
public class ProductEvent implements Serializable {

	private static final long serialVersionUID = -2182815586359587829L;
	
	private List<Product> productList = new ArrayList<>();

}
