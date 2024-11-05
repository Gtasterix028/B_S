package com.spring.jwt.dto;

import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data

public class PaymentDTO {
    private Integer paymentID;
    private Integer invoiceID;
    private LocalDate paymentDate;
    private String paymentMethod;
    private Double amount;

    private Invoice1DTO invoice1;

    private List<PaymentStatusDTO> paymentStatuses;

    private List<CustomersDTO> customers;
}
