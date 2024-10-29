package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Invoices> invoicesList;

    @OneToOne(mappedBy = "customer")
    private Payment payment;

    // One customer can have many invoices
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Invoice1> invoices1List;


}