//package com.spring.jwt.service;
//
//import com.spring.jwt.Interfaces.IInvoice;
//import com.spring.jwt.dto.InvoiceDTO;
//import com.spring.jwt.entity.Customers;
//import com.spring.jwt.entity.Invoice1;
//import com.spring.jwt.repository.CustomersRepository;
//import com.spring.jwt.repository.Invoice1Repository;
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
//    private Invoice1Repository invoiceRepository;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private CustomersRepository customersRepository;
//
//    @Override
//    public Object saveInformation( InvoiceDTO invoiceDTO){
//
////        Customers customer = customersRepository.findById(id)
////                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        Invoice1 invoice = modelMapper.map(invoiceDTO, Invoice1.class);
//       // invoice.setCustomers(customer);
//        Invoice1 savedInvoice = invoiceRepository.save(invoice);
//        return modelMapper.map(savedInvoice , InvoiceDTO.class);
//    }
//
//    @Override
//    public List<InvoiceDTO> getALlInvoices(){
//        List<Invoice1> invoicesList = invoiceRepository.findAll();
//        List<InvoiceDTO> invoiceDTOList = new ArrayList<>();
//        for (Invoice1 invoice : invoicesList) {
//            InvoiceDTO invoiceDTO = modelMapper.map(invoice, InvoiceDTO.class);
//            invoiceDTOList.add(invoiceDTO);
//        }
//        return invoiceDTOList;
//    }
//
//    @Override
//    public InvoiceDTO getById(UUID id) {
//        Invoice1 invoice = invoiceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + id));
//
//        return modelMapper.map(invoice, InvoiceDTO.class);
//    }
//
//    @Override
//    public InvoiceDTO updateAny(UUID id ,InvoiceDTO invoiceDTO){
//        Invoice1 existingInvoice = invoiceRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Invoice not found for ID: " + id));
//
//        existingInvoice.setInvoice1Date(invoiceDTO.getInvoiceDate());
//        existingInvoice.setDueDate(invoiceDTO.getDueDate());
//        existingInvoice.setTotal(invoiceDTO.getTotal());
//
//        Invoice1 updatedInvoice = invoiceRepository.save(existingInvoice);
//        return modelMapper.map(updatedInvoice, InvoiceDTO.class);
//    }
//}
