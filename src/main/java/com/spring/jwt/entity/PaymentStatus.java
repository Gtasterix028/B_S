package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentStatusID;

    @Column(name = "Status")
    private String status;

    // Add a reference to the Payment entity
    @OneToOne
    @JoinColumn(name = "paymentID", referencedColumnName = "paymentID", insertable = false, updatable = false)
    private Payment payment;

    // Optionally, you can keep this if you want to fetch paymentID
    @Column(name = "paymentID")
    private Integer paymentID; // This can be kept if you want a non-insertable version of paymentID
}