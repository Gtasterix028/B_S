//package com.spring.jwt.service;
//
//import com.spring.jwt.Interfaces.IProducts;
//import com.spring.jwt.dto.ProductsDTO;
//import com.spring.jwt.entity.Products;
//import com.spring.jwt.repository.ProductsRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class ProductsServiceTest {
//
//    @Mock
//    private ProductsRepository productsRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @InjectMocks
//    private ProductsService productsService;
//
//    private Products product;
//    private ProductsDTO productDTO;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Initialize a sample product and product DTO
//        product = new Products();
//        product.setProductID(1);
//        product.setProductName("Sample Product");
//        product.setDescription("Sample Description");
//        product.setPrice(100.0);
//
//        productDTO = new ProductsDTO();
//        productDTO.setProductID(1);
//        productDTO.setProductName("Sample Product");
//        productDTO.setDescription("Sample Description");
//        productDTO.setPrice(100.0);
//    }
//
//    @Test
//    public void testGetProductByID() {
//        when(productsRepository.findById(1)).thenReturn(Optional.of(product));
//        when(modelMapper.map(product, ProductsDTO.class)).thenReturn(productDTO);
//
//        ProductsDTO foundProduct = productsService.getProductByID(1);
//
//        assertEquals(productDTO, foundProduct);
//        verify(productsRepository, times(1)).findById(1);
//    }
//
//    @Test
//    public void testSaveInformation() {
//        when(modelMapper.map(productDTO, Products.class)).thenReturn(product);
//        when(productsRepository.save(product)).thenReturn(product);
//        when(modelMapper.map(product, ProductsDTO.class)).thenReturn(productDTO);
//
//        ProductsDTO savedProduct = productsService.saveInformation(productDTO);
//
//        assertEquals(productDTO, savedProduct);
//        verify(productsRepository, times(1)).save(product);
//    }
//
//    @Test
//    public void testUpdateAny() {
//        when(productsRepository.findById(1)).thenReturn(Optional.of(product));
//        when(productsRepository.save(product)).thenReturn(product);
//        when(modelMapper.map(product, ProductsDTO.class)).thenReturn(productDTO);
//
//        ProductsDTO updatedProduct = productsService.updateAny(1, productDTO);
//
//        assertEquals(productDTO, updatedProduct);
//        verify(productsRepository, times(1)).findById(1);
//        verify(productsRepository, times(1)).save(product);
//    }
//
//    @Test
//    public void testDeleteProduct() {
//        when(productsRepository.findById(1)).thenReturn(Optional.of(product));
//        doNothing().when(productsRepository).delete(product);
//
//        productsService.deleteProduct(1);
//
//        verify(productsRepository, times(1)).findById(1);
//        verify(productsRepository, times(1)).delete(product);
//    }
//
//    @Test
//    public void testGetAllProducts() {
//        List<Products> productsList = Arrays.asList(product);
//        List<ProductsDTO> productsDTOList = Arrays.asList(productDTO);
//
//        when(productsRepository.findAll()).thenReturn(productsList);
//        when(modelMapper.map(product, ProductsDTO.class)).thenReturn(productDTO);
//
//        List<ProductsDTO> allProducts = productsService.getAllProducts();
//
//        assertEquals(productsDTOList, allProducts);
//        verify(productsRepository, times(1)).findAll();
//    }
//}
