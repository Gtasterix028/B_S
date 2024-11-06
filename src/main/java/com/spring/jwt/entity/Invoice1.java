package com.spring.jwt.entity;

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

    private Boolean submit;

    // Many invoices to one customer
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customer;

    // Many invoices to many products
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "invoice_products",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Products> products;



}