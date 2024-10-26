package com.spring.jwt.entity;

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


    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoicesDetails> invoicesDetails;
}