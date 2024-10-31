package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.InvoiceDTO;

import java.util.List;
import java.util.UUID;

public interface IInvoice {

    Object saveInformation( InvoiceDTO invoiceDTO);

    List<InvoiceDTO> getALlInvoices();

    InvoiceDTO getById(UUID id);

    InvoiceDTO updateAny(UUID id, InvoiceDTO invoiceDTO);

}
