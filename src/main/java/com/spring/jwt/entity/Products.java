package com.spring.jwt.entity;

import com.spring.jwt.Enum.ProductType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer productID;
    private String productName;
    private String description;
    private Double price;
    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING) // Using enum for product type
    private ProductType productType;// NEW FIELD: indicates if the product is readymade or unstitched

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoicesDetails> invoicesDetails;
}