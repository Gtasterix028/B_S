
package com.spring.jwt.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.jwt.entity.ClothingType;
//import com.spring.jwt.entity.Invoice1;
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
    private Double sellingPrice;
    private Double discount;
    private Double subTotalPrice;

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "stockQuantities")
    private List<Integer> stockQuantities;

    @ManyToOne
    @JoinColumn(name = "selling_id")  // Foreign key to Sell
    private Sell sell;

    // One product can belong to many invoices
//    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Invoice1> invoices;
}

