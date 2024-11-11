//package com.spring.jwt.controller;
//
//import com.spring.jwt.Interfaces.ITax;
//import com.spring.jwt.dto.Response;
//import com.spring.jwt.dto.ShippingDto;
//
//import com.spring.jwt.service.ShippingService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/Shipping")
//@RequiredArgsConstructor
//public class ShippingController {
//       @Autowired
//       ITax taxSecurity;
//
//      private ShippingService service;
//
//    @PostMapping("/saveInformation")
//    public ResponseEntity<Response> register(@RequestBody ShippingDto shippingDto) {
//        try {
//           ShippingDto shippingsave = taxSecurity.SaveShipping(shippingDto);
//            Response response = new Response("Shipping Save succesfully", shippingsave, false);
//            return  ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } catch (Exception e) {
//            Response response = new Response("Shipping Save Unsuccesfully", e.getMessage(), false);
//            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//
//        }
//
//    }
//
//    @PatchMapping("/updateInformation")
//    public ResponseEntity<Response> updateShipping(@RequestParam int id, @RequestBody ShippingDto shippingDto) {
//        ShippingDto updatedShipping = taxSecurity.updateShipping(id, shippingDto);
//        if (updatedShipping != null) {
//            Response response = new Response("Shipping updated successfully", updatedShipping, false);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            Response response = new Response("Shipping not found for update", null, true);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/getById")
//     public ResponseEntity<Response> getShippingById(@RequestParam int id) {
//        ShippingDto shippingDto = taxSecurity.getById(id);
//        if (shippingDto != null) {
//            Response response = new Response("Shipping detail fetched successfully", shippingDto, false);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            Response response = new Response("Shipping not found", null, true);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<Object> getAllShipping() {
//        List<ShippingDto> shippingList = taxSecurity.getAll();
//        return new ResponseEntity<>(shippingList, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/deleteById")
//    public ResponseEntity<Response> deleteShippingById(@RequestParam int id) {
//        boolean isDeleted = taxSecurity.deleteById(id);
//        if (isDeleted) {
//            Response response = new Response("Shipping deleted successfully", null, false);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            Response response = new Response("Shipping not found for deletion", null, true);
//            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//        }
//    }
//
//
//
//}
