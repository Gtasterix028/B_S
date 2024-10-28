package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.ProductsDTO;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IProducts {
    ProductsDTO getProductByID(Integer id);

    List<ProductsDTO> getAllProducts();

    ProductsDTO saveInformation(ProductsDTO productsDTO);

    ProductsDTO updateAny(Integer id, ProductsDTO productsDTO);

    void deleteProduct(Integer id);

    List<ProductsDTO> getProducts(Integer productId, String productName, String description, Double price);

    List<ProductsDTO> searchProductsByName(String name);
}
