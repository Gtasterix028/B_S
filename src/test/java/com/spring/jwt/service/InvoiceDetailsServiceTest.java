package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoiceDetails;
import com.spring.jwt.dto.InvoicesDetailsDTO;
import com.spring.jwt.entity.InvoicesDetails;
import com.spring.jwt.repository.InvoiceDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InvoiceDetailsServiceTest {

    @Mock
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private InvoiceDetailsService invoiceDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveInformation() {
        InvoicesDetailsDTO dto = new InvoicesDetailsDTO();
        dto.setQuantity(5);

        InvoicesDetails entity = new InvoicesDetails();
        entity.setQuantity(5);

        when(modelMapper.map(dto, InvoicesDetails.class)).thenReturn(entity);
        when(invoiceDetailsRepository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, InvoicesDetailsDTO.class)).thenReturn(dto);

        Object savedDto = invoiceDetailsService.saveInformation(dto);

        assertEquals(dto, savedDto);
        verify(invoiceDetailsRepository, times(1)).save(entity);
    }

    @Test
    public void testGetAllInvoicesDetails() {
        InvoicesDetails entity1 = new InvoicesDetails();
        entity1.setQuantity(5);

        InvoicesDetails entity2 = new InvoicesDetails();
        entity2.setQuantity(10);

        InvoicesDetailsDTO dto1 = new InvoicesDetailsDTO();
        dto1.setQuantity(5);

        InvoicesDetailsDTO dto2 = new InvoicesDetailsDTO();
        dto2.setQuantity(10);

        when(invoiceDetailsRepository.findAll()).thenReturn(Arrays.asList(entity1, entity2));
        when(modelMapper.map(entity1, InvoicesDetailsDTO.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, InvoicesDetailsDTO.class)).thenReturn(dto2);

        List<InvoicesDetailsDTO> result = invoiceDetailsService.getAllInvoicesDetails();

        assertEquals(2, result.size());
        assertEquals(dto1, result.get(0));
        assertEquals(dto2, result.get(1));
    }

    @Test
    public void testGetById() {
        InvoicesDetails entity = new InvoicesDetails();
        entity.setInvoiceDetailID(1);
        entity.setQuantity(5);

        InvoicesDetailsDTO dto = new InvoicesDetailsDTO();
        dto.setInvoiceDetailID(1);
        dto.setQuantity(5);

        when(invoiceDetailsRepository.findById(1)).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, InvoicesDetailsDTO.class)).thenReturn(dto);

        InvoicesDetailsDTO result = invoiceDetailsService.getById(1);

        assertEquals(dto, result);
    }

    @Test
    public void testUpdateAny() {
        InvoicesDetails entity = new InvoicesDetails();
        entity.setInvoiceDetailID(1);
        entity.setQuantity(5);

        InvoicesDetailsDTO dto = new InvoicesDetailsDTO();
        dto.setQuantity(10);

        InvoicesDetails updatedEntity = new InvoicesDetails();
        updatedEntity.setInvoiceDetailID(1);
        updatedEntity.setQuantity(10);

        when(invoiceDetailsRepository.findById(1)).thenReturn(Optional.of(entity));
        when(invoiceDetailsRepository.save(updatedEntity)).thenReturn(updatedEntity);
        when(modelMapper.map(updatedEntity, InvoicesDetailsDTO.class)).thenReturn(dto);

        InvoicesDetailsDTO result = invoiceDetailsService.updateAny(1, dto);

        assertEquals(dto, result);
    }
}
