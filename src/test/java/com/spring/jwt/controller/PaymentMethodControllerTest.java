package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IPaymentMethod;
import com.spring.jwt.dto.PaymentMethodDto;
import com.spring.jwt.dto.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentMethodControllerTest {

    @Mock
    private IPaymentMethod paymentMethodInterface;

    @InjectMocks
    private PaymentMethodController paymentMethodController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePaymentMethod() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Credit Card");

        when(paymentMethodInterface.savePayment(paymentMethodDto)).thenReturn(paymentMethodDto);

        ResponseEntity<Response> responseEntity = paymentMethodController.saveDiscount(paymentMethodDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("SavedDiscount Added", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).savePayment(paymentMethodDto);
    }

    @Test
    public void testGetAll() {
        PaymentMethodDto paymentMethodDto1 = new PaymentMethodDto();
        paymentMethodDto1.setPaymentMethodName("Credit Card");

        PaymentMethodDto paymentMethodDto2 = new PaymentMethodDto();
        paymentMethodDto2.setPaymentMethodName("PayPal");

        List<PaymentMethodDto> paymentMethodDtos = Arrays.asList(paymentMethodDto1, paymentMethodDto2);

        when(paymentMethodInterface.getAll()).thenReturn(paymentMethodDtos);

        ResponseEntity<Response> response = paymentMethodController.getAll();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("get all paymentsMethods", response.getBody().getMessage());
        assertEquals(paymentMethodDtos, response.getBody().getObject());
        verify(paymentMethodInterface, times(1)).getAll();
    }


    @Test
    public void testGetByIdSuccess() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Credit Card");

        when(paymentMethodInterface.getById(1)).thenReturn(paymentMethodDto);

        ResponseEntity<Response> responseEntity = paymentMethodController.getById(1);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("getById successfully", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).getById(1);
    }

    @Test
    public void testGetByIdNotFound() {
        when(paymentMethodInterface.getById(1)).thenReturn(null);

        ResponseEntity<Response> responseEntity = paymentMethodController.getById(1);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("getByid not successully", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).getById(1);
    }

    @Test
    public void testUpdateSuccess() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Debit Card");

        when(paymentMethodInterface.update(1, paymentMethodDto)).thenReturn(paymentMethodDto);

        ResponseEntity<Response> responseEntity = paymentMethodController.update(1, paymentMethodDto);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("update Successfully", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).update(1, paymentMethodDto);
    }

    @Test
    public void testUpdateNotFound() {
        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.setPaymentMethodName("Debit Card");

        when(paymentMethodInterface.update(1, paymentMethodDto)).thenReturn(null);

        ResponseEntity<Response> responseEntity = paymentMethodController.update(1, paymentMethodDto);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("update not successfully", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).update(1, paymentMethodDto);
    }

    @Test
    public void testDeleteSuccess() {
        doNothing().when(paymentMethodInterface).delete(1);

        ResponseEntity<Response> responseEntity = paymentMethodController.delete(1);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Deleted Successfully", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).delete(1);
    }

    @Test
    public void testDeleteFail() {
        doThrow(new RuntimeException("Delete failed")).when(paymentMethodInterface).delete(1);

        ResponseEntity<Response> responseEntity = paymentMethodController.delete(1);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("delete not successfully", responseEntity.getBody().getMessage());
        verify(paymentMethodInterface, times(1)).delete(1);
    }
}
