//package com.spring.jwt.Controller;
//
//import com.spring.jwt.Interfaces.IInvoice1;
//import com.spring.jwt.controller.Invoice1Controller;
//import com.spring.jwt.dto.Invoice1DTO;
//import com.spring.jwt.dto.ProductWithInvoicesDTO;
//import com.spring.jwt.dto.Response;
//import com.spring.jwt.dto.TransactionDTO;
//import com.spring.jwt.entity.Invoice1;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//
//public class InvoiceController1Test {
//
//    @InjectMocks
//   private Invoice1Controller invoice1Controller;
//
//    @Mock
//    private IInvoice1 iInvoice1;
//
//    @Mock
//    private Invoice1 invoice1;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveInvoice2() {
//
//        Invoice1DTO invoiceDTO = new Invoice1DTO();
//        invoiceDTO.setInvoice1ID(UUID.randomUUID());
//        invoiceDTO.setGrandTotal(1000.0);
//        invoiceDTO.setCGstInRs(5.0);
//        invoiceDTO.setSGstInRs(5.0);
//
//        List<String> productNames = Arrays.asList("Product1", "Product2");
//        List<Double> sellQuantities = Arrays.asList(2.0, 3.0);
//
//        List<ProductWithInvoicesDTO> savedDTOs = new ArrayList<>();
//        savedDTOs.add(new ProductWithInvoicesDTO());
//
//        when(iInvoice1.saveInvoiceAndProducts(invoiceDTO, productNames, sellQuantities)).thenReturn(savedDTOs);
//
//
//        ResponseEntity<Response> response = invoice1Controller.saveInvoice2(invoiceDTO, productNames, sellQuantities,sellingPrice);
//
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Invoice and products saved successfully", response.getBody().getMessage());
//        assertEquals(savedDTOs, response.getBody().getObject());
//        verify(iInvoice1, times(1)).saveInvoiceAndProducts(invoiceDTO, productNames, sellQuantities);
//    }
//
//    @Test
//    public void testGetInvoicesByDateRange() {
//
//        LocalDate startDate = LocalDate.of(2023, 1, 1);
//        LocalDate endDate = LocalDate.of(2023, 12, 31);
//
//        TransactionDTO transaction1 = new TransactionDTO();
//        transaction1.setInvoice1ID(UUID.randomUUID());
//        transaction1.setInvoiceNumber("INV-001");
//        transaction1.setInvoice1Date(LocalDate.of(2023, 5, 15));
//        transaction1.setPaymentMethod("Credit Card");
//        transaction1.setGrandTotal(1000.0);
//        transaction1.setCustomerName("John Doe");
//        transaction1.setCGstInRs(5.0);
//        transaction1.setSGstInRs(5.0);
//        transaction1.setCGstInPercent(2.5);
//        transaction1.setSGstInPercent(2.5);
//
//        TransactionDTO transaction2 = new TransactionDTO();
//        transaction2.setInvoice1ID(UUID.randomUUID());
//        transaction2.setInvoiceNumber("INV-002");
//        transaction2.setInvoice1Date(LocalDate.of(2023, 8, 10));
//        transaction2.setPaymentMethod("Cash");
//        transaction2.setGrandTotal(2000.0);
//        transaction2.setCustomerName("Jane Smith");
//        transaction2.setCGstInRs(10.0);
//        transaction2.setSGstInRs(10.0);
//        transaction2.setCGstInPercent(5.0);
//        transaction2.setSGstInPercent(5.0);
//
//        List<TransactionDTO> mockInvoices = Arrays.asList(transaction1, transaction2);
//
//
//        when(iInvoice1.getInvoicesByDateRange(startDate, endDate)).thenReturn(mockInvoices);
//
//
//        ResponseEntity<List<TransactionDTO>> response = invoice1Controller.getInvoicesByDateRange(startDate, endDate);
//
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(mockInvoices, response.getBody());
//        verify(iInvoice1, times(1)).getInvoicesByDateRange(startDate, endDate);
//    }
//
//    @Test
//    public void testGetInvoicesById() {
//
//        UUID id = UUID.randomUUID();
//
//
//        invoice1.setInvoice1ID(id);
//        invoice1.setInvoiceNumber("INV-001");
//        invoice1.setGrandTotal(1000.0);
//
//        List<Invoice1> mockInvoices = new ArrayList<>();
//        mockInvoices.add(invoice1);
//
//
//        when(iInvoice1.getInvoicesByID(id)).thenReturn(mockInvoices);
//
//
//        ResponseEntity<Response> response = invoice1Controller.getInvoicesById(id);
//
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Invoice retrived successfully", response.getBody().getMessage());
//        assertEquals(mockInvoices, response.getBody().getObject());
//        verify(iInvoice1, times(1)).getInvoicesByID(id);
//    }
//
//
//
//
//}
//
//
