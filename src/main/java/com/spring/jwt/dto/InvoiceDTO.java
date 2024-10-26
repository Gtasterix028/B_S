package com.spring.jwt.dto;

import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.InvoicesDetails;
import com.spring.jwt.entity.Payment;
import com.spring.jwt.entity.ShippingDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InvoiceDTO {

    private Integer invoiceId;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double total;

    private List<Payment> payment;
    private List<InvoicesDetailsDTO> invoicesDetails;

    private CustomersDTO customers;
    private List<ShippingDto> shippingDetails;

}
