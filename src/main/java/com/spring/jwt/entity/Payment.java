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

    @OneToOne
    @JoinColumn(name = "invoiceID", referencedColumnName = "invoiceId", nullable = false)
    private Invoices invoices;
}
