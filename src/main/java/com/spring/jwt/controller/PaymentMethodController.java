package com.spring.jwt.controller;


import com.spring.jwt.Interfaces.IPaymentMethod;
import com.spring.jwt.dto.PaymentMethodDto;
import com.spring.jwt.dto.Response;
import com.spring.jwt.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/PaymentMethod")
@RestController

public class PaymentMethodController {
    @Autowired
    private IPaymentMethod paymentMethodInterface;
    private PaymentMethodService paymentMethodService;

    @PostMapping("/saveInformation")
    public ResponseEntity<Response> saveDiscount(@RequestBody PaymentMethodDto paymentMethodDto) {
        try {
            PaymentMethodDto savePaymentMethod = paymentMethodInterface.savePayment(paymentMethodDto);
            Response response = new Response("SavedDiscount Added", savePaymentMethod, false);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Response response = new Response("Not Saved Discount", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }

    }
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAll() {
        try {
            List<PaymentMethodDto> savedPayment = paymentMethodInterface.getAll();
            Response response=new Response("get all paymentsMethods", savedPayment,false);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
         catch(Exception e){
            Response response=new Response("payment Method is not get Successfully ",e.getMessage(),true);
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
         }

    }
    @GetMapping("getById")
    public ResponseEntity<Response> getById(@RequestParam Integer id){
        PaymentMethodDto payGet=paymentMethodInterface.getById(id);
        if(payGet != null){
            Response response=new Response("getById successfully",payGet,false);
            return new ResponseEntity<>(response,HttpStatus.CREATED);

        }
        else{
            Response response=new Response("getByid not successully", payGet,true);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping("/update")
    public ResponseEntity<Response> update(@RequestParam Integer id,@RequestBody PaymentMethodDto paymentMethodDto){
    PaymentMethodDto updatePayment=paymentMethodInterface.update(id,paymentMethodDto);
        if(updatePayment != null){
            Response response=new Response("update Successfully",updatePayment,false);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        else {
            Response response=new Response("update not successfully",updatePayment,true);
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
     @DeleteMapping("/delete")
    public ResponseEntity<Response> delete(@RequestParam Integer id) {

         try{
             paymentMethodInterface.delete(id);
             Response response = new Response("Deleted Successfully", null, false);
             return new ResponseEntity<>(response, HttpStatus.CREATED);
         } catch(Exception e) {
             Response response = new Response("delete not successfully", e.getMessage(), true);
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
         }

     }
}
