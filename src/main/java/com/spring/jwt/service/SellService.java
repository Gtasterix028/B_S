package com.spring.jwt.service;

import com.spring.jwt.Interfaces.ISell;
import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Sell;
import com.spring.jwt.repository.SellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class SellService implements ISell {

    @Autowired
    private SellRepository sellRepository;

    @Override
    // Method to calculate profit or loss for a specific sell
    public Double calculateProfitOrLoss(UUID sellingId) {
        // Fetch the Sell entity using sellingId
        Sell sell = sellRepository.findById(sellingId)
                .orElseThrow(() -> new RuntimeException("Sell not found with ID: " + sellingId));

        // Get the associated Invoice1 to access actualPrice
        Invoice1 invoice1 = sell.getInvoice1();  // Fetch associated Invoice1
        if (invoice1 == null) {
            throw new RuntimeException("Associated Invoice1 not found for Sell ID: " + sellingId);
        }

        // Get the actual price from the Invoice1 entity
        Double actualPrice = invoice1.getActualPrice();  // Fetch actualPrice from Invoice1

        if (actualPrice == null) {
            throw new RuntimeException("Actual price is missing in the associated Invoice1.");
        }

        // Calculate profit or loss using actual price and the quantity sold
        double profitOrLoss = sell.getProductSubtotal() - (actualPrice * sell.getProductSellQuantity());
        return profitOrLoss;
    }
    @Override
    // Method to calculate total profit or loss for all sales within a date range
    public Double calculateTotalProfitOrLoss(LocalDate startDate, LocalDate endDate) {
        List<Sell> sells = sellRepository.findAllByDateBetween(startDate, endDate);
        double totalProfitOrLoss = 0.0;

        for (Sell sell : sells) {
            // Fetch associated Invoice1 for each Sell
            Invoice1 invoice1 = sell.getInvoice1();  // Fetch associated Invoice1
            if (invoice1 == null) {
                throw new RuntimeException("Associated Invoice1 not found for Sell ID: " + sell.getSellingId());
            }

            // Get the actual price from Invoice1
            Double actualPrice = invoice1.getActualPrice();  // Fetch actualPrice from Invoice1

            if (actualPrice == null) {
                throw new RuntimeException("Actual price is missing in the associated Invoice1.");
            }

            // Calculate profit or loss for this specific sell
            double profitOrLoss = sell.getProductSubtotal() - (actualPrice * sell.getProductSellQuantity());
            totalProfitOrLoss += profitOrLoss;
        }

        return totalProfitOrLoss;
    }
}