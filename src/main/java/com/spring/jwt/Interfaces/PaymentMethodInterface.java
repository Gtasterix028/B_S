package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.DiscountDto;
import com.spring.jwt.dto.PaymentMethodDto;

import java.util.List;

public interface PaymentMethodInterface {
   PaymentMethodDto savePayment(PaymentMethodDto paymentMethodDto);

   List<PaymentMethodDto> getAll();

   PaymentMethodDto getById(Integer id);

   PaymentMethodDto update(Integer id, PaymentMethodDto paymentMethodDto);

   void delete(Integer id);
}
