package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IInvoiceDetails;
import com.spring.jwt.dto.InvoicesDetailsDTO;
import com.spring.jwt.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InvoiceDetailsControllerTest {

    @Mock
    private IInvoiceDetails iInvoiceDetails;

    @InjectMocks
    private InvoiceDetailsController invoiceDetailsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveInformation() {
        InvoicesDetailsDTO dto = new InvoicesDetailsDTO();
        dto.setQuantity(5);

        when(iInvoiceDetails.saveInformation(dto)).thenReturn(dto);

        ResponseEntity<Response> response = invoiceDetailsController.saveInformation(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invoice detail added successfully", response.getBody().getMessage());
        assertEquals(dto, response.getBody().getObject());
    }

    @Test
    public void testGetAll() {
        InvoicesDetailsDTO dto1 = new InvoicesDetailsDTO();
        dto1.setQuantity(5);

        InvoicesDetailsDTO dto2 = new InvoicesDetailsDTO();
        dto2.setQuantity(10);

        List<InvoicesDetailsDTO> list = Arrays.asList(dto1, dto2);
        when(iInvoiceDetails.getAllInvoicesDetails()).thenReturn(list);

        ResponseEntity<Response> response = invoiceDetailsController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("List of All Invoice Details", response.getBody().getMessage());
        assertEquals(list, response.getBody().getObject());
    }

    @Test
    public void testGetById() {
        InvoicesDetailsDTO dto = new InvoicesDetailsDTO();
        dto.setInvoiceDetailID(1);
        dto.setQuantity(5);

        when(iInvoiceDetails.getById(1)).thenReturn(dto);

        ResponseEntity<Response> response = invoiceDetailsController.getById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invoice detail for ID: 1", response.getBody().getMessage());
        assertEquals(dto, response.getBody().getObject());
    }

    @Test
    public void testUpdateAny() {
        InvoicesDetailsDTO dto = new InvoicesDetailsDTO();
        dto.setQuantity(10);

        when(iInvoiceDetails.updateAny(1, dto)).thenReturn(dto);

        ResponseEntity<Response> response = invoiceDetailsController.updateAny(1, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invoice detail updated by ID: 1", response.getBody().getMessage());
        assertEquals(dto, response.getBody().getObject());
    }
}
