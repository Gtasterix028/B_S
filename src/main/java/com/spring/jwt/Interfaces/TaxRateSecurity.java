package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.TaxRateDto;
import com.spring.jwt.entity.TaxRates;

import java.util.List;

public interface TaxRateSecurity {

    TaxRateDto taxratebyid(Integer id);

    TaxRateDto savetaxrate(TaxRateDto taxRateDto);

    List<TaxRateDto> getall();

    TaxRateDto updateData(Integer id, TaxRateDto taxRateDto);

    void deleteTaxRate(Integer id);
}
