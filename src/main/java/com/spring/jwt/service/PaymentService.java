package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IPayment;
import com.spring.jwt.dto.PaymentDTO;
import com.spring.jwt.entity.Payment;
import com.spring.jwt.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService implements IPayment {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    @Override
    public Payment savePayment(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Integer id, PaymentDTO paymentDTO) {
        Payment payment = getPaymentById(id);
        modelMapper.map(paymentDTO, payment);
        return paymentRepository.save(payment);
    }



    @Override
    public void deletePaymentById(Integer id) {
        Payment payment = getPaymentById(id);
        paymentRepository.delete(payment);
    }

}
