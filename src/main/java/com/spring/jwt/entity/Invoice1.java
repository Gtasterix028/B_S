package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.jwt.entity.Customers;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class Invoice1 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID invoice1ID;

    private LocalDate invoice1Date;
    private LocalDate invoice1DueDate;
    private Boolean submit;

    private Double sellQuantity;

    private UUID productID;
    private String productName;
    private Double unitPrice;
    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;
    private ClothingType clothingType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Products product; // Change the field type to Products

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customers customer;
}

