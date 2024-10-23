package com.spring.jwt.dto;

import com.spring.jwt.entity.Invoices;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
@Data

public class ShippingDto {

        private int shippingDetailId;
        private int invoicedId;
        private String shippingAddress;
        private LocalDate shippingDate;
        private LocalDate estimatedArrivalDate;



}
