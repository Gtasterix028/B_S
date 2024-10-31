package com.spring.jwt.dto;

import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.InvoicesDetails;
import com.spring.jwt.entity.Payment;
import com.spring.jwt.entity.ShippingDetail;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class InvoiceDTO {

    private UUID invoiceId;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Double total;

    private List<InvoicesDetailsDTO> invoicesDetails;
    private CustomersDTO customers;


//    private List<ShippingDto> shippingDetails;
//    private List<Payment> payment;

}
