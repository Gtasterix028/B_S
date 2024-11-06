package com.spring.jwt.dto;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CustomersDTO {

    private UUID customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private List<Invoice1DTO> invoicesList; // List of Invoice DTOs



    private List<InvoiceDTO> invoiceDTOS;
    private PaymentDTO paymentDTO;



}