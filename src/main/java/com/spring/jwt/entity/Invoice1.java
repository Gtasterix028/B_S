package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.jwt.entity.Customers;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Invoice1 {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID invoice1ID;

    private LocalDate invoice1Date;
    private LocalDate invoice1DueDate;

    private Double sellQuantity;

    private UUID productID;
    private String productName;

    private Double discount;
    private Double sellingPrice;  // Price with Discount

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    private Double subTotalPrice;

    private Double grandTotal; // Total without discount

    private  String paymentMethod;

    private Double gst;

    private Double sGst;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference(value = "customer-invoice") // Jackson to get confused about which back-reference to use, leading to the error
    private Customers customer;

    @OneToMany(mappedBy = "invoice1", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "invoice-sell") // Named managed reference
    private List<Sell> sells;




}

