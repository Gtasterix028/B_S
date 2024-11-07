package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ISell;
import com.spring.jwt.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    private ISell sellService;

    // Endpoint to calculate profit or loss for a specific sale
    @GetMapping("/{sellingId}/profit-or-loss")
    public ResponseEntity<Double> getProfitOrLoss(@PathVariable UUID sellingId) {
        Double profitOrLoss = sellService.calculateProfitOrLoss(sellingId);
        return ResponseEntity.ok(profitOrLoss);
    }

    // Endpoint to calculate total profit or loss within a date range

    // Endpoint to calculate total profit or loss for sales within a date range
    @GetMapping("/profit-or-loss")
    public ResponseEntity<Double> getTotalProfitOrLoss(@RequestParam LocalDate startDate,
                                                       @RequestParam LocalDate endDate) {
        Double totalProfitOrLoss = sellService.calculateTotalProfitOrLoss(startDate, endDate);
        return ResponseEntity.ok(totalProfitOrLoss);
    }
}
