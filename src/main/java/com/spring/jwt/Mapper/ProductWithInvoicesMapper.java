
package com.spring.jwt.Mapper;
import com.spring.jwt.dto.ProductWithInvoicesDTO;

import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductWithInvoicesMapper {

    // Map from DTO to Entity
    public Invoice1 toInvoiceEntity(ProductWithInvoicesDTO dto, Products product, Invoice1 existingInvoice) {
        Invoice1 newInvoice = new Invoice1();
        newInvoice.setProduct(product);  // Set the associated product
        newInvoice.setInvoice1Date(dto.getInvoice1Date());
        newInvoice.setInvoice1DueDate(dto.getInvoice1DueDate());
        newInvoice.setSellQuantity(dto.getSellQuantity());
        newInvoice.setSubmit(dto.getSubmit());
        newInvoice.setCustomer(existingInvoice.getCustomer());// Set the existing customer
        // Set additional product details in the Invoice1 entity
        newInvoice.setProductID(product.getProductID());
        newInvoice.setProductName(product.getProductName());
        newInvoice.setActualPrice(product.getActualPrice());
        newInvoice.setSellingPrice(product.getSellingPrice());
        newInvoice.setDiscount(product.getDiscount());
        newInvoice.setClothingType(product.getClothingType());
        newInvoice.setSubTotalPrice(product.getSubTotalPrice());
        return newInvoice;
    }

    // Map from Entity to DTO
    public ProductWithInvoicesDTO toProductWithInvoicesDTO(Products product, Invoice1 invoice) {
        ProductWithInvoicesDTO productDTO = new ProductWithInvoicesDTO();
        productDTO.setProductID(product.getProductID());
        productDTO.setProductName(product.getProductName());
        productDTO.setActualPrice(product.getActualPrice());
        productDTO.setSellingPrice(product.getSellingPrice());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setClothingType(product.getClothingType());
        productDTO.setSubTotalPrice(product.getSubTotalPrice());

        productDTO.setInvoice1ID(invoice.getInvoice1ID());
        productDTO.setInvoice1Date(invoice.getInvoice1Date());
        productDTO.setSellQuantity(invoice.getSellQuantity());
        productDTO.setInvoice1DueDate(invoice.getInvoice1DueDate());
        productDTO.setSubmit(invoice.getSubmit());

        return productDTO;
    }
}
