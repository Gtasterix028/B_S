package com.spring.jwt.dto;

import com.spring.jwt.entity.Invoices;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data

public class ShippingDto {

        private int shippingDetailId;
        private int invoicedId;
        private String shippingAddress;
        private LocalDate shippingDate;
        private LocalDate estimatedArrivalDAte;

        private InvoiceDTO invoiceDTO;



}
