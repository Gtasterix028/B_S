package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

   @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoiceId", referencedColumnName = "invoiceId", nullable = false)
    private Invoices invoices;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private PaymentStatus paymentStatuses;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private Customers customers;



}
