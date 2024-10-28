package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.InvoicesDetailsDTO;

import java.util.List;
import java.util.UUID;

public interface IInvoiceDetails {
    Object saveInformation(UUID id , InvoicesDetailsDTO invoicesDetailsDTO);

    List<InvoicesDetailsDTO> getAllInvoicesDetails();

    InvoicesDetailsDTO getById(UUID id);

    InvoicesDetailsDTO updateAny(UUID id, InvoicesDetailsDTO invoicesDetailsDTO);
}
