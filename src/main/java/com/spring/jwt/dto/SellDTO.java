package com.spring.jwt.dto;

import com.spring.jwt.entity.Customers;

import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Products;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class SellDTO {

    private UUID sellingId;
    private LocalDateTime date;
    private Double productSellQuantity;
    private UUID productIdl;
    private Double productSubtotal;
    private Double totalAmount;


    private Invoice1DTO invoice1;



}
