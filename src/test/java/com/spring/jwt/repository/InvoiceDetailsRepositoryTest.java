package com.spring.jwt.repository;

import com.spring.jwt.entity.InvoicesDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceDetailsRepositoryTest {

    @Mock
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @InjectMocks
    private InvoicesDetails invoicesDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        InvoicesDetails mockInvoiceDetail = new InvoicesDetails();
        mockInvoiceDetail.setInvoiceDetailID(1);
        mockInvoiceDetail.setQuantity(5);

        // Mock the save behavior
        when(invoiceDetailsRepository.save(mockInvoiceDetail)).thenReturn(mockInvoiceDetail);

        // Perform the save operation
        InvoicesDetails savedInvoiceDetail = invoiceDetailsRepository.save(mockInvoiceDetail);

        // Assertions
        assertEquals(mockInvoiceDetail.getInvoiceDetailID(), savedInvoiceDetail.getInvoiceDetailID());
        assertEquals(mockInvoiceDetail.getQuantity(), savedInvoiceDetail.getQuantity());
        verify(invoiceDetailsRepository, times(1)).save(mockInvoiceDetail);
    }

    @Test
    public void testFindById() {
        InvoicesDetails mockInvoiceDetail = new InvoicesDetails();
        mockInvoiceDetail.setInvoiceDetailID(1);
        mockInvoiceDetail.setQuantity(5);

        // Mock the findById behavior
        when(invoiceDetailsRepository.findById(1)).thenReturn(Optional.of(mockInvoiceDetail));

        // Perform the find operation
        Optional<InvoicesDetails> foundInvoiceDetail = invoiceDetailsRepository.findById(1);

        // Assertions
        assertEquals(true, foundInvoiceDetail.isPresent());
        assertEquals(mockInvoiceDetail.getInvoiceDetailID(), foundInvoiceDetail.get().getInvoiceDetailID());
        assertEquals(mockInvoiceDetail.getQuantity(), foundInvoiceDetail.get().getQuantity());
        verify(invoiceDetailsRepository, times(1)).findById(1);
    }

    @Test
    public void testFindAll() {
        InvoicesDetails invoiceDetail1 = new InvoicesDetails();
        invoiceDetail1.setInvoiceDetailID(1);
        invoiceDetail1.setQuantity(5);

        InvoicesDetails invoiceDetail2 = new InvoicesDetails();
        invoiceDetail2.setInvoiceDetailID(2);
        invoiceDetail2.setQuantity(10);

        List<InvoicesDetails> mockInvoiceDetailsList = Arrays.asList(invoiceDetail1, invoiceDetail2);

        // Mock the findAll behavior
        when(invoiceDetailsRepository.findAll()).thenReturn(mockInvoiceDetailsList);

        // Perform the findAll operation
        List<InvoicesDetails> foundInvoiceDetailsList = invoiceDetailsRepository.findAll();

        // Assertions
        assertEquals(2, foundInvoiceDetailsList.size());
        assertEquals(invoiceDetail1.getInvoiceDetailID(), foundInvoiceDetailsList.get(0).getInvoiceDetailID());
        assertEquals(invoiceDetail2.getInvoiceDetailID(), foundInvoiceDetailsList.get(1).getInvoiceDetailID());
        verify(invoiceDetailsRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        // Assume we want to delete the invoice with ID 1
        doNothing().when(invoiceDetailsRepository).deleteById(1);

        // Perform the delete operation
        invoiceDetailsRepository.deleteById(1);

        // Verification
        verify(invoiceDetailsRepository, times(1)).deleteById(1);
    }
}
