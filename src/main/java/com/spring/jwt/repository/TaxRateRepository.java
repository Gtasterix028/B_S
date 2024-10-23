package com.spring.jwt.repository;

import com.spring.jwt.entity.TaxRates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRateRepository extends JpaRepository<TaxRates, Integer> {


}
