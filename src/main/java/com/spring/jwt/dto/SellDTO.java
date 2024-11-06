package com.spring.jwt.dto;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data

public class SellDTO {
    private UUID sellingId;
    private LocalDateTime time;
    private Integer sellQuantity;
    private CustomersDTO customers;
    private List<ProductsDTO> products;
}
