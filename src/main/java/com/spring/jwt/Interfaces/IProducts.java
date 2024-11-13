package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.ProductsDTO;
import com.spring.jwt.entity.Products;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface IProducts {


    ProductsDTO getProductByID(UUID id);

    List<ProductsDTO> getAllProducts();

    ProductsDTO saveInformation(ProductsDTO productsDTO);

    ProductsDTO updateAny(UUID id, ProductsDTO productsDTO);

    void deleteProduct(UUID id);

    List<ProductsDTO> getProducts(UUID productId, String productName, String description, Double price);

    List<ProductsDTO> searchProductsByName(String name);

    List<ProductsDTO> getProductsByClothingType(String clothingType);

    Integer getTotalStockQuantity();

    Integer getStockQuantityByProductId(UUID productId);

    List<ProductsDTO> saveProduct(List<ProductsDTO> productsDTOList);

    List<ProductsDTO> getProductsByFilter(String clothingType, String sortBy, String order);

    List<ProductsDTO> getProductsByFilterstock(String order);

//    List<ProductsDTO> getProductsByFilterstock(Integer stockQuantities);
}


