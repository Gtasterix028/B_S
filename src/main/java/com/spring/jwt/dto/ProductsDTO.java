package com.spring.jwt.dto;


import com.spring.jwt.entity.InvoicesDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class ProductsDTO {
    private  Integer productID;
    private String productName;
    private String description;
    private Double price;
    private List<InvoicesDetailsDTO> invoicesDetails;

}
