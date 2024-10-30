package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IProducts;
import com.spring.jwt.dto.ProductsDTO;
import com.spring.jwt.entity.Products;
import com.spring.jwt.repository.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductsService implements IProducts {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductsDTO getProductByID(UUID id) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return modelMapper.map(product, ProductsDTO.class);
    }

//    @Override
//    public ProductsDTO saveInformation(ProductsDTO productsDTO) {
//        Products product = modelMapper.map(productsDTO, Products.class);
//
//        if (productsDTO.getStockQuantities() != null) {
//            product.setStockQuantities(productsDTO.getStockQuantities());
//        }
//        Products savedProduct = productsRepository.save(product);
//        return modelMapper.map(savedProduct, ProductsDTO.class);
//    }

    @Override
    public ProductsDTO saveInformation(ProductsDTO productsDTO) {
        // Check if stock quantities are provided in the DTO
        if (productsDTO.getStockQuantities() == null || productsDTO.getStockQuantities().isEmpty()) {
            throw new IllegalArgumentException("Stock quantities must be provided.");
        }

        // Map DTO to entity
        Products product = modelMapper.map(productsDTO, Products.class);

        // Set stock quantities
        product.setStockQuantities(productsDTO.getStockQuantities());

        // Save the product, which will also save the associated stock quantities
        Products savedProduct = productsRepository.save(product);

        // Return the saved product as a DTO
        return modelMapper.map(savedProduct, ProductsDTO.class);
    }


    @Override
    public ProductsDTO updateAny(UUID id, ProductsDTO productsDTO) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        if (productsDTO.getProductName() != null) {
            product.setProductName(productsDTO.getProductName());
        }
        if (productsDTO.getDescription() != null) {
            product.setDescription(productsDTO.getDescription());
        }


        if (productsDTO.getPrice() != null) {
            product.setPrice(productsDTO.getPrice());
        }
        if (productsDTO.getActualPrice() != null) {
            product.setActualPrice(productsDTO.getActualPrice());
        }
        if (productsDTO.getSellingPrice() != null) {
            product.setSellingPrice(productsDTO.getSellingPrice());
        }
        if (productsDTO.getDiscount() != null) {
            product.setDiscount(productsDTO.getDiscount());
        }
        if (productsDTO.getProductType() != null) {
            product.setProductType(productsDTO.getProductType());
        }
        if (productsDTO.getStockQuantities() != null && !productsDTO.getStockQuantities().isEmpty()) {
            // Clear existing stock quantities and add new ones
            product.getStockQuantities().clear();
            product.getStockQuantities().addAll(productsDTO.getStockQuantities());
        }

        Products updatedProduct = productsRepository.save(product);

        System.out.println("Updated Product: " + updatedProduct); // Log the updated product

        return modelMapper.map(updatedProduct, ProductsDTO.class);
    }

    @Override
    public void deleteProduct(UUID id) {
        Products existingProduct = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        productsRepository.delete(existingProduct);
    }

    @Override
    public List<ProductsDTO> getAllProducts() {
        List<Products> productsList = productsRepository.findAll();
        List<ProductsDTO> productsDTOList = new ArrayList<>();

        for (Products product : productsList) {
            productsDTOList.add(modelMapper.map(product, ProductsDTO.class));
        }
        return productsDTOList;
    }

    @Override
    public List<ProductsDTO> getProducts(UUID productId, String productName, String description, Double price) {
        if (productId != null) {
            List<Products> Products =productsRepository.findById(productId).map(List::of).orElse(List.of());
            List<ProductsDTO> productsDTOList=new ArrayList<>();
            for(Products products : Products)
            {
                productsDTOList.add(modelMapper.map(products,ProductsDTO.class));
            }
            return productsDTOList;
        } else if (productName != null) {
            List<Products> products= productsRepository.findByProductName(productName);
            List<ProductsDTO> productsDTOList=new ArrayList<>();
            for (Products  products1: products){
                productsDTOList.add(modelMapper.map(products1,ProductsDTO.class));
            }
            return productsDTOList;
        } else if (description != null) {
            List <Products> products= productsRepository.findByDescription(description);
            List<ProductsDTO> productsDTOList=new ArrayList<>();
            for(Products products2:products){
                productsDTOList.add(modelMapper.map(products2,ProductsDTO.class));
            }
            return productsDTOList;
        } else if (price != null) {
            List<Products> products= productsRepository.findByPrice(price);
            List<ProductsDTO> productsDTOList=new ArrayList<>();
            for(Products products3:products){
                productsDTOList.add(modelMapper.map(products3,ProductsDTO.class));
            }
            return productsDTOList;

        } else {
            List<Products> products = productsRepository.findAll();
            List<ProductsDTO> productsDTOList=new ArrayList<>();
            for(Products products4 : products){
                productsDTOList.add(modelMapper.map(products4,ProductsDTO.class));
            }
            return productsDTOList;}
    }
    @Override
    public List<ProductsDTO> searchProductsByName(String name) {
        List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(name);

        List<ProductsDTO> productsDTOList = new ArrayList<>();
        for (Products product : foundProducts) {
            ProductsDTO dto = modelMapper.map(product, ProductsDTO.class);
            productsDTOList.add(dto);
        }

        return productsDTOList;
    }
}
