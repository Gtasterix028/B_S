package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IInvoice;
import com.spring.jwt.dto.InvoiceDTO;
import com.spring.jwt.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvoiceControllerTest {

    @InjectMocks
    private InvoiceController invoiceController;

    @Mock
    private IInvoice iInvoice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveInformation() {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setTotal(100.0);
        Integer customerId = 1;

       // when(iInvoice.saveInformation(customerId, invoiceDTO)).thenReturn(invoiceDTO);

       // ResponseEntity<Response> response = invoiceController.saveInformation(customerId, invoiceDTO);

       // assertEquals(HttpStatus.OK, response.getStatusCode());
//assertEquals("Information added Sucessfully", response.getBody().getMessage());
      //  assertEquals(invoiceDTO, response.getBody().getObject());
      //  verify(iInvoice, times(1)).saveInformation(customerId, invoiceDTO);
    }

    @Test
    public void testGetAll() {
        List<InvoiceDTO> invoiceDTOList = Arrays.asList(new InvoiceDTO(), new InvoiceDTO());

        when(iInvoice.getALlInvoices()).thenReturn(invoiceDTOList);

        ResponseEntity<Response> response = invoiceController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("List of All Invoices", response.getBody().getMessage());
        assertEquals(invoiceDTOList, response.getBody().getObject());
        verify(iInvoice, times(1)).getALlInvoices();
    }

    @Test
    public void testGetAllNoInvoices() {
        when(iInvoice.getALlInvoices()).thenReturn(Collections.emptyList());

        ResponseEntity<Response> response = invoiceController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("List of All Invoices", response.getBody().getMessage());
        assertTrue(((List<?>) response.getBody().getObject()).isEmpty());
        verify(iInvoice, times(1)).getALlInvoices();
    }

    @Test
    public void testGetById() {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setTotal(100.0);
        Integer invoiceId = 1;

        when(iInvoice.getById(invoiceId)).thenReturn(invoiceDTO);

        ResponseEntity<Response> response = invoiceController.getById(invoiceId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invoice details for ID: " + invoiceId, response.getBody().getMessage());
        assertEquals(invoiceDTO, response.getBody().getObject());
        verify(iInvoice, times(1)).getById(invoiceId);
    }

    @Test
    public void testGetByIdNotFound() {
        Integer invoiceId = 1;
        when(iInvoice.getById(invoiceId)).thenThrow(new RuntimeException("Invoice not found for ID: " + invoiceId));

        ResponseEntity<Response> response = invoiceController.getById(invoiceId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to retrieve invoice for ID: " + invoiceId, response.getBody().getMessage());
        verify(iInvoice, times(1)).getById(invoiceId);
    }

    @Test
    public void testUpdateAny() {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setTotal(150.0);
        Integer invoiceId = 1;

        when(iInvoice.updateAny(invoiceId, invoiceDTO)).thenReturn(invoiceDTO);

        ResponseEntity<Response> response = invoiceController.updateAny(invoiceId, invoiceDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Invoice Updated by ID: " + invoiceId, response.getBody().getMessage());
        assertEquals(invoiceDTO, response.getBody().getObject());
        verify(iInvoice, times(1)).updateAny(invoiceId, invoiceDTO);
    }

    @Test
    public void testUpdateAnyNotFound() {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        Integer invoiceId = 1;

        when(iInvoice.updateAny(invoiceId, invoiceDTO)).thenThrow(new RuntimeException("Invoice not found for ID: " + invoiceId));

        ResponseEntity<Response> response = invoiceController.updateAny(invoiceId, invoiceDTO);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Failed to update invoice by ID: " + invoiceId, response.getBody().getMessage());
        verify(iInvoice, times(1)).updateAny(invoiceId, invoiceDTO);
    }
}