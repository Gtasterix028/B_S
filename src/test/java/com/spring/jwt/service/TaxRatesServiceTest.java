package com.spring.jwt.service;


import com.spring.jwt.dto.TaxRateDto;
import com.spring.jwt.entity.TaxRates;
import com.spring.jwt.repository.TaxRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaxRatesServiceTest {

    @Mock
    private TaxRateRepository repository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private TaxRatesService taxRatesService;

    private TaxRateDto taxRateDto;
    private TaxRates taxRates;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        taxRateDto = new TaxRateDto();
        taxRateDto.setTaxRateId(1);
        taxRateDto.setTaxRate(15.0);
        taxRateDto.setTaxName("GST");

        taxRates = new TaxRates();
        taxRates.setTaxRateId(1);
        taxRates.setTaxRate(15.0);
        taxRates.setTaxName("GST");
    }

    @Test
    public void testTaxRateById() {
        when(repository.findById(1)).thenReturn(Optional.of(taxRates));
        when(mapper.map(taxRates, TaxRateDto.class)).thenReturn(taxRateDto);

        TaxRateDto result = taxRatesService.taxratebyid(1);

        assertNotNull(result);
        assertEquals(taxRateDto.getTaxRateId(), result.getTaxRateId());
        verify(repository, times(1)).findById(1);
    }

    @Test
    public void testSaveTaxRate() {
        when(mapper.map(taxRateDto, TaxRates.class)).thenReturn(taxRates);
        when(repository.save(taxRates)).thenReturn(taxRates);
        when(mapper.map(taxRates, TaxRateDto.class)).thenReturn(taxRateDto);

        TaxRateDto result = taxRatesService.savetaxrate(taxRateDto);

        assertNotNull(result);
        assertEquals(taxRateDto.getTaxRateId(), result.getTaxRateId());
        verify(repository, times(1)).save(taxRates);
    }

    @Test
    public void testGetAllTaxRates() {
        List<TaxRates> taxRatesList = Arrays.asList(taxRates);
        when(repository.findAll()).thenReturn(taxRatesList);
        when(mapper.map(taxRates, TaxRateDto.class)).thenReturn(taxRateDto);

        List<TaxRateDto> result = taxRatesService.getall();

        assertEquals(1, result.size());
        assertEquals(taxRateDto.getTaxRateId(), result.get(0).getTaxRateId());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testUpdateTaxRate() {
        when(repository.findById(1)).thenReturn(Optional.of(taxRates));
        when(mapper.map(taxRateDto, TaxRates.class)).thenReturn(taxRates);
        when(repository.save(taxRates)).thenReturn(taxRates);
        when(mapper.map(taxRates, TaxRateDto.class)).thenReturn(taxRateDto);

        TaxRateDto result = taxRatesService.updateData(1, taxRateDto);

        assertNotNull(result);
        assertEquals(taxRateDto.getTaxRateId(), result.getTaxRateId());
        verify(repository, times(1)).save(taxRates);
    }

    @Test
    public void testDeleteTaxRate() {
        when(repository.findById(1)).thenReturn(Optional.of(taxRates));
        doNothing().when(repository).delete(taxRates);

        taxRatesService.deleteTaxRate(1);

        verify(repository, times(1)).delete(taxRates);
    }

    @Test
    public void testTaxRateByIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taxRatesService.taxratebyid(1);
        });

        assertEquals("Tax rate detail not found for ID: 1", exception.getMessage());
    }

    @Test
    public void testUpdateTaxRateNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taxRatesService.updateData(1, taxRateDto);
        });

        assertEquals("Tax rate detail not found for ID: 1", exception.getMessage());
    }

    @Test
    public void testDeleteTaxRateNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taxRatesService.deleteTaxRate(1);
        });

        assertEquals("Tax rate detail not found for ID: 1", exception.getMessage());
    }
}
