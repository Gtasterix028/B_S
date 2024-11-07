
package com.spring.jwt.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Sell {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID sellingId;
    private LocalDate date;
    private Double productSellQuantity;
    private Double actualPrice;
    private UUID productIdl;
    private Double productSubtotal;

    @ManyToOne
    @JoinColumn(name = "invoice1ID", nullable = false)
    @JsonBackReference(value = "invoice-sell")
    private Invoice1 invoice1;


//    @OneToOne(mappedBy = "sell",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private  Customers customers;
//
//    @OneToMany(mappedBy = "sell", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Products> products;invoice1ID



}