package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.SellDTO;

import java.time.LocalDate;
import java.util.List;

public interface ISell {
    List<SellDTO> getDailyTotal(LocalDate date);

//    List<SellDTO> getMonthlyTotal(int year, int month);
//
//    List<SellDTO> getYearlyTotal(int year);
//
//    List<SellDTO> getTotalFromStartDateToPresent(LocalDate startDate);




    Double getSubTotal(LocalDate date);

    Double getSubTotalBetweenDates(LocalDate startDate, LocalDate endDate);

    List<Double> getProductSubtotals(String period);
}
