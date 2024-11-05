package com.spring.jwt.dto;

import com.spring.jwt.entity.ClothingType;
import lombok.Data;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class ProductsDTO {

    private UUID productID;
    private String productName;
    private String description;
    private Double price;
    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;
    private ClothingType clothingType;
    private List<Integer> stockQuantities;
//    private List<InvoiceDTO> invoices; // List of Invoice DTOs

private Optional<List<InvoicesDetailsDTO>> invoicesDetails= Optional.empty();;


}
