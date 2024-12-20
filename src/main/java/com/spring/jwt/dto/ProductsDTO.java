package com.spring.jwt.dto;

import com.spring.jwt.entity.ClothingType;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class ProductsDTO {

    private UUID productID;
    private String productName;
    private String description;
    private Double actualPrice;
    private Double discount;
    private Double sellingPrice;

    private String size;
    private String color;

    private ClothingType clothingType;

    private List<Double>stockQuantities;

}

