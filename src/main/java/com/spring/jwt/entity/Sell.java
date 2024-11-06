package com.spring.jwt.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private UUID sellingId;
    private LocalDateTime time;
    private Integer sellQuantity;

    @OneToOne(mappedBy = "sell",cascade = CascadeType.ALL)
   private  Customers customers;

    @OneToMany(mappedBy = "sell", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   private List<Products> products;

}
