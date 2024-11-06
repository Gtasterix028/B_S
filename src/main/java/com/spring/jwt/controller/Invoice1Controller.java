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


    @PostMapping("saveInvoice")
    public ResponseEntity<Response> saveInvoice2(
            @RequestBody Invoice1DTO invoice1DTO,
            @RequestParam List<String> productNames,
            @RequestParam List<Double> sellQuantity) {
        try {

            List<ProductWithInvoicesDTO> savedDTO1 = iInvoice1.saveInvoiceAndProducts(invoice1DTO, productNames, sellQuantity);

            return ResponseEntity.ok(new Response("Invoice and products saved successfully", savedDTO1, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to save invoice and products", e.getMessage(), true));
        }
    }


}