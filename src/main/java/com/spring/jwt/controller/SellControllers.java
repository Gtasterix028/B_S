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
    private ISell sellService;

    @GetMapping("/daily-total")
    public ResponseEntity<Response> getDailyTotal(
            @RequestParam("date") LocalDate date) {
        try {
            List<SellDTO> sellDTOS = sellService.getDailyTotal(date);
            Response response = new Response("get daily sell", sellDTOS, false);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Response response = new Response("sell not get ", e.getMessage(), true);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

//    @GetMapping("AllGrandTotal")
//    public ResponseEntity<Response> getGrandTotal(@RequestParam String period) {
//        try {
//            Double grandTotal = sellService.getGrandTotal(period);
//            Response response = new Response("sale get successfully", grandTotal, false);
//            return ResponseEntity.status(HttpStatus.CREATED).body(response);
//        } catch (Exception e) {
//            Response response = new Response("Failed to retrieve GrandTotal", e.getMessage(), true);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
//    }
        @GetMapping("/subtotal")
        public ResponseEntity<Response> getDateWise (@RequestParam("data") LocalDate date ){
            try {
                Double sellDTOS = sellService.getSubTotal(date);
                Response response = new Response("get properly subtotal", sellDTOS, false);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (Exception e) {
                Response response = new Response("sell not get ", e.getMessage(), true);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        @GetMapping("/subtotalRange")
        public ResponseEntity<Response> getDateWise (
                @RequestParam("startDate") LocalDate startDate,
                @RequestParam("endDate") LocalDate endDate){
            try {
                Double sellDTOS = sellService.getSubTotalBetweenDates(startDate, endDate);
                Response response = new Response("Subtotal retrieved successfully", sellDTOS, false);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (Exception e) {
                Response response = new Response("Failed to retrieve subtotal", e.getMessage(), true);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

//        @GetMapping("/AllSubtotals")
//        public ResponseEntity<Response> getProductSubtotals (
//                @RequestParam String period){
//            try {
//                Double productSubtotals = sellService.getProductSubtotals(period);
//                Response response = new Response("Product subtotals retrieved successfully", productSubtotals, false);
//                return ResponseEntity.status(HttpStatus.OK).body(response);
//            } catch (Exception e) {
//                Response response = new Response("Failed to retrieve product subtotals", e.getMessage(), true);
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//            }
//        }

    }

