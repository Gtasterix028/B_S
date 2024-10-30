//package com.spring.jwt.service;
//
//import com.spring.jwt.Interfaces.IInvoice;
//import com.spring.jwt.dto.InvoiceDTO;
//import com.spring.jwt.entity.Invoices;
//import com.spring.jwt.entity.Customers;
//import com.spring.jwt.repository.CustomersRepository;
//import com.spring.jwt.repository.InvoiceRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//
//@Service
//public class InvoiceService implements IInvoice {
//
//    @Autowired
//    private InvoiceRepository invoiceRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private CustomersRepository customersRepository;
//
//    @Override
//    public Object saveInformation(UUID id , InvoiceDTO invoiceDTO){
//
//        Customers customer = customersRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        Invoices invoice = modelMapper.map(invoiceDTO, Invoices.class);
//        invoice.setCustomers(customer);
//        Invoices savedInvoice = invoiceRepository.save(invoice);
//        return modelMapper.map(savedInvoice , InvoiceDTO.class);
//    }
//
//    @Override
//    public List<InvoiceDTO> getALlInvoices(){
//        List<Invoices> invoicesList = invoiceRepository.findAll();
//        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
//        for (Invoices invoice : invoicesList) {
//            InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);
//            invoiceDTOList.add(invoiceDTO);
//        }
//        return invoiceDTOList;
//    }
//
//    @Override
//    public InvoiceDTO getById(UUID id) {
//        Invoices invoice = invoiceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + id));
//
//        return modelMapper.map(invoice, InvoiceDTO.class);
//    }
//
//    @Override
//    public InvoiceDTO updateAny(UUID id ,InvoiceDTO invoiceDTO){
//        Invoices existingInvoice = invoiceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + id));
//
//        existingInvoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
//        existingInvoice.setDueDate(invoiceDTO.getDueDate());
//        existingInvoice.setTotal(invoiceDTO.getTotal());
//
//        Invoices updatedInvoice = invoiceRepository.save(existingInvoice);
//        return modelMapper.map(updatedInvoice, InvoiceDTO.class);
//    }
//}
