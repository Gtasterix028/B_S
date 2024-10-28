package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.ITax;
import com.spring.jwt.dto.Response;
import com.spring.jwt.dto.ShippingDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShippingControllerTest {

    @Mock
    private ITax taxService;

    @InjectMocks
    private ShippingController shippingController;

    private ShippingDto shippingDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        shippingDto = new ShippingDto();
    }

    @Test
    public void testRegister() {
        when(taxService.SaveShipping(shippingDto)).thenReturn(shippingDto);

        ResponseEntity<Response> response = shippingController.register(shippingDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Shipping Save succesfully", response.getBody().getMessage());
        assertEquals(shippingDto, response.getBody().getObject());
        verify(taxService, times(1)).SaveShipping(shippingDto);
    }

    @Test
    public void testUpdateShipping() {
        when(taxService.updateShipping(1, shippingDto)).thenReturn(shippingDto);

        ResponseEntity<Response> response = shippingController.updateShipping(1, shippingDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shipping updated successfully", response.getBody().getMessage());
        assertEquals(shippingDto, response.getBody().getObject());
        verify(taxService, times(1)).updateShipping(1, shippingDto);
    }

    @Test
    public void testGetShippingById() {
        when(taxService.getById(1)).thenReturn(shippingDto);

        ResponseEntity<Response> response = shippingController.getShippingById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shipping detail fetched successfully", response.getBody().getMessage());
        assertEquals(shippingDto, response.getBody().getObject());
        verify(taxService, times(1)).getById(1);
    }

    @Test
    public void testGetAllShipping() {

    }

    @Test
    public void testDeleteShippingById() {
        when(taxService.deleteById(1)).thenReturn(true);

        ResponseEntity<Response> response = shippingController.deleteShippingById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Shipping deleted successfully", response.getBody().getMessage());
        verify(taxService, times(1)).deleteById(1);
    }
}
