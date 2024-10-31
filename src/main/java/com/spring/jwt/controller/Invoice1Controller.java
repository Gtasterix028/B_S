package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IInvoice1;
import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;
import com.spring.jwt.dto.ProductsDTO;
import com.spring.jwt.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Invoice1")
public class Invoice1Controller {

    @Autowired
    private IInvoice1 iInvoice1;


    @PostMapping("saveInvoice1")
    public ResponseEntity<Response> saveInformation(@RequestBody Invoice1DTO invoice1DTO) {
        try {
            Invoice1DTO savedDTO = iInvoice1.saveInformation(invoice1DTO);
            return ResponseEntity.ok(new Response("Information added successfully", savedDTO, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to add Information", e.getMessage(), true));
        }
    }

    @GetMapping("getByName")
    public ResponseEntity<Response> getByNameAndSaveQuantity(
            @RequestParam List<String> productNames,
            @RequestParam List<Double >sellQuantity,
            @RequestParam UUID invoiceId) {
        try {
            List<ProductWithInvoicesDTO> productsDTOList = iInvoice1.getByNameAndSaveQuantity(productNames, sellQuantity,  invoiceId);
            return ResponseEntity.ok(new Response("List of Product by name", productsDTOList, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to display the List ", e.getMessage(), true));
        }
    }

    @PostMapping("saveInvoice2")
    public ResponseEntity<Response> saveInvoice2(
            @RequestBody Invoice1DTO invoice1DTO,
            @RequestParam List<String> productNames,
            @RequestParam List<Double> sellQuantity) {
        try {
            // Call the service method to save the invoice and products
            List<ProductWithInvoicesDTO> savedDTO1 = iInvoice1.saveInvoiceAndProducts(invoice1DTO, productNames, sellQuantity);

            // Return a successful response
            return ResponseEntity.ok(new Response("Invoice and products saved successfully", savedDTO1, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to save invoice and products", e.getMessage(), true));
        }
    }
//    public static class CombinedResponse {
//        private Invoice1DTO invoice;
//        private List<ProductWithInvoicesDTO> products;
//
//        public CombinedResponse(Invoice1DTO invoice, List<ProductWithInvoicesDTO> products) {
//            this.invoice = invoice;
//            this.products = products;
//        }
//
//        // Getters and setters
//        public Invoice1DTO getInvoice() {
//            return invoice;
//        }
//
//        public void setInvoice(Invoice1DTO invoice) {
//            this.invoice = invoice;
//        }
//
//        public List<ProductWithInvoicesDTO> getProducts() {
//            return products;
//        }
//
//        public void setProducts(List<ProductWithInvoicesDTO> products) {
//            this.products = products;
//        }
//    }
}




