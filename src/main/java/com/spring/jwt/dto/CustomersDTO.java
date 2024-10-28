package com.spring.jwt.dto;

import com.spring.jwt.entity.Invoices;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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


    private List<InvoiceDTO> invoiceDTOS;
     private PaymentDTO paymentDTO;



}
