package com.spring.jwt.repository;

import com.spring.jwt.entity.PaymentMethods;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentMethodRepositoryTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @InjectMocks
    private PaymentMethods paymentMethod;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        PaymentMethods paymentMethod1 = new PaymentMethods();
        paymentMethod1.setId(1);
        paymentMethod1.setPaymentMethodName("Credit Card");

        PaymentMethods paymentMethod2 = new PaymentMethods();
        paymentMethod2.setId(2);
        paymentMethod2.setPaymentMethodName("PayPal");

        List<PaymentMethods> mockPaymentMethods = Arrays.asList(paymentMethod1, paymentMethod2);

        when(paymentMethodRepository.findAll()).thenReturn(mockPaymentMethods);

        List<PaymentMethods> paymentMethods = paymentMethodRepository.findAll();

        assertEquals(2, paymentMethods.size());
        assertEquals(mockPaymentMethods, paymentMethods);
    }

    @Test
    public void testFindById() {
        PaymentMethods paymentMethod = new PaymentMethods();
        paymentMethod.setId(1);
        paymentMethod.setPaymentMethodName("Credit Card");

        when(paymentMethodRepository.findById(1)).thenReturn(Optional.of(paymentMethod));

        Optional<PaymentMethods> foundPaymentMethod = paymentMethodRepository.findById(1);

        assertEquals(true, foundPaymentMethod.isPresent());
        assertEquals(paymentMethod, foundPaymentMethod.get());
    }

    @Test
    public void testSave() {
        PaymentMethods paymentMethod = new PaymentMethods();
        paymentMethod.setPaymentMethodName("Credit Card");

        when(paymentMethodRepository.save(paymentMethod)).thenReturn(paymentMethod);

        PaymentMethods savedPaymentMethod = paymentMethodRepository.save(paymentMethod);

        assertEquals(paymentMethod, savedPaymentMethod);
        verify(paymentMethodRepository, times(1)).save(paymentMethod);
    }

    @Test
    public void testDelete() {
        PaymentMethods paymentMethod = new PaymentMethods();
        paymentMethod.setId(1);
        paymentMethod.setPaymentMethodName("Credit Card");

        doNothing().when(paymentMethodRepository).delete(paymentMethod);

        paymentMethodRepository.delete(paymentMethod);
        verify(paymentMethodRepository, times(1)).delete(paymentMethod);
    }
}
