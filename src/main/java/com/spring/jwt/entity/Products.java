package com.spring.jwt.entity;

import com.spring.jwt.Enum.ProductType;
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
    private Double price;

    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;

    @Enumerated(EnumType.STRING) // Using enum for product type
    private ClothingType clothingType;// NEW FIELD: indicates if the product is readymade or unstitched

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoicesDetails> invoicesDetails;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "quantity")
    private List<Integer> stockQuantities;


}
