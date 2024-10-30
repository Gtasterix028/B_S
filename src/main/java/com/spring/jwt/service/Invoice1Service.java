package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoice1;
import com.spring.jwt.Mapper.ProductWithInvoicesMapper;
import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;
import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Products;
import com.spring.jwt.repository.CustomersRepository;
import com.spring.jwt.repository.Invoice1Repository;
import com.spring.jwt.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Invoice1Service implements IInvoice1 {  // Change here to implement IInvoice1

    @Autowired
    private Invoice1Repository invoice1Repository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductWithInvoicesMapper mapper;


    @Override
    public Invoice1DTO saveInformation(Invoice1DTO invoice1DTO) {
        Invoice1 invoice = modelMapper.map(invoice1DTO, Invoice1.class);
        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class); // Now this is correct

        Customers savedCustomer = customersRepository.save(customer);
        invoice.setCustomer(savedCustomer); // Make sure you have the correct setter for single customer


        Invoice1 savedInvoice = invoice1Repository.save(invoice);
        return modelMapper.map(savedInvoice, Invoice1DTO.class);
    }

    @Transactional
    public List<ProductWithInvoicesDTO> getByNameAndSaveQuantity(List<String> productNames, List<Double> sellQuantities, UUID invoiceId) {
        Optional<Invoice1> invoiceOptional = invoice1Repository.findById(invoiceId);
        if (!invoiceOptional.isPresent()) {
            throw new RuntimeException("Invoice with ID " + invoiceId + " not found.");
        }

        if (productNames.size() != sellQuantities.size()) {
            throw new RuntimeException("The number of product names must match the number of sell quantities.");
        }

        Invoice1 existingInvoice = invoiceOptional.get();
        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();

        // Iterate over product names and quantities
        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            Double sellQuantity = sellQuantities.get(i);

            // Find product by name
            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
            if (foundProducts.isEmpty()) {
                throw new RuntimeException("Product with name " + productName + " not found.");
            }

            for (Products product : foundProducts) {
                // Create ProductWithInvoicesDTO for mapping
                ProductWithInvoicesDTO productDTO = new ProductWithInvoicesDTO();
                productDTO.setProductID(product.getProductID());
                productDTO.setProductName(product.getProductName());
                productDTO.setUnitPrice(product.getUnitPrice());
                productDTO.setActualPrice(product.getActualPrice());
                productDTO.setSellingPrice(product.getSellingPrice());
                productDTO.setDiscount(product.getDiscount());
                productDTO.setClothingType(product.getClothingType());
                productDTO.setSellQuantity(sellQuantity);
                productDTO.setInvoice1Date(existingInvoice.getInvoice1Date());
                productDTO.setInvoice1DueDate(existingInvoice.getInvoice1DueDate());
                productDTO.setSubmit(existingInvoice.getSubmit());

                // Map to Invoice1 entity
                Invoice1 newInvoice = mapper.toInvoiceEntity(productDTO, product, existingInvoice);
                invoice1Repository.save(newInvoice);
                 // Save new Invoice1 entity

                // Map back to DTO for response
                ProductWithInvoicesDTO savedProductDTO = mapper.toProductWithInvoicesDTO(product, newInvoice);
                productsDTOList.add(savedProductDTO);



            }
        }
        return productsDTOList;
    }
}



//@Override
//public List<ProductWithInvoicesDTO> getByNameAndSaveQuantity(List<String> productNames, Double sellQuantity, UUID invoiceId) {
//    // Check if the invoice exists by ID
//    Optional<Invoice1> invoiceOptional = invoice1Repository.findById(invoiceId);
//    if (!invoiceOptional.isPresent()) {
//        throw new RuntimeException("Invoice with ID " + invoiceId + " not found.");
//    }
//    Invoice1 invoice = invoiceOptional.get();
//
//    List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();
//
//    // Loop through each product name in the list
//    for (String productName : productNames) {
//        // Find products by name
//        List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
//        if (foundProducts.isEmpty()) {
//            throw new RuntimeException("Product with name " + productName + " not found.");
//        }
//
//        // For each found product, update the invoice with product details and selling quantity
//        for (Products product : foundProducts) {
//            // Update invoice with product details and selling quantity
//           // invoice.setProduct(product);
//            invoice.setSellQuantity(sellQuantity);
//
//            // Save the updated invoice back to the database
//            invoice1Repository.save(invoice);
//
//            // Convert product and invoice to DTO
//            ProductWithInvoicesDTO productDTO = new ProductWithInvoicesDTO();
//            productDTO.setProductID(product.getProductID());
//            productDTO.setProductName(product.getProductName());
//            productDTO.setUnitPrice(product.getUnitPrice());
//            productDTO.setActualPrice(product.getActualPrice());
//            productDTO.setSellingPrice(product.getSellingPrice());
//            productDTO.setDiscount(product.getDiscount());
//            productDTO.setClothingType(product.getClothingType());
//
//            // Set invoice details in the DTO
//            productDTO.setInvoice1ID(invoice.getInvoice1ID());
//            productDTO.setInvoice1Date(invoice.getInvoice1Date());
//            productDTO.setInvoice1DueDate(invoice.getInvoice1DueDate());
//            productDTO.setSubmit(invoice.getSubmit());
//            productDTO.setSellQuantity(invoice.getSellQuantity());
//
//            productsDTOList.add(productDTO);
//
//            // Breaking after the first match, if each invoice should relate to only one product
//            break;
//        }
//    }
//
//    return productsDTOList;
//}




