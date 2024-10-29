//package com.spring.jwt.repository;
//
//import com.spring.jwt.entity.Products;
//import com.spring.jwt.service.ProductsService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class ProductsRepositoryTest {
//
//    @Mock
//    private ProductsRepository productsRepository;
//
//    @InjectMocks
//    private ProductsService productsService; // Assuming you might use this in a service context
//
//    private Products product;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        product = new Products();
//        product.setProductID(1);
//        product.setProductName("Sample Product");
//        product.setDescription("Sample Description");
//        product.setPrice(100.0);
//    }
//
//    @Test
//    public void testFindById() {
//        when(productsRepository.findById(1)).thenReturn(Optional.of(product));
//
//        Optional<Products> foundProduct = productsRepository.findById(1);
//
//        assertEquals(true, foundProduct.isPresent());
//        assertEquals(product, foundProduct.get());
//        verify(productsRepository, times(1)).findById(1);
//    }
//
//    @Test
//    public void testFindAll() {
//        List<Products> productsList = Arrays.asList(product);
//        when(productsRepository.findAll()).thenReturn(productsList);
//
//        List<Products> allProducts = productsRepository.findAll();
//
//        assertEquals(1, allProducts.size());
//        assertEquals(product, allProducts.get(0));
//        verify(productsRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testSave() {
//        when(productsRepository.save(product)).thenReturn(product);
//
//        Products savedProduct = productsRepository.save(product);
//
//        assertEquals(product, savedProduct);
//        verify(productsRepository, times(1)).save(product);
//    }
//
//    @Test
//    public void testDelete() {
//        doNothing().when(productsRepository).delete(product);
//
//        productsRepository.delete(product);
//
//        verify(productsRepository, times(1)).delete(product);
//    }
//}
