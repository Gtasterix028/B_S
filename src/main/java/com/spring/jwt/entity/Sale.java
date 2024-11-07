package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID saleId;

    private Double daily;

    private Double day;

    private Double month;

    private Double year;

    private Double total;

//    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JsonManagedReference(value = "invoice-sell") // Named managed reference
//    private List<Invoice1> invoice1s;

}
