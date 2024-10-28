package com.spring.jwt.repository;

import com.spring.jwt.dto.ProductsDTO;
import com.spring.jwt.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {
    List<Products> findByProductName(String productName);
    List<Products> findByDescription(String description);
    List<Products> findByPrice(Double price);
    List<Products> findByProductNameContainingIgnoreCaseOrderByProductNameAsc(String name);
}
