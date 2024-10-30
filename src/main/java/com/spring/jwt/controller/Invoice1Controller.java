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

//    @GetMapping("getByName")
//    public ResponseEntity<Response> getByNameAndSaveQuantity(@RequestParam String name, @RequestParam Double sellQuantity){
//          //  ,@RequestBody UUID invoiceID) {
//        try{
//        List<ProductsDTO> productsDTOList = iInvoice1.getByNameAndSaveQuantity(name,sellQuantity);
//            return ResponseEntity.ok(new Response("List of Product by name", productsDTOList, false));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new Response("Failed to display the List ", e.getMessage(), true));
//        }
//
//    }

    @GetMapping("getByName")
    public ResponseEntity<Response> getByNameAndSaveQuantity(
            @RequestParam List<String> productNames,
            @RequestParam List<Double>sellQuantity,
            @RequestParam UUID invoiceId) { // Accept customerId as a parameter

        try {
            List<ProductWithInvoicesDTO> productsDTOList = iInvoice1.getByNameAndSaveQuantity(productNames, sellQuantity,  invoiceId);
            return ResponseEntity.ok(new Response("List of Product by name", productsDTOList, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("Failed to display the List ", e.getMessage(), true));
        }
    }


}




