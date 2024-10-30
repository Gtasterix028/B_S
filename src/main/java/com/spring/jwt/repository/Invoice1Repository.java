package com.spring.jwt.repository;

import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.Invoice1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface Invoice1Repository extends JpaRepository<Invoice1, UUID> {

}
