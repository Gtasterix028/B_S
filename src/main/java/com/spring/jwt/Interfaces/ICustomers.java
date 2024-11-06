package com.spring.jwt.Interfaces;

import com.spring.jwt.dto.CustomersDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


public interface ICustomers {
    CustomersDTO getCustomerByID(UUID id);


    CustomersDTO saveInformation(UUID id ,CustomersDTO customersDTO);

    CustomersDTO saveInformation(CustomersDTO customersDTO);

    CustomersDTO updateAny(UUID id, CustomersDTO customersDTO);

    void deleteCustomer(UUID id);

    List<CustomersDTO> getAllCustomers();
}