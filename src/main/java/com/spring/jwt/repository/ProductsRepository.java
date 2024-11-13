package com.spring.jwt.repository;

import com.spring.jwt.entity.ClothingType;
import com.spring.jwt.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Products,UUID> {
    List<Products> findByProductName(String productName);
    List<Products> findByDescription(String description);
    List<Products> findByProductNameContainingIgnoreCaseOrderByProductNameAsc(String name);
    List<Products> findByClothingType(ClothingType type);

    @Query("SELECT p FROM Products p JOIN p.stockQuantities sq GROUP BY p.id ORDER BY SUM(sq) ASC")
    List<Products> findProductsOrderByTotalStockQuantityAsc();

    @Query("SELECT p FROM Products p JOIN p.stockQuantities sq GROUP BY p.id ORDER BY SUM(sq) DESC")
    List<Products> findProductsOrderByTotalStockQuantityDesc();

    @Query("SELECT p FROM Products p LEFT JOIN p.stockQuantities sq GROUP BY p ORDER BY p.clothingType ASC, SUM(sq) ASC")
    List<Products> findByOrderByClothingTypeAscAndTotalStockQuantityAsc();


    @Query("SELECT p FROM Products p LEFT JOIN p.stockQuantities sq GROUP BY p ORDER BY p.clothingType DESC, SUM(sq) DESC")
    List<Products> findByOrderByClothingTypeDescAndTotalStockQuantityDesc();


   


    List<Products> findByClothingTypeOrderByProductNameAsc(ClothingType clothingType);


    List<Products> findByClothingTypeOrderByProductNameDesc(ClothingType clothingType);


    List<Products> findByClothingTypeOrderByStockQuantitiesAsc(ClothingType clothingType);


    List<Products> findByClothingTypeOrderByStockQuantitiesDesc(ClothingType clothingType);

    List<Products> findAllByOrderByStockQuantitiesAsc();

    List<Products> findAllByOrderByStockQuantitiesDesc();
}
