package com.hcl.product.service;

import java.text.DecimalFormat;

public class Test {

	public static void main(String[] args) {
		
		double value = 12.3457652133;
				  value =Double.parseDouble(new DecimalFormat("#.#").format(value));
				  System.out.println(value);
				System.out.println(Math.floor(12.3457652133));  
		// TODO Auto-generated method stub

	}

}
