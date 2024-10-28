package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IPayment;
import com.spring.jwt.dto.PaymentDTO;
import com.spring.jwt.dto.Response;
import com.spring.jwt.entity.Payment;
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

public class PaymentControllerTest {

    @Mock
    private IPayment iPayment;

    @InjectMocks
    private PaymentController paymentController;

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
    public void testSavePaymentInformation() {
        when(iPayment.savePayment(paymentDTO)).thenReturn(payment);

        ResponseEntity<Response> response = paymentController.savePaymentInformation(paymentDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Payment Added", response.getBody().getMessage());
        assertEquals(payment, response.getBody().getObject());
        verify(iPayment, times(1)).savePayment(paymentDTO);
    }

    @Test
    public void testGetPaymentById() {
        when(iPayment.getPaymentById(1)).thenReturn(payment);

        ResponseEntity<Response> response = paymentController.getPaymentById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment Retrieved", response.getBody().getMessage());
        assertEquals(payment, response.getBody().getObject());
        verify(iPayment, times(1)).getPaymentById(1);
    }

    @Test
    public void testGetAllPayments() {
        List<Payment> payments = Arrays.asList(payment);
        when(iPayment.getAllPayments()).thenReturn(payments);

        ResponseEntity<Response> response = paymentController.getAllPayments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payments Retrieved", response.getBody().getMessage());
        assertEquals(payments, response.getBody().getObject());
        verify(iPayment, times(1)).getAllPayments();
    }

    @Test
    public void testUpdatePayment() {
        when(iPayment.updatePayment(1, paymentDTO)).thenReturn(payment);

        ResponseEntity<Response> response = paymentController.updatePayment(1, paymentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment Updated", response.getBody().getMessage());
        assertEquals(payment, response.getBody().getObject());
        verify(iPayment, times(1)).updatePayment(1, paymentDTO);
    }

    @Test
    public void testDeletePaymentById() {
        doNothing().when(iPayment).deletePaymentById(1);

        ResponseEntity<Response> response = paymentController.deletePaymentById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment Deleted Successfully", response.getBody().getMessage());
        verify(iPayment, times(1)).deletePaymentById(1);
    }
}
