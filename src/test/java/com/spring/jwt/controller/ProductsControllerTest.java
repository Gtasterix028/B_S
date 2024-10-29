//package com.spring.jwt.controller;
//
//import com.spring.jwt.Interfaces.IProducts;
//import com.spring.jwt.dto.ProductsDTO;
//import com.spring.jwt.dto.Response;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class ProductsControllerTest {
//
//    @Mock
//    private IProducts productsService;
//
//    @InjectMocks
//    private ProductsController productsController;
//
//    private ProductsDTO productDTO;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        productDTO = new ProductsDTO();
//        productDTO.setProductID(1);
//        productDTO.setProductName("Sample Product");
//        productDTO.setDescription("Sample Description");
//        productDTO.setPrice(100.0);
//    }
//
//    @Test
//    public void testSaveProduct() {
//        when(productsService.saveInformation(productDTO)).thenReturn(productDTO);
//
//        ResponseEntity<Response> response = productsController.createProduct(productDTO);
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals("Product created successfully", response.getBody().getMessage());
//        assertEquals(productDTO, response.getBody().getObject());
//        verify(productsService, times(1)).saveInformation(productDTO);
//    }
//
//    @Test
//    public void testGetAllProducts() {
//        List<ProductsDTO> productsList = Arrays.asList(productDTO);
//        when(productsService.getAllProducts()).thenReturn(productsList);
//
//        ResponseEntity<Response> response = productsController.getAllProducts();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("All products retrieved successfully", response.getBody().getMessage());
//        assertEquals(productsList, response.getBody().getObject());
//        verify(productsService, times(1)).getAllProducts();
//    }
//
//    @Test
//    public void testGetProductById() {
//        when(productsService.getProductByID(1)).thenReturn(productDTO);
//
//        ResponseEntity<Response> response = productsController.getProductById(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Product retrieved successfully", response.getBody().getMessage());
//        assertEquals(productDTO, response.getBody().getObject());
//        verify(productsService, times(1)).getProductByID(1);
//    }
//
//    @Test
//    public void testUpdateProduct() {
//        ProductsDTO updatedProductDTO = new ProductsDTO();
//        updatedProductDTO.setProductID(1);
//        updatedProductDTO.setProductName("Updated Product");
//        updatedProductDTO.setDescription("Updated Description");
//        updatedProductDTO.setPrice(150.0);
//
//        when(productsService.updateAny(1, updatedProductDTO)).thenReturn(updatedProductDTO);
//
//        ResponseEntity<Response> response = productsController.updateProduct(1, updatedProductDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Product updated successfully", response.getBody().getMessage());
//        assertEquals(updatedProductDTO, response.getBody().getObject());
//        verify(productsService, times(1)).updateAny(1, updatedProductDTO);
//    }
//
//    @Test
//    public void testDeleteProduct() {
//        doNothing().when(productsService).deleteProduct(1);
//
//        ResponseEntity<Response> response = productsController.deleteProductByID(1);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Product deleted successfully", response.getBody().getMessage());
//        verify(productsService, times(1)).deleteProduct(1);
//    }
//}
