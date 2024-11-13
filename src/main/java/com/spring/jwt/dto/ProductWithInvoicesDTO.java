package com.spring.jwt.dto;
import com.spring.jwt.entity.ClothingType;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ProductWithInvoicesDTO {
    private UUID productID;
    private String productName;
    private Double sellingPrice;
    private Double discount;
    private ClothingType clothingType;
    private Double subTotalPrice;
    private Double grandTotal;
    private Double productPrice;

    private String size;
    private String color;

        private UUID invoice1ID;
    private String invoiceNumber;
        private LocalDate invoice1Date;
        private Double sellQuantity;
        private LocalDate invoice1DueDate;

    private Double cGstInRs;
    private Double sGstInRs;

    private Double cGstInPercent;
    private Double sGstInPercent;
    private String paymentMethod;


}
