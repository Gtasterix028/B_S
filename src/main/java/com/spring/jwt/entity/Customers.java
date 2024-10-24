package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Invoices> invoicesList;

    @OneToOne(mappedBy = "customer")
    private Payment payment;
}