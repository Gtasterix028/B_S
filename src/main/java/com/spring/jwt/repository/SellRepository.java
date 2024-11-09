package com.spring.jwt.repository;

import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SellRepository extends JpaRepository<Sell,UUID> {
    List<Sell> findByInvoice1(Invoice1 invoice);

    @Query("SELECT s FROM Sell s WHERE s.date = :date")
    List<Sell> findByDate(@Param("date") LocalDate date);

    @Query("SELECT s.productSubtotal FROM Sell s WHERE s.date = :date")
    List<Double> findProductSubtotalsByDate(@Param("date") LocalDate date);

    @Query("SELECT s.productSubtotal FROM Sell s WHERE s.date BETWEEN :startDate AND :endDate")
    List<Double> findProductSubtotalsBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);





    @Query("SELECT SUM(s.productSubtotal) FROM Sell s WHERE DATE(s.date) = CURRENT_DATE GROUP BY s.productId")
    List<Double> findDailyProductSubtotals();

    @Query("SELECT SUM(s.productSubtotal) FROM Sell s WHERE MONTH(s.date) = MONTH(CURRENT_DATE) AND YEAR(s.date) = YEAR(CURRENT_DATE) GROUP BY s.productId")
    List<Double> findMonthlyProductSubtotals();

    @Query("SELECT SUM(s.productSubtotal) FROM Sell s WHERE YEAR(s.date) = YEAR(CURRENT_DATE) GROUP BY s.productId")
    List<Double> findYearlyProductSubtotals();


    @Query("SELECT SUM(s.productSubtotal) FROM Sell s GROUP BY s.productId")
    List<Double> findAllProductSubtotals();


}
