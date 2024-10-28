package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PaymentStatus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentStatusID;

    @Column(name = "PaymentID", nullable = false) // Ensure it's not null if required
    private Integer paymentID;

    @Column(name = "Status", nullable = false) // Ensure status is required
    private String status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Adjust cascade as needed
   @JoinColumn(name = "payment_ID", nullable = false)
    private Payment payment; // Assuming Payment is another entity

}
