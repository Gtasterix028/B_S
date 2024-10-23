package com.spring.jwt.entity;

import com.spring.jwt.dto.ShippingDto;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class ShippingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shippingDetailId;

    @Column(nullable = false)
    private String shippingAddress;

//    @Column(nullable = false)
    public LocalDate shippingDate;

//    @Column(nullable = false)
    public LocalDate estimatedArrivalDate;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoices invoices;



}

