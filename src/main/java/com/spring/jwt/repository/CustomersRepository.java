package com.spring.jwt.repository;

import com.spring.jwt.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomersRepository extends JpaRepository<Customers,UUID> {



}