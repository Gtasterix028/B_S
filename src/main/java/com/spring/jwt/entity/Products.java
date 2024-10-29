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

    @Column(nullable = false)
    private Double price;

    private Double actualPrice;
    private Double sellingPrice;
    private Double discount;

    @Enumerated(EnumType.STRING)
    private ClothingType clothingType;

    @OneToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<InvoicesDetails> invoicesDetails;

    @ElementCollection
    @CollectionTable(name = "stock", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "quantity")
    private List<Integer> stockQuantities;

//    // Many products can belong to many invoices
//    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
//    private List<Invoice1> invoices;

}
