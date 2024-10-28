package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IPaymentMethod;
import com.spring.jwt.dto.PaymentMethodDto;
import com.spring.jwt.entity.PaymentMethods;
import com.spring.jwt.repository.PaymentMethodRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class PaymentMethodServiceTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PaymentMethodService paymentMethodService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePayment() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Credit Card");

        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setPaymentMethodName("Credit Card");

        when(modelMapper.map(paymentMethodDto, PaymentMethods.class)).thenReturn(paymentMethods);
        when(paymentMethodRepository.save(paymentMethods)).thenReturn(paymentMethods);
        when(modelMapper.map(paymentMethods, PaymentMethodDto.class)).thenReturn(paymentMethodDto);

        PaymentMethodDto savedPaymentMethod = paymentMethodService.savePayment(paymentMethodDto);

        assertEquals(paymentMethodDto.getPaymentMethodName(), savedPaymentMethod.getPaymentMethodName());
        verify(paymentMethodRepository, times(1)).save(paymentMethods);
    }

    @Test
    public void testGetAll() {
        PaymentMethodDto paymentMethodDto1 = new PaymentMethodDto();
        paymentMethodDto1.setPaymentMethodName("Credit Card");

        PaymentMethodDto paymentMethodDto2 = new PaymentMethodDto();
        paymentMethodDto2.setPaymentMethodName("PayPal");

        PaymentMethods paymentMethods1 = new PaymentMethods();
        paymentMethods1.setPaymentMethodName("Credit Card");

        PaymentMethods paymentMethods2 = new PaymentMethods();
        paymentMethods2.setPaymentMethodName("PayPal");

        when(paymentMethodRepository.findAll()).thenReturn(Arrays.asList(paymentMethods1, paymentMethods2));
        when(modelMapper.map(paymentMethods1, PaymentMethodDto.class)).thenReturn(paymentMethodDto1);
        when(modelMapper.map(paymentMethods2, PaymentMethodDto.class)).thenReturn(paymentMethodDto2);

        List<PaymentMethodDto> paymentMethodDtos = paymentMethodService.getAll();

        assertEquals(2, paymentMethodDtos.size());
        assertEquals("Credit Card", paymentMethodDtos.get(0).getPaymentMethodName());
        assertEquals("PayPal", paymentMethodDtos.get(1).getPaymentMethodName());
    }

    @Test
    public void testGetById() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Credit Card");

        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setPaymentMethodName("Credit Card");

        when(paymentMethodRepository.findById(1)).thenReturn(Optional.of(paymentMethods));
        when(modelMapper.map(paymentMethods, PaymentMethodDto.class)).thenReturn(paymentMethodDto);

        PaymentMethodDto foundPaymentMethod = paymentMethodService.getById(1);

        assertEquals(paymentMethodDto.getPaymentMethodName(), foundPaymentMethod.getPaymentMethodName());
    }

    @Test
    public void testUpdate() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Debit Card");

        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setPaymentMethodName("Credit Card");

        when(paymentMethodRepository.findById(1)).thenReturn(Optional.of(paymentMethods));
        when(paymentMethodRepository.save(paymentMethods)).thenReturn(paymentMethods);
        when(modelMapper.map(paymentMethods, PaymentMethodDto.class)).thenReturn(paymentMethodDto);

        PaymentMethodDto updatedPaymentMethod = paymentMethodService.update(1, paymentMethodDto);

        assertEquals("Debit Card", updatedPaymentMethod.getPaymentMethodName());
        verify(paymentMethodRepository, times(1)).save(paymentMethods);
    }

    @Test
    public void testDelete() {
        PaymentMethods paymentMethods = new PaymentMethods();
        paymentMethods.setPaymentMethodName("Credit Card");

        when(paymentMethodRepository.findById(1)).thenReturn(Optional.of(paymentMethods));

        paymentMethodService.delete(1);

        verify(paymentMethodRepository, times(1)).delete(paymentMethods);
    }

    @Test
    public void testDeleteNotFound() {
        when(paymentMethodRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            paymentMethodService.delete(1);
        });

        assertEquals("PaymentMethod not found Exception", exception.getMessage());
    }
}
