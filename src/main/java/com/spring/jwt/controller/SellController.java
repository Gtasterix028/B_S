package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ISell;
import com.spring.jwt.dto.DiscountDto;
import com.spring.jwt.dto.Response;
import com.spring.jwt.dto.SellDTO;
//import com.spring.jwt.service.SellService; // Assume you have a service layer
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sells")
public class SellController {
    @Autowired
private ISell sell;

    @PostMapping("/saveInformation")
    public ResponseEntity<Response> saveSell(@RequestBody SellDTO sell) {
        try {
            SellDTO savedSell = sell.saveSell(sell);
            Response response = new Response("SavedSell Added", savedSell, false);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Response response = new Response("Not Saved Sell", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        }

    }
