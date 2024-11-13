
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

    private String description;

    private Double actualPrice;
    private Double discount;
    private Double sellingPrice;

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "stockQuantities")
    private List<Integer> stockQuantities;

}


