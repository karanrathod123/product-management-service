package com.hcl.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT * from productservice.product t1 WHERE t1.product_version = (SELECT max(product_version) FROM productservice.product t2 WHERE t2.product_id = t1.product_id)", nativeQuery = true)
	public List<Product> getAllProduct();

	@Query(value = "select * from product where product_id =:productId group by product_id", nativeQuery = true)
	public Product findLatestProduct(Long productId);

	public List<Product> findByProductId(Long productId);

}
