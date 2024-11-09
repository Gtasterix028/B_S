
package com.spring.jwt.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productID;

    @Column(nullable = false)

    private String productName;
    @Column(nullable = true)
    private String description;
    @Column(nullable = true)
    private Double actualPrice;
    @Column(nullable = true)
    private Double discount;
    @Column(nullable = true)
    private Double sellingPrice;  // Price with Discount

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "stockQuantities")
    private List<Integer> stockQuantities;

}

