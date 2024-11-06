package com.spring.jwt.dto;

import com.spring.jwt.entity.Invoices;
import com.spring.jwt.entity.Products;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
public class InvoicesDetailsDTO {
    private UUID invoiceDetailID;
    private Integer Quantity;

    private InvoiceDTO invoice;
    private ProductsDTO product;


}