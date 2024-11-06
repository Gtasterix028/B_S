package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoiceDetails;
import com.spring.jwt.dto.InvoicesDetailsDTO;
import com.spring.jwt.entity.Invoices;
import com.spring.jwt.entity.InvoicesDetails;
import com.spring.jwt.entity.Products;
import com.spring.jwt.repository.InvoiceDetailsRepository;
import com.spring.jwt.repository.ProductsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceDetailsService implements IInvoiceDetails {

    @Autowired
    private InvoiceDetailsRepository invoicesDetailsRepository;

    @Autowired
    private InvoiceDetailsRepository invoiceDetailsRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Object saveInformation(UUID id, InvoicesDetailsDTO invoicesDetailsDTO) {
        InvoicesDetails invoicesDetails1 = invoiceDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found with ID: " ));

        Products product = productsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " ));

        InvoicesDetails invoicesDetails = modelMapper.map(invoicesDetailsDTO, InvoicesDetails.class);

//        invoicesDetails.setInvoice(invoicesDetails.getInvoice());
//        invoicesDetails.setProduct(product);
        InvoicesDetails savedInvoicesDetails = invoicesDetailsRepository.save(invoicesDetails);

        return modelMapper.map(savedInvoicesDetails, InvoicesDetailsDTO.class);
    }


    @Override
    public List<InvoicesDetailsDTO> getAllInvoicesDetails() {
        List<InvoicesDetails> invoicesDetailsList = invoicesDetailsRepository.findAll();
        List<InvoicesDetailsDTO> invoicesDetailsDTOList = new ArrayList<>();
        for (InvoicesDetails invoicesDetails : invoicesDetailsList) {
            InvoicesDetailsDTO invoicesDetailsDTO = modelMapper.map(invoicesDetails, InvoicesDetailsDTO.class);
            invoicesDetailsDTOList.add(invoicesDetailsDTO);
        }
        return invoicesDetailsDTOList;
    }

    @Override
    public InvoicesDetailsDTO getById(UUID id) {
        InvoicesDetails invoicesDetails = invoicesDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice detail not found for ID: " + id));
        return modelMapper.map(invoicesDetails, InvoicesDetailsDTO.class);
    }

    @Override
    public InvoicesDetailsDTO updateAny(UUID id, InvoicesDetailsDTO invoicesDetailsDTO) {
        InvoicesDetails invoicesDetails = invoicesDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice detail not found for ID: " + id));

        invoicesDetails.setQuantity(invoicesDetailsDTO.getQuantity());

        InvoicesDetails updatedInvoicesDetails = invoicesDetailsRepository.save(invoicesDetails);
        return modelMapper.map(updatedInvoicesDetails, InvoicesDetailsDTO.class);
    }
}