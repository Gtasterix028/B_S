
package com.spring.jwt.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Sell {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID sellingId;
    private LocalDateTime date;
    private Integer sellQuantity;

//    @OneToOne(mappedBy = "sell",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private  Customers customers;
//
//    @OneToMany(mappedBy = "sell", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Products> products;

}