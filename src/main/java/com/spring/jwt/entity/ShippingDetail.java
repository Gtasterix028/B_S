//package com.spring.jwt.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Data
//@Entity
//public class ShippingDetail {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer shippingDetailId;
//
//    @Column(nullable = false)
//    private String shippingAddress;
//
//    @Column(nullable = false)
//    private LocalDate shippingDate;
//
//    @Column(nullable = false)
//    private LocalDate estimatedArrivalDate;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "invoiceID", nullable = false)
//    private Invoice1 invoice;
//
//
//
//
//
//}
