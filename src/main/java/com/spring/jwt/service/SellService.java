package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ISell;
import com.spring.jwt.dto.SellDTO;
import com.spring.jwt.entity.Sell;
import com.spring.jwt.repository.Invoice1Repository;
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
    private Invoice1Repository invoice1Repository;

    @Autowired
    ModelMapper modelMapper;


    public Double getGrandTotal(String period) {
        switch (period) {
            case "daily":
                return invoice1Repository.findDailyGrandTotals();
            case "monthly":
                return invoice1Repository.findMonthlyGrandTotals();
            case "yearly":
                return invoice1Repository.findYearlyGrandTotals();
            case "all":
            default:
                return invoice1Repository.findAllGrandTotals();
        }
    }

}
