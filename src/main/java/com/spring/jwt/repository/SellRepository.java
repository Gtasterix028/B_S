package com.spring.jwt.repository;

import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SellRepository extends JpaRepository<Sell,UUID> {
    List<Sell> findByInvoice1(Invoice1 invoice);
}
