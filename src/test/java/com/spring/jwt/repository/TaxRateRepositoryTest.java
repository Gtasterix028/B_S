package com.spring.jwt.repository;

import com.spring.jwt.entity.TaxRates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaxRateRepositoryTest {

    @Mock
    private TaxRateRepository taxRateRepository;

    @InjectMocks
    private TaxRates taxRates;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        TaxRates rate1 = new TaxRates();
        rate1.setTaxName("Tax 1");
        rate1.setTaxRate(10.0);

        TaxRates rate2 = new TaxRates();
        rate2.setTaxName("Tax 2");
        rate2.setTaxRate(15.0);

        List<TaxRates> mockTaxRates = Arrays.asList(rate1, rate2);

        when(taxRateRepository.findAll()).thenReturn(mockTaxRates);

        List<TaxRates> taxRatesList = taxRateRepository.findAll();

        assertEquals(2, taxRatesList.size());
        assertEquals(mockTaxRates, taxRatesList);
    }

    @Test
    public void testFindById() {
        TaxRates rate = new TaxRates();
        rate.setTaxName("Tax 1");
        rate.setTaxRate(10.0);

        when(taxRateRepository.findById(1)).thenReturn(Optional.of(rate));

        Optional<TaxRates> foundRate = taxRateRepository.findById(1);

        assertTrue(foundRate.isPresent());
        assertEquals(rate, foundRate.get());
    }

    @Test
    public void testSave() {
        TaxRates rate = new TaxRates();
        rate.setTaxName("Tax 1");
        rate.setTaxRate(10.0);

        when(taxRateRepository.save(rate)).thenReturn(rate);

        TaxRates savedRate = taxRateRepository.save(rate);

        assertEquals(rate, savedRate);
        verify(taxRateRepository, times(1)).save(rate);
    }

    @Test
    public void testDelete() {
        TaxRates rate = new TaxRates();
        rate.setTaxName("Tax 1");
        rate.setTaxRate(10.0);

        doNothing().when(taxRateRepository).delete(rate);

        taxRateRepository.delete(rate);
        verify(taxRateRepository, times(1)).delete(rate);
    }
}
