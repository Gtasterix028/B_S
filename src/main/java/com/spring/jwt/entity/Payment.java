package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentID;

    private LocalDate paymentDate;
    private String paymentMethod;
    private Double amount;

//    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
//    private PaymentStatus paymentStatus;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "invoiceID", nullable = false)
//    private Invoices invoice;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "customerID", nullable = false)
//    private Customers customer;
}
