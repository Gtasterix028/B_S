package com.spring.jwt.repository;

import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.Invoice1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface Invoice1Repository extends JpaRepository<Invoice1, UUID> {

    @Query("SELECT i FROM Invoice1 i WHERE i.invoice1Date BETWEEN :startDate AND :endDate")
    List<Invoice1> findInvoicesByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
