package com.spring.jwt.repository;

import com.spring.jwt.entity.Sell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SellRepository extends JpaRepository<Sell, UUID>  {
}
