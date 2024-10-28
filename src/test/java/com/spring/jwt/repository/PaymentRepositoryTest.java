package com.spring.jwt.repository;

import com.spring.jwt.entity.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class PaymentRepositoryTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private Payment payment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Payment payment1 = new Payment();
        payment1.setPaymentID(1);
        payment1.setPaymentDate(LocalDate.now());
        payment1.setPaymentMethod("Credit Card");
        payment1.setAmount(150.0);

        Payment payment2 = new Payment();
        payment2.setPaymentID(2);
        payment2.setPaymentDate(LocalDate.now());
        payment2.setPaymentMethod("PayPal");
        payment2.setAmount(200.0);

        List<Payment> mockPayments = Arrays.asList(payment1, payment2);

        when(paymentRepository.findAll()).thenReturn(mockPayments);

        List<Payment> payments = paymentRepository.findAll();

        assertEquals(2, payments.size());
        assertEquals(mockPayments, payments);
    }

    @Test
    public void testFindById() {
        Payment payment = new Payment();
        payment.setPaymentID(1);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(150.0);

        when(paymentRepository.findById(1)).thenReturn(Optional.of(payment));

        Optional<Payment> foundPayment = paymentRepository.findById(1);

        assertEquals(true, foundPayment.isPresent());
        assertEquals(payment, foundPayment.get());
    }

    @Test
    public void testSave() {
        Payment payment = new Payment();
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(150.0);

        when(paymentRepository.save(payment)).thenReturn(payment);

        Payment savedPayment = paymentRepository.save(payment);

        assertEquals(payment, savedPayment);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testDelete() {
        Payment payment = new Payment();
        payment.setPaymentID(1);
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentMethod("Credit Card");
        payment.setAmount(150.0);

        doNothing().when(paymentRepository).delete(payment);

        paymentRepository.delete(payment);
        verify(paymentRepository, times(1)).delete(payment);
    }
}
