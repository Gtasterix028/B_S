package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.SellDTO;

import java.time.LocalDate;
import java.util.List;

public interface ISell {


    Double getGrandTotal(String period);
}
