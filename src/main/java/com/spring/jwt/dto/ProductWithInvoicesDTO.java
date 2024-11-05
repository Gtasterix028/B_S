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
    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;
    private ClothingType clothingType;
    private Double subTotalPrice;

        private UUID invoice1ID;
        private LocalDate invoice1Date;
        private Double sellQuantity;
        private LocalDate invoice1DueDate;
//        private Boolean submit;

        // Add any other invoice fields you want to include

}
// getByName?invoiceId=3fda6c0d-89a9-47f3-8a0e-1a2104013fd0&productNames=Printed Shirt&productNames=Plane Shirt&sellQuantity=75&sellQuantity=50