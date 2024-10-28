package com.spring.jwt.controller;
 import com.spring.jwt.Interfaces.IDiscount;
 import com.spring.jwt.dto.DiscountDto;
 import com.spring.jwt.dto.Response;
 import com.spring.jwt.entity.Customers;
 import com.spring.jwt.service.DiscountService;
 import org.junit.jupiter.api.BeforeEach;
 import org.junit.jupiter.api.Test;
 import org.mockito.InjectMocks;
 import org.mockito.Mock;
 import org.mockito.MockitoAnnotations;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;

 import java.util.*;

 import static org.junit.jupiter.api.Assertions.*;
 import static org.mockito.Mockito.*;

 public class DiscountControllerTest {

      @InjectMocks
      private DiscountController discountController;

     @Mock
     private IDiscount discount;


     @Mock
     private DiscountService discountService;

      @BeforeEach
     public void setUp() {
         MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testSaveDiscount() {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscountId(1);
        discountDto.setDiscountName("Summer Sale");
        discountDto.setDiscountValue(20.00);

        when(discount.saveDiscount(discountDto)).thenReturn(discountDto);


        ResponseEntity<Response> response = discountController.saveDiscount(discountDto);

        // Check if the response status is CREATED
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("SavedDiscount Added", response.getBody().getMessage());
        //verify(discount, times(1)).saveDiscount(discountDto);
    }

    @Test
    public void testGetById() {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscountName("Winter Sale");

        when(discount.getByID(1)).thenReturn(discountDto);

        ResponseEntity<Response> response = discountController.getById(1);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
       assertEquals("get by id succefully", Objects.requireNonNull(response.getBody()).getMessage());
       verify(discount, times(1)).getByID(1);
    }

    @Test
   public void testGetAll() {
      List<DiscountDto> discounts = Arrays.asList(new DiscountDto(), new DiscountDto());

        when(discount.getall()).thenReturn(discounts);

        ResponseEntity<Response> response = discountController.getAll();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("getAll successfully", response.getBody().getMessage());
        verify(discount, times(1)).getall();
    }

    @Test
   public void testGetAllNoDiscounts() {
         // List<DiscountDto> discountList  =Arrays.asList(new DiscountDto(),new DiscountDto());

        when(discount.getall()).thenReturn(Collections.emptyList());


        ResponseEntity<Response> response = discountController.getAll();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("getAll successfully", response.getBody().getMessage());
        assertTrue(((List<?>) response.getBody().getObject()).isEmpty());
       // verify(discount, times(1)).getall();
    }



    @Test
    public void testGetByIdNotFound() {
        when(discount.getByID(1)).thenThrow(new RuntimeException("Discount not found"));

        ResponseEntity<Response> response = discountController.getById(1);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("not get it", response.getBody().getMessage());
        verify(discount , times(1)).getByID(1);
    }

    @Test
    public void testSaveDiscountException() {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setDiscountName("Summer Sale");
        discountDto.setDiscountValue(20.00);

        when(discount.saveDiscount(discountDto)).thenThrow(new RuntimeException("Error saving discount"));

        ResponseEntity<Response> response = discountController.saveDiscount(discountDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Not Saved Discount", response.getBody().getMessage());
        verify(discount, times(1)).saveDiscount(discountDto);
    }
}
