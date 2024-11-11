package com.spring.jwt.dto;

import com.spring.jwt.entity.Sell;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class CustomersDTO {
    private UUID customerID;
    private String fullName ;
    private String email;
    private String phone;
    private String address;

    //private List<InvoiceDTO> invoiceDTOS;

    // private List<Invoice1DTO> invoicesList;

    // private SellDTO sell;

//    private PaymentDTO paymentDTO;
}




