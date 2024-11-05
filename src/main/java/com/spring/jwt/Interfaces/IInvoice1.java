package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;

import java.util.List;

public interface IInvoice1 {

    List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities);

}

