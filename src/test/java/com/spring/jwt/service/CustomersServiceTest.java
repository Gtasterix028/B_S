package com.spring.jwt.service;

import com.spring.jwt.dto.CustomersDTO;
import com.spring.jwt.entity.Customers;
import com.spring.jwt.repository.CustomersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomersServiceTest {

    @InjectMocks
    private CustomersService customersService;

    @Mock
    private CustomersRepository customersRepository;

    @Mock
    private ModelMapper modelMapper;

    private Customers customer;
    private CustomersDTO customerDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customers();
        customer.setCustomerID(1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhone("123456789");
        customer.setAddress("123 Main St");

        customerDTO = new CustomersDTO();
        customerDTO.setFirstName("John");
        customerDTO.setLastName("Doe");
        customerDTO.setEmail("john.doe@example.com");
        customerDTO.setPhone("123456789");
        customerDTO.setAddress("123 Main St");
    }

    @Test
    void getCustomerByID_ShouldReturnCustomerDTO_WhenCustomerExists() {
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer, CustomersDTO.class)).thenReturn(customerDTO);

        CustomersDTO foundCustomer = customersService.getCustomerByID(1);

        assertEquals(customerDTO.getFirstName(), foundCustomer.getFirstName());
        verify(customersRepository, times(1)).findById(1);
    }

    @Test
    void saveInformation_ShouldReturnSavedCustomerDTO() {
        when(modelMapper.map(customerDTO, Customers.class)).thenReturn(customer);
        when(customersRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomersDTO.class)).thenReturn(customerDTO);

        CustomersDTO savedCustomer = customersService.saveInformation(customerDTO);

        assertEquals(customerDTO.getFirstName(), savedCustomer.getFirstName());
        verify(customersRepository, times(1)).save(customer);
    }

    @Test
    void updateAny_ShouldReturnUpdatedCustomerDTO_WhenCustomerExists() {
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customersRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomersDTO.class)).thenReturn(customerDTO);

        CustomersDTO updatedCustomer = customersService.updateAny(1, customerDTO);

        assertEquals(customerDTO.getFirstName(), updatedCustomer.getFirstName());
        verify(customersRepository, times(1)).findById(1);
        verify(customersRepository, times(1)).save(customer);
    }

    @Test
    void deleteCustomer_ShouldDeleteCustomer_WhenCustomerExists() {
        when(customersRepository.findById(1)).thenReturn(Optional.of(customer));

        customersService.deleteCustomer(1);

        verify(customersRepository, times(1)).delete(customer);
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomerDTOs() {
        when(customersRepository.findAll()).thenReturn(List.of(customer));
        when(modelMapper.map(customer, CustomersDTO.class)).thenReturn(customerDTO);

        List<CustomersDTO> customersList = customersService.getAllCustomers();

        assertFalse(customersList.isEmpty());
        assertEquals(1, customersList.size());
        assertEquals(customerDTO.getFirstName(), customersList.get(0).getFirstName());
        verify(customersRepository, times(1)).findAll();
    }
}

