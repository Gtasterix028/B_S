package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;

import java.util.List;
import java.util.UUID;


public interface IInvoice1 {
    Invoice1DTO saveInformation( Invoice1DTO invoice1DTO);

   // List<ProductsDTO> getByNameAndSaveQuantity(String productName, Double sellQuantity);//, UUID invoiceID);


    List<ProductWithInvoicesDTO> getByNameAndSaveQuantity(List<String> productNames, List<Double> sellQuantity, UUID invoiceId);
}

