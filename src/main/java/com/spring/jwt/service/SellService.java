package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ISell;
import com.spring.jwt.dto.SellDTO;
import com.spring.jwt.entity.Sell;
import com.spring.jwt.repository.SellRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellService implements ISell {
    @Autowired
    private SellRepository sellRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<SellDTO> getDailyTotal(LocalDate date) {
        List<Sell> sells = sellRepository.findByDate(date);
        return sells.stream()
                .map(sell -> modelMapper.map(sell, SellDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Double getSubTotal(LocalDate date) {
        List<Double> productSubtotals = sellRepository.findProductSubtotalsByDate(date);
        return productSubtotals.stream()
                .filter(subtotal -> subtotal != null) // filter out null values if any
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public Double getSubTotalBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Double> productSubtotals = sellRepository.findProductSubtotalsBetweenDates(startDate, endDate);

        return productSubtotals.stream()
                .filter(subtotal -> subtotal != null) // filter out null values if any
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public Double getProductSubtotals(String period) {
        switch (period) {
            case "daily":
                List<Double> doubles= sellRepository.findDailyProductSubtotals();
                return doubles.stream()
                        .filter(subtotal -> subtotal != null) // filter out null values if any
                        .mapToDouble(Double::doubleValue)
                        .sum();
            case "monthly":
                List<Double> doubles1= sellRepository.findMonthlyProductSubtotals();
                return doubles1.stream()
                        .filter(subtotal -> subtotal != null) // filter out null values if any
                        .mapToDouble(Double::doubleValue)
                        .sum();
            case "yearly":
               List<Double> doubles2= sellRepository.findYearlyProductSubtotals();
                return doubles2.stream()
                        .filter(subtotal -> subtotal != null) // filter out null values if any
                        .mapToDouble(Double::doubleValue)
                        .sum();
               case "all":
            default:
                List<Double> doubles3=sellRepository.findAllProductSubtotals();
                return doubles3.stream()
                        .filter(subtotal -> subtotal != null) // filter out null values if any
                        .mapToDouble(Double::doubleValue)
                        .sum();
        }
    }
}
