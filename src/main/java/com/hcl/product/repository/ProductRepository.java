package com.hcl.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
