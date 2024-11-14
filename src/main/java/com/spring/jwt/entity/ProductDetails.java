package com.spring.jwt.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
public class ProductDetails implements Serializable {

    private UUID productID;
    private String productName;
    private Double discount;
    private Double sellingPrice;
    private Double sellQuantity;
    private String size;
    private String color;

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    private Double subTotalPrice;
}