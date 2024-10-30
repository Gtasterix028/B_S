package com.spring.jwt.dto;


import com.spring.jwt.Enum.ProductType;
import com.spring.jwt.entity.InvoicesDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.UUID;

    @Data
    public class ProductsDTO {

        private UUID productID;
        private String productName;
        private String description;
        private Double price;
        private Double actualPrice;
        private Double sellingPrice;
        private Double discount;
        private ProductType productType;

//        private List<InvoicesDetailsDTO> invoicesDetails;
        private List<Integer> stockQuantities;

    }
