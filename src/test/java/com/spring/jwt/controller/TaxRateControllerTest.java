package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ITaxRate;
import com.spring.jwt.dto.Response;
import com.spring.jwt.dto.TaxRateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaxRateControllerTest {

    @Mock
    private ITaxRate taxRateSecurity;

    @InjectMocks
    private TaxRateController taxRateController;

    private TaxRateDto taxRateDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taxRateDto = new TaxRateDto();
        taxRateDto.setTaxRateId(1);
        taxRateDto.setTaxRate(15.0);
        taxRateDto.setTaxName("GST");
    }

    @Test
    public void testTaxRateByID() {
        when(taxRateSecurity.taxratebyid(1)).thenReturn(taxRateDto);

        ResponseEntity<Response> response = taxRateController.taxRateByID(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TaxRates Get By Id Successfully", response.getBody().getMessage());
        assertEquals(taxRateDto, response.getBody().getObject());
    }

    @Test
    public void testSaveTaxRate() {
        when(taxRateSecurity.savetaxrate(taxRateDto)).thenReturn(taxRateDto);

        ResponseEntity<Response> response = taxRateController.saveTaxRate(taxRateDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Save TaxRate Succesfully", response.getBody().getMessage());
        assertEquals(taxRateDto, response.getBody().getObject());
    }

    @Test
    public void testGetAll() {
        List<TaxRateDto> taxRateList = new ArrayList<>();
        taxRateList.add(taxRateDto);
        when(taxRateSecurity.getall()).thenReturn(taxRateList);

        ResponseEntity<Response> response = taxRateController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Get All Data Succesfully", response.getBody().getMessage());
        assertEquals(taxRateList, response.getBody().getObject());
    }

    @Test
    public void testUpdateData() {
        when(taxRateSecurity.updateData(1, taxRateDto)).thenReturn(taxRateDto);

        ResponseEntity<Response> response = taxRateController.updateData(1, taxRateDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Update Data Succesfully", response.getBody().getMessage());
        assertEquals(taxRateDto, response.getBody().getObject());
    }

    @Test
    public void testDeleteTaxRate() {
        doNothing().when(taxRateSecurity).deleteTaxRate(1);

        ResponseEntity<Response> response = taxRateController.deleteTaxRate(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Delete TaxRate Successfully", response.getBody().getMessage());
    }
}
