package com.spring.jwt.dto;

import com.spring.jwt.entity.ClothingType;
import com.spring.jwt.entity.Products;
import com.spring.jwt.entity.Sell;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class Invoice1DTO {
    private UUID invoice1ID;
    private String invoiceNumber;
    private LocalDate invoice1Date;
    private LocalDate invoice1DueDate;

    private Double sellQuantity;

    private UUID productID;
    private String productName;
    private Double sellingPrice;
    private Double discount;
    private ClothingType clothingType;


    private String paymentMethod;

    private Double grandTotal;

    private Double cGstInRs;
    private Double sGstInRs;

    private Double cGstInPercent;
    private Double sGstInPercent;

    private CustomersDTO customer;
    private List<SellDTO> sells;

}




