package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IPayment;
import com.spring.jwt.dto.PaymentDTO;
import com.spring.jwt.entity.Payment;
import com.spring.jwt.repository.PaymentRepository;
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

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;
    private PaymentDTO paymentDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);


        payment = new Payment();
        payment.setPaymentID(1);
        payment.setAmount(100.0);

        paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(100.0);
    }

    @Test
    public void testGetAllPayments() {
        when(paymentRepository.findAll()).thenReturn(Arrays.asList(payment));

        List<Payment> payments = paymentService.getAllPayments();

        assertEquals(1, payments.size());
        assertEquals(payment, payments.get(0));
        verify(paymentRepository, times(1)).findAll();
    }

    @Test
    public void testGetPaymentById() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        Payment result = paymentService.getPaymentById(1);

        assertEquals(payment, result);
        verify(paymentRepository, times(1)).findById(1);
    }

    @Test
    public void testGetPaymentById_NotFound() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            paymentService.getPaymentById(1);
        });

        assertEquals("Payment not found with id: 1", thrown.getMessage());
    }

    @Test
    public void testSavePayment() {
        when(modelMapper.map(paymentDTO, Payment.class)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentService.savePayment(paymentDTO);

        assertEquals(payment, savedPayment);
        verify(modelMapper, times(1)).map(paymentDTO, Payment.class);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testUpdatePayment() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        // Call the update method
        paymentService.updatePayment(1, paymentDTO);

        // Verify interactions
        verify(paymentRepository, times(1)).findById(1);
        verify(modelMapper, times(1)).map(paymentDTO, payment); // Check that map was called
        verify(paymentRepository, times(1)).save(payment); // Ensure save is called
    }

    @Test
    public void testDeletePaymentById() {
        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));
        doNothing().when(paymentRepository).delete(payment);

        assertDoesNotThrow(() -> paymentService.deletePaymentById(1));
        verify(paymentRepository, times(1)).delete(payment);
    }

    @Test
    public void testDeletePaymentById_NotFound() {
        when(paymentRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            paymentService.deletePaymentById(1);
        });

        assertEquals("Payment not found with id: 1", thrown.getMessage());
    }
}
