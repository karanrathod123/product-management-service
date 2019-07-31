package com.hcl.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	//@Query(value = "select MAX(p.product_version) from Product p Where p.product_Id=:productId", nativeQuery = true)
	@Query(value = "select * from product where product_id =:productId group by product_id", nativeQuery = true)
	public Product findByProductId(Long productId);
	
}
