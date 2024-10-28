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
import java.util.Optional;

@Service
public class ProductsService implements IProducts {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductsDTO getProductByID(Integer id) {
        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));
        return modelMapper.map(product, ProductsDTO.class);
    }

    @Override
    public ProductsDTO saveInformation(ProductsDTO productsDTO) {

        Products product = modelMapper.map(productsDTO, Products.class);
        Products savedProduct = productsRepository.save(product);

        return modelMapper.map(savedProduct, ProductsDTO.class);
    }

    @Override
    public ProductsDTO updateAny(Integer id, ProductsDTO productsDTO) {
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


        Products updatedProduct = productsRepository.save(product);
        return modelMapper.map(updatedProduct, ProductsDTO.class);
    }
    //    List<QualificationDTO> qualificationDTOS = applicationDTO.getQualifications();
//            if (qualificationDTOS != null) {
//        for (QualificationDTO qualificationDTO : qualificationDTOS) {
//            Qualification qualification = QualificationMapper.toQualification(qualificationDTO);
//
//            Optional<Qualification> existingQualification = qualificationRepository.findByApplicationAndStandardAndUniversityAndPassingYearAndPercentage(
//                    savedApplication, qualification.getStandard(), qualification.getUniversity(),
//                    qualification.getPassingYear(), qualification.getPercentage());
//
//            // Check if the qualification already exists
//            if (existingQualification.isEmpty()) {
//                qualification.setApplication(savedApplication);
//                qualificationRepository.save(qualification);


    @Override
    public void deleteProduct(Integer id) {
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
    public List<ProductsDTO> getProducts(Integer productId, String productName, String description, Double price) {
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

        // Convert to DTO
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        for (Products product : foundProducts) {
            ProductsDTO dto = modelMapper.map(product, ProductsDTO.class);
            productsDTOList.add(dto);
        }

        return productsDTOList;
    }


}
