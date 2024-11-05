package com.spring.jwt.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

//    @OneToOne
//    @JoinColumn(name = "selling_id")
//    private Sell sell;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Invoice1> invoicesList;


//    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<Invoice1> invoices1List;

//    @OneToOne(mappedBy = "customer")
//    private Payment payment;
}




