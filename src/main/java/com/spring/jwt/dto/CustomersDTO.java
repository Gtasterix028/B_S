package com.spring.jwt.dto;

import lombok.Data;

import java.util.List;


@Data
public class CustomersDTO {
    private Integer customerID ;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    private List<InvoiceDTO> invoicesList;
}
