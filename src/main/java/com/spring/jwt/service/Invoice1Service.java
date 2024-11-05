package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoice1;
import com.spring.jwt.Mapper.ProductWithInvoicesMapper;
import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;
import com.spring.jwt.dto.SellDTO;
import com.spring.jwt.entity.Customers;
import com.spring.jwt.entity.Invoice1;
import com.spring.jwt.entity.Products;
import com.spring.jwt.entity.Sell;
import com.spring.jwt.repository.CustomersRepository;
import com.spring.jwt.repository.Invoice1Repository;
import com.spring.jwt.repository.ProductsRepository;
import com.spring.jwt.repository.SellRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Invoice1Service implements IInvoice1 {  // Change here to implement IInvoice1

    @Autowired
    private Invoice1Repository invoice1Repository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductWithInvoicesMapper mapper;

    @Autowired
    private SellRepository sellRepository;


    @Override
    @Transactional
    public List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities) {
        // Validate productNames and sellQuantities
        if (productNames.size() != sellQuantities.size()) {
            throw new RuntimeException("The number of product names must match the number of sell quantities.");
        }

        // Create a new Customer entity from the DTO
        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class);
        Customers savedCustomer = customersRepository.save(customer); // Save the customer

        // Create a new Invoice1 entity from the DTO
        Invoice1 invoice = new Invoice1();
        invoice.setInvoice1Date(invoice1DTO.getInvoice1Date());
        invoice.setInvoice1DueDate(invoice1DTO.getInvoice1DueDate());
        invoice.setCustomer(savedCustomer); // Set the saved customer

        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            Double sellQuantity = sellQuantities.get(i);

            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
            if (foundProducts.isEmpty()) {
                throw new RuntimeException("Product with name " + productName + " not found.");
            }

            for (Products product : foundProducts) {
                int totalStock = product.getStockQuantities().stream().mapToInt(Integer::intValue).sum();

                if (totalStock < sellQuantity) {
                    throw new RuntimeException("Insufficient stock for product: " + productName);
                }

                // Update stock quantities
                List<Integer> updatedStockQuantities = new ArrayList<>(product.getStockQuantities());
                int remainingQuantity = sellQuantity.intValue();

                for (int j = 0; j < updatedStockQuantities.size(); j++) {
                    if (remainingQuantity <= 0) break;

                    int availableStock = updatedStockQuantities.get(j);
                    if (availableStock >= remainingQuantity) {
                        updatedStockQuantities.set(j, availableStock - remainingQuantity);
                        remainingQuantity = 0;
                    } else {
                        updatedStockQuantities.set(j, 0);
                        remainingQuantity -= availableStock;
                    }
                }

                product.setStockQuantities(updatedStockQuantities);
                productsRepository.save(product); // Save updated product

                // Create a new Invoice1 entry for this product
                Invoice1 newInvoice = new Invoice1();
                newInvoice.setInvoice1Date(invoice.getInvoice1Date());
                newInvoice.setInvoice1DueDate(invoice.getInvoice1DueDate());
                newInvoice.setCustomer(savedCustomer);
                newInvoice.setProductID(product.getProductID());
                newInvoice.setProductName(product.getProductName());
                newInvoice.setActualPrice(product.getActualPrice());
                newInvoice.setSellingPrice(product.getSellingPrice());
                newInvoice.setDiscount(product.getDiscount());
                newInvoice.setClothingType(product.getClothingType());
                newInvoice.setSellQuantity(sellQuantity);
                newInvoice.setSubTotalPrice(product.getSellingPrice() * sellQuantity);

                // Save the invoice
                Invoice1 savedInvoice = invoice1Repository.save(newInvoice);

                // Create and save Sell entity
                Sell newSellEntity = new Sell();
                newSellEntity.setProductIdl(product.getProductID());
                newSellEntity.setProductSellQuantity(sellQuantity);
                newSellEntity.setDate(invoice1DTO.getInvoice1Date());
                newSellEntity.setInvoice1(savedInvoice);
                sellRepository.save(newSellEntity);

                // Prepare DTO for the response
                ProductWithInvoicesDTO productDTO = modelMapper.map(product, ProductWithInvoicesDTO.class);
                productDTO.setProductID(product.getProductID());
                productDTO.setProductName(product.getProductName());
                productDTO.setActualPrice(product.getActualPrice());
                productDTO.setSellingPrice(product.getSellingPrice());
                productDTO.setDiscount(product.getDiscount());
                productDTO.setClothingType(product.getClothingType());
                productDTO.setSubTotalPrice(newInvoice.getSubTotalPrice());
                productDTO.setSellQuantity(sellQuantity);
                productDTO.setInvoice1Date(newInvoice.getInvoice1Date());
                productDTO.setInvoice1DueDate(newInvoice.getInvoice1DueDate());

                productsDTOList.add(productDTO);
            }
        }

        return productsDTOList;
    }
}
