//package com.spring.jwt.Service;
//
//import com.spring.jwt.dto.Invoice1DTO;
//import com.spring.jwt.dto.ProductWithInvoicesDTO;
//import com.spring.jwt.entity.Customers;
//import com.spring.jwt.entity.Invoice1;
//import com.spring.jwt.entity.Products;
//import com.spring.jwt.entity.Sell;
//import com.spring.jwt.repository.CustomersRepository;
//import com.spring.jwt.repository.Invoice1Repository;
//import com.spring.jwt.repository.ProductsRepository;
//import com.spring.jwt.repository.SellRepository;
//import com.spring.jwt.service.Invoice1Service;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.modelmapper.ModelMapper;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//
//class Invoice1ServiceTest {
//
//    @Mock
//    private CustomersRepository customersRepository;
//
//    @Mock
//    private Invoice1Repository invoice1Repository;
//
//    @Mock
//    private ProductsRepository productsRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Mock
//    private SellRepository sellRepository;
//
//    @InjectMocks
//    private Invoice1Service invoiceService;
//
//    private Invoice1DTO invoice1DTO;
//    private Customers customer;
//    private Products product;
//    private Invoice1 invoice;
//    private Sell sell;
//    private List<String> productNames;
//     List<Double> sellQuantities;
//     private UUID invoiceId;
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//
//        customer = new Customers();
//        customer.setCustomerID(UUID.randomUUID());
//
//        invoice1DTO = new Invoice1DTO();
//         invoice1DTO.setInvoice1ID(UUID.randomUUID());
//        invoice1DTO.setInvoice1Date(LocalDate.now());
//        invoice1DTO.setInvoice1DueDate(LocalDate.now().plusDays(10));
//        invoice1DTO.setPaymentMethod("Credit Card");
//        invoice1DTO.setGrandTotal(1000.0);
//        invoice1DTO.setCGstInRs(18.0);
//        invoice1DTO.setSGstInRs(9.0);
//
//        product = new Products();
//        product.setProductID(UUID.randomUUID());
//        product.setProductName("Test Product");
//        product.setStockQuantities(Arrays.asList(10, 5));
//        product.setSellingPrice(100.0);
//        product.setDiscount(10.0);
//
//        invoice = new Invoice1();
//        invoice.setInvoice1ID(invoice1DTO.getInvoice1ID());
//        invoice.setInvoice1Date(invoice1DTO.getInvoice1Date());
//        invoice.setInvoice1DueDate(invoice1DTO.getInvoice1DueDate());
//        invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());
//        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
//
//        sell = new Sell();
//        sell.setProductId(product.getProductID());
//        sell.setProductSellQuantity(2.0);
//        sell.setDate(invoice1DTO.getInvoice1Date());
//
//        invoiceId = UUID.randomUUID();
//        invoice = new Invoice1();
//        invoice.setInvoice1ID(invoiceId);
//        invoice.setInvoice1DueDate(LocalDate.now().plusDays(30));
//        invoice.setInvoice1Date(LocalDate.now());
//        invoice.setPaymentMethod("Cash method");
//        invoice.setCGstInPercent(8.0);
//        invoice.setSGstInPercent(7.0);
//        invoice.setGrandTotal(200.0);
//        when(invoice1Repository.findByInvoice1ID(invoiceId)).thenReturn(Collections.singletonList(invoice));
//    }
//
//    @Test
//    void testGetInvoicesByID() {
//
//        List<Invoice1> result = invoiceService.getInvoicesByID(invoiceId);
//
//
//        assertEquals(1, result.size());
//        assertEquals(invoiceId, result.get(0).getInvoice1ID());
//        assertEquals(200.0, result.get(0).getGrandTotal());
//        assertEquals("Cash method",result.get(0).getPaymentMethod());
//        assertEquals(8.0,result.get(0).getCGstInPercent());
//        assertEquals(7.0,result.get(0).getSGstInPercent());
//    }
//
//    @Test
//    void testSaveInvoiceAndProducts_InsufficientStock() {
//
//        Products product = new Products();
//        product.setProductID(UUID.randomUUID());
//        product.setProductName("Product1");
//        product.setSellingPrice(200.00);
//        product.setStockQuantities(Arrays.asList(1)); // Only 1 unit in stock
//
//        when(productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc("Product1")).thenReturn(Collections.singletonList(product));
//
//
//        assertThrows(RuntimeException.class, () -> {
//            invoiceService.saveInvoiceAndProducts(invoice1DTO, Collections.singletonList("Product1"), Collections.singletonList(2.0));
//        });
//    }
//
//    @Test
//    void testSaveInvoiceAndProducts_ProductNotFound() {
//
//        when(productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc("Product1")).thenReturn(Collections.emptyList());
//
//
//        assertThrows(RuntimeException.class, () -> {
//            invoiceService.saveInvoiceAndProducts(invoice1DTO, Collections.singletonList("Product1"), Collections.singletonList(2.0));
//        });
//    }
//}
