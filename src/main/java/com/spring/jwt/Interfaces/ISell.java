package com.spring.jwt.Interfaces;

import java.time.LocalDate;
import java.util.UUID;

public interface ISell {
    Double calculateProfitOrLoss(UUID sellingId);

    Double calculateTotalProfitOrLoss(LocalDate startDate, LocalDate endDate);
}
