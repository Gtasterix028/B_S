package com.spring.jwt.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.jwt.entity.Invoice1;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class SaleDTO {
    private UUID saleId;
    private Double daily;

    private Double day;

    private Double month;

    private Double year;

    private Double total;


//    private List<Invoice1DTO> invoice1sDto;
}
