package com.spring.jwt.service;


import com.spring.jwt.Interfaces.TaxRateSecurity;
import com.spring.jwt.dto.TaxRateDto;
import com.spring.jwt.entity.TaxRates;
import com.spring.jwt.repository.TaxRateRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaxRatesService implements TaxRateSecurity {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TaxRateRepository repository;


    @Override
    public TaxRateDto taxratebyid(Integer id) {
        TaxRates taxRates = repository.findById(id).orElse(null);
        if (taxRates != null) {
            return mapper.map(taxRates, TaxRateDto.class);
        } else {
            throw new RuntimeException("Tax rate detail not found for ID: " + id);
        }
    }

    @Override
    public TaxRateDto savetaxrate(TaxRateDto taxRateDto) {
        TaxRates toEntity = mapper.map(taxRateDto, TaxRates.class);
        TaxRates saveTaxRate = repository.save(toEntity);
        return mapper.map(saveTaxRate, TaxRateDto.class);
    }

    @Override
    public List<TaxRateDto> getall() {
        List<TaxRates> taxRatesList = repository.findAll();
        return taxRatesList.stream()
                .map(taxRates -> mapper.map(taxRates, TaxRateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaxRateDto updateData(Integer id, TaxRateDto taxRateDto) {
        TaxRates taxRates = repository.findById(id).orElse(null);
        if (taxRates != null) {
            taxRates.setTaxRate(taxRateDto.getTaxRate());
            taxRates.setTaxName(taxRateDto.getTaxName());
            TaxRates updatedTaxRates = repository.save(taxRates);
            return mapper.map(updatedTaxRates, TaxRateDto.class);
        } else {
            throw new RuntimeException("Tax rate detail not found for ID: " + id);
        }
    }

    @Override
    public void deleteTaxRate(Integer id) {
        TaxRates taxRates = repository.findById(id).orElse(null);
        if (taxRates != null) {
            repository.delete(taxRates);
        } else {
            throw new RuntimeException("Tax rate detail not found for ID: " + id);
        }
    }
}






