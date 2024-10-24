package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class InvoicesDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceDetailID;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productID", nullable = false)
    private Products products; //

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiceID", nullable = false)
    private Invoices invoice;
}