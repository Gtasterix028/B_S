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

    @Column(name = "PaymentID")
    private Integer paymentID;

    @Column(name = "Status")
    private String status;
}
