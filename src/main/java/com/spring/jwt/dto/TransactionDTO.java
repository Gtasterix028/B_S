package com.spring.jwt.dto;

import com.spring.jwt.entity.ClothingType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class TransactionDTO {
    private UUID invoice1ID;
    private LocalDate invoice1Date;

    private String paymentMethod;

    private Double grandTotal;

    private String customerName;

}
