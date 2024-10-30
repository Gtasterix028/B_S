
package com.spring.jwt.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.jwt.entity.ClothingType;
import com.spring.jwt.entity.Invoice1;
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

    @Column(nullable = false)
    private Double unitPrice;

    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "quantity")
    private List<Integer> stockQuantities;

    // One product can belong to many invoices
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Invoice1> invoices; // Change from "invoices" to "invoice1List" for clarity if needed
}

