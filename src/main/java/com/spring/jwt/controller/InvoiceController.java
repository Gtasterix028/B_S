package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IInvoice;
import com.spring.jwt.dto.InvoiceDTO;
import com.spring.jwt.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")

public class InvoiceController {

    @Autowired
    private IInvoice iInvoice;

    @PostMapping("/saveInformation")
    public ResponseEntity<Response> saveInformation (@RequestParam UUID id, @RequestBody InvoiceDTO invoiceDTO){
        try{

            System.out.println("post data of Invoice DTO from from frontend...."+id+"&&&&"+invoiceDTO);
            Object saveDTO = iInvoice.saveInformation(id,invoiceDTO);
            return  ResponseEntity.ok(new Response("Information added Sucessfully",saveDTO,false));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response("Failed to add Information",e.getMessage(),true));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAll() {
        try{
            List<InvoiceDTO> invoiceDTOList = iInvoice.getALlInvoices();
            return ResponseEntity.ok(new Response("List of All Invoices",invoiceDTOList,false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("Failed to Display All Invoices",e.getMessage(),true));
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<Response> getById(@RequestParam UUID id) {
        try {
            InvoiceDTO invoiceDTO = iInvoice.getById(id);
            return ResponseEntity.ok(new Response("Invoice details for ID: " + id, invoiceDTO, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Failed to retrieve invoice for ID: " + id, e.getMessage(), true));
        }
    }

    @PatchMapping("/updateAny")
    public ResponseEntity<Response> updateAny
            (@RequestParam UUID id,
            @RequestBody InvoiceDTO invoiceDTO){
        try {
            InvoiceDTO invoiceDTO1 = iInvoice.updateAny(id,invoiceDTO);
            return ResponseEntity.ok(new Response("Invoice Updated by ID: " + id, invoiceDTO1, false));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response("Failed to update invoice by ID: " + id, e.getMessage(), true));
        }
    }
}
