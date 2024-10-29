//package com.spring.jwt.controller;
//import com.spring.jwt.Interfaces.ICustomers;
//import com.spring.jwt.dto.CustomersDTO;
//import com.spring.jwt.dto.Response;
//import com.spring.jwt.controller.CustomersController;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class CustomersControllerTest {
//
//    @Mock
//    private ICustomers customersService;
//
//    @InjectMocks
//    private CustomersController customersController;
//
//    private CustomersDTO customerDTO;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        customerDTO = new CustomersDTO();
//        customerDTO.setFirstName("John");
//        customerDTO.setLastName("Doe");
//        customerDTO.setEmail("john.doe@example.com");
//    }
//
//    @Test
//    public void testGetCustomerById_Success() {
//        // Arrange
//        when(customersService.getCustomerByID(1)).thenReturn(customerDTO);
//
//        // Act
//        ResponseEntity<Response> responseEntity = customersController.getCustomerById(1);
//
//        // Assert
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Customer retrieved successfully", responseEntity.getBody().getMessage());
//        verify(customersService, times(1)).getCustomerByID(1);
//    }
//
//    @Test
//    public void testGetAllCustomers_Success() {
//        // Arrange
//        List<CustomersDTO> customerList = new ArrayList<>();
//        customerList.add(customerDTO);
//        when(customersService.getAllCustomers()).thenReturn(customerList);
//
//        // Act
//        ResponseEntity<Response> responseEntity = customersController.getAllCustomers();
//
//        // Assert
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("All customers retrieved successfully", responseEntity.getBody().getMessage());
//        verify(customersService, times(1)).getAllCustomers();
//    }
//
//    @Test
//    public void testCreateCustomer_Success() {
//        // Arrange
//        when(customersService.saveInformation(customerDTO)).thenReturn(customerDTO);
//
//        // Act
//        ResponseEntity<Response> responseEntity = customersController.createCustomer(customerDTO);
//
//        // Assert
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals("Customer created successfully", responseEntity.getBody().getMessage());
//        verify(customersService, times(1)).saveInformation(customerDTO);
//    }
//
//    @Test
//    public void testDeleteCustomer_Success() {
//        // Act
//        ResponseEntity<Response> responseEntity = customersController.deleteCustomerBYID(1);
//
//        // Assert
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Customer deleted successfully", responseEntity.getBody().getMessage());
//        verify(customersService, times(1)).deleteCustomer(1);
//    }
//}
