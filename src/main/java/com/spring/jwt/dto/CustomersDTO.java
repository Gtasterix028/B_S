package com.spring.jwt.dto;

import com.spring.jwt.entity.Invoices;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class CustomersDTO {
    private UUID customerID ;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    private Optional<List<InvoiceDTO>> invoiceDTOS = Optional.empty();
    private Optional<PaymentDTO> paymentDTO = Optional.empty();



}
