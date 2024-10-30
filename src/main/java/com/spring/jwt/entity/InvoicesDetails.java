package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class InvoicesDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID invoiceDetailID;

    private Integer quantity;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "productID", nullable = false)
//    private Products products;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "invoiceID", nullable = false)
//    private Invoices invoice;
}