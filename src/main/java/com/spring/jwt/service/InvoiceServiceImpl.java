package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoice;
import com.spring.jwt.dto.InvoiceDTO;
import com.spring.jwt.dto.ShippingDto;
import com.spring.jwt.entity.Invoices;
import com.spring.jwt.entity.ShippingDetail;
import com.spring.jwt.repository.InvoiceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements IInvoice {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Object saveInformation(InvoiceDTO invoiceDTO) {
        Invoices invoice = modelMapper.map(invoiceDTO, Invoices.class);

        List<ShippingDetail> shippingDetails = new ArrayList<>();

        invoiceDTO.getShippingDetails().forEach(detailDTO -> {
            ShippingDetail shippingDetail = modelMapper.map(detailDTO, ShippingDetail.class);
            shippingDetail.setInvoices(invoice);
            shippingDetails.add(shippingDetail);
        });
        invoice.setShippingDetails(shippingDetails);

        Invoices savedInvoice = invoiceRepository.save(invoice);
        return modelMapper.map(savedInvoice, InvoiceDTO.class);
    }


    @Override
    public List<InvoiceDTO> getALlInvoices() {
        // Fetch all invoices
        List<Invoices> invoicesList = invoiceRepository.findAll();

        // Map the invoice list to InvoiceDTO list
        return invoicesList.stream()
                .map(invoice -> modelMapper.map(invoice, InvoiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getById(Integer id) {
        // Fetch invoice by ID or throw exception if not found
        Invoices invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + id));

        return modelMapper.map(invoice, InvoiceDTO.class);
    }


    @Override
    public InvoiceDTO updateAny(Integer id, InvoiceDTO invoiceDTO) {

        Invoices existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + id));

        existingInvoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
        existingInvoice.setDueDate(invoiceDTO.getDueDate());
        existingInvoice.setTotal(invoiceDTO.getTotal());

        List<ShippingDetail> updatedShippingDetails = invoiceDTO.getShippingDetails()
                .stream()
                .map(detailDTO -> modelMapper.map(detailDTO, ShippingDetail.class))
                .collect(Collectors.toList());

        updatedShippingDetails.forEach(detail -> detail.setInvoices(existingInvoice));
        existingInvoice.setShippingDetails(updatedShippingDetails);

        Invoices updatedInvoice = invoiceRepository.save(existingInvoice);
        return modelMapper.map(updatedInvoice, InvoiceDTO.class);
    }
}
