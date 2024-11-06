package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IProducts;
import com.spring.jwt.dto.ProductsDTO;
import com.spring.jwt.entity.ClothingType;
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


    @Override
    public ProductsDTO saveInformation(ProductsDTO productsDTO) {

        if (productsDTO.getStockQuantities() == null || productsDTO.getStockQuantities().isEmpty()) {
            throw new IllegalArgumentException("Stock quantities must be provided.");
        }

        Products product = modelMapper.map(productsDTO, Products.class);
        product.setStockQuantities(productsDTO.getStockQuantities());

        // Calculate total stock quantity using a for-each loop
        int totalStockQuantity = 0;
        for (Integer quantity : productsDTO.getStockQuantities()) {
            totalStockQuantity += quantity; // Assuming stock quantities are integers
        }

        Products savedProduct = productsRepository.save(product);
        return modelMapper.map(savedProduct, ProductsDTO.class);
    }



    @Override
    public ProductsDTO updateAny(UUID id, ProductsDTO productsDTO) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

        // Update fields conditionally based on non-null values in ProductsDTO
        if (productsDTO.getProductName() != null) {
            product.setProductName(productsDTO.getProductName());
        }
        if (productsDTO.getDescription() != null) {
            product.setDescription(productsDTO.getDescription());
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
        if (productsDTO.getClothingType() != null) {
            product.setClothingType(productsDTO.getClothingType());
        }
        if (productsDTO.getStockQuantities() != null) {
            product.setStockQuantities(productsDTO.getStockQuantities());
        }

        Products updatedProduct = productsRepository.save(product);
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

    @Override
    public List<ProductsDTO> getProductsByClothingType(String clothingType) {
        try {
            ClothingType type = ClothingType.valueOf(clothingType.toUpperCase());
            List<Products> products = productsRepository.findByClothingType(type);
            List<ProductsDTO> productsDTOList = new ArrayList<>();

            for (Products product : products) {
                productsDTOList.add(modelMapper.map(product, ProductsDTO.class));
            }
            return productsDTOList;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid clothing type: " + clothingType);
        }
    }

    @Override
    public Integer getTotalStockQuantity() {
        List<Products> allProducts = productsRepository.findAll();
        int totalStock = 0;

        for (Products product : allProducts) {
            for (Integer quantity : product.getStockQuantities()) {
                totalStock += quantity;
            }
        }
        return totalStock;
    }

    @Override
    public Integer getStockQuantityByProductId(UUID productId) {
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        int stockQuantity = 0;
        for (Integer quantity : product.getStockQuantities()) {
            stockQuantity += quantity;
        }
        return stockQuantity;
    }
}
