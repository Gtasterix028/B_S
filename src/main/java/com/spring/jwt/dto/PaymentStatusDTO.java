package com.spring.jwt.dto;

import com.spring.jwt.entity.Payment;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStatusDTO {
    private Integer paymentStatusID;
    private Integer paymentID;
    private String status;
    private PaymentDTO paymentDTO;
}

