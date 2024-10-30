//package com.spring.jwt.controller;
//
//
//import com.spring.jwt.Interfaces.IInvoice1;
//import com.spring.jwt.dto.Invoice1DTO;
//import com.spring.jwt.dto.InvoiceDTO;
//import com.spring.jwt.dto.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/Invoice1")
//
//public class Invoice1Controller {
//
//    @Autowired
//    private IInvoice1 iInvoice1;
//
//    @PostMapping("saveInvoice1")
//    public ResponseEntity<Response> saveInformation (@RequestParam UUID id, @RequestBody Invoice1DTO invoice1DTO){
//        try{
//            Object saveDTO = invoice1DTO.saveInformation(id,);
//            return  ResponseEntity.ok(new Response("Information added Sucessfully",saveDTO,false));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failed to add Information",e.getMessage(),true));
//        }
//    }
//
//
//}
