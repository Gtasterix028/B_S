package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IPaymentMethod;
import com.spring.jwt.dto.PaymentMethodDto;
import com.spring.jwt.entity.PaymentMethods;
import com.spring.jwt.repository.PaymentMethodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentMethodService implements IPaymentMethod {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PaymentMethodDto savePayment(PaymentMethodDto paymentMethodDto) {

        PaymentMethods paymentMethods = modelMapper.map(paymentMethodDto, PaymentMethods.class);
        paymentMethodRepository.save(paymentMethods);
        return modelMapper.map(paymentMethods, PaymentMethodDto.class);
    }

    @Override
    public List<PaymentMethodDto> getAll(){
        List<PaymentMethods> payMethod=paymentMethodRepository.findAll();
        List<PaymentMethodDto> pay=new ArrayList();
        for(PaymentMethods paymentMethods : payMethod){
            pay.add(modelMapper.map(paymentMethods,PaymentMethodDto.class));
        }
        return pay;
    }

    @Override
    public PaymentMethodDto getById(Integer id){
        PaymentMethods paymentMethods=paymentMethodRepository.findById(id).orElse(null);
        PaymentMethodDto methodDto=modelMapper.map(paymentMethods,PaymentMethodDto.class);
        return methodDto;
    }

    @Override
    public PaymentMethodDto update(Integer id,PaymentMethodDto paymentMethodDto){
        PaymentMethods paymentMethods=paymentMethodRepository.findById(id).orElseThrow(() -> new RuntimeException("PaymentMethod not found Exception"));
        if(paymentMethodDto.getPaymentMethodName() != null){
            paymentMethods.setPaymentMethodName(paymentMethodDto.getPaymentMethodName());
        }
        PaymentMethods updatepaymentMethod=paymentMethodRepository.save(paymentMethods);
        return modelMapper.map(updatepaymentMethod,PaymentMethodDto.class);
    }

    @Override
    public void delete(Integer id) {
        PaymentMethods paymentMethods=paymentMethodRepository.findById(id).orElseThrow(() -> new RuntimeException("PaymentMethod not found Exception"));
        paymentMethodRepository.delete(paymentMethods);

    }
}
