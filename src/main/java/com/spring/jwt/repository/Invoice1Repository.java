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

    @Query("SELECT SUM(i.grandTotal) FROM Invoice1 i WHERE DATE(i.invoice1Date) = CURRENT_DATE")
    Double findDailyGrandTotals();

    @Query("SELECT SUM(i.grandTotal) FROM Invoice1 i WHERE MONTH(i.invoice1Date) = MONTH(CURRENT_DATE) AND YEAR(i.invoice1Date) = YEAR(CURRENT_DATE)")
    Double findMonthlyGrandTotals();

    @Query("SELECT SUM(i.grandTotal) FROM Invoice1 i WHERE YEAR(i.invoice1Date) = YEAR(CURRENT_DATE)")
    Double findYearlyGrandTotals();

    @Query("SELECT SUM(i.grandTotal) FROM Invoice1 i")
    Double findAllGrandTotals();

    List<Invoice1> findByInvoice1ID(UUID id);
}
