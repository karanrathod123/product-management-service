package com.hcl.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.product.dto.ResponseData;
import com.hcl.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
//public final static String GET_ALL_PRODUCT =	"SELECT * from productservice.product t1 WHERE t1.product_version = (SELECT max(product_version) FROM productservice.product t2 WHERE t2.product_id = t1.product_id)" ; 
	
	@Query(value = "SELECT * from productservice.product t1 WHERE t1.product_version = (SELECT max(product_version) FROM productservice.product t2 WHERE t2.product_id = t1.product_id)",nativeQuery = true)
	public List<Product> getAllProduct();

	
	
}
