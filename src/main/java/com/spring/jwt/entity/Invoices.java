package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity

public class Invoices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerID", nullable = false)
    private Customers customer;

    @OneToMany(mappedBy = "invoices", cascade = CascadeType.ALL)
    private List<ShippingDetail> shippingDetails;

}
