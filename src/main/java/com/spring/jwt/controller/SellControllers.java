package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ISell;
import com.spring.jwt.dto.Response;
import com.spring.jwt.dto.SellDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sell")
public class SellControllers {
    @Autowired
    private ISell sell;


    @GetMapping("/allGrandTotal")
    public ResponseEntity<Response> getGrandTotal(@RequestParam String period) {
        try {
            Double grandTotal = sell.getGrandTotal(period);
            Response response = new Response("Sale retrieved successfully", grandTotal, false);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Response response = new Response("Failed to retrieve GrandTotal", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}




