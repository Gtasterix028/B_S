package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;
import com.spring.jwt.dto.TransactionDTO;
import com.spring.jwt.entity.Invoice1;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface IInvoice1 {

    List<TransactionDTO> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate);

    List<Invoice1> getInvoicesByID(UUID id);

    List<ProductWithInvoicesDTO> saveInvoiceAndProductsWithPrice(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantity, List<Double> productPrice);
}

