package com.spring.jwt.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class Invoice1DTO {

    private UUID invoice1ID;
    private LocalDate invoice1Date;
    private LocalDate invoice1DueDate;
    private Boolean submit;
    private CustomersDTO customer;
    private List<ProductsDTO> products;
}