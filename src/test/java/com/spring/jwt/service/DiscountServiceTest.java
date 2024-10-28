package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IDiscount;
import com.spring.jwt.controller.DiscountController;
import com.spring.jwt.dto.DiscountDto;
import com.spring.jwt.dto.Response;
import com.spring.jwt.repository.DiscountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceTest{

    @InjectMocks
    private DiscountController discountController;

    @Mock
    private DiscountService discountService;

    @Mock
    private DiscountRepository repository;

    @Mock
    private IDiscount discount;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveDiscountTest() {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscountId(1);
        discountDto.setDiscountValue(10.8);
        discountDto.setDiscountName("Birthday Bash"); // Fixed spelling

      //when(discountService.saveDiscount(discountDto)).thenReturn(discountDto);
     when(discount.saveDiscount(discountDto)).thenReturn(discountDto);
        ResponseEntity<Response> response = discountController.saveDiscount(discountDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Expected HTTP status to be CREATED");
        assertEquals("SavedDiscount Added", response.getBody().getMessage(), "Expected success message in response body");
    }
}
