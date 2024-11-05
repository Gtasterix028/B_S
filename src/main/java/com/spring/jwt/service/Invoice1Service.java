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
    public Invoice1DTO saveInformation(Invoice1DTO invoice1DTO) {
        Invoice1 invoice = modelMapper.map(invoice1DTO, Invoice1.class);
        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class);

        Customers savedCustomer = customersRepository.save(customer);
        invoice.setCustomer(savedCustomer);

        Invoice1 savedInvoice = invoice1Repository.save(invoice);
        return modelMapper.map(savedInvoice, Invoice1DTO.class);
    }


    @Override
    @Transactional
    public List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities) {
        Invoice1DTO savedInvoiceDTO = saveInformation(invoice1DTO);

        Optional<Invoice1> invoiceOptional = invoice1Repository.findById(savedInvoiceDTO.getInvoice1ID());
        if (!invoiceOptional.isPresent()) {
            throw new RuntimeException("Invoice with ID " + savedInvoiceDTO.getInvoice1ID() + " not found.");
        }

        Invoice1 existingInvoice = invoiceOptional.get();

        if (productNames.size() != sellQuantities.size()) {
            throw new RuntimeException("The number of product names must match the number of sell quantities.");
        }

        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            Double sellQuantity = sellQuantities.get(i);

            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
            if (foundProducts.isEmpty()) {
                throw new RuntimeException("Product with name " + productName + " not found.");
            }

            for (Products product : foundProducts) {

                int totalStock = 0;
                for (Integer quantity : product.getStockQuantities()) {
                    totalStock += quantity; // Calculate total stock using a for-each loop
                }

                if (totalStock < sellQuantity) {
                    throw new RuntimeException("Insufficient stock for product: " + productName);
                }

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
                productsRepository.save(product);

                Double subTotalPrice = product.getSellingPrice() * sellQuantity;

                ProductWithInvoicesDTO productDTO = modelMapper.map(product, ProductWithInvoicesDTO.class);
                productDTO.setProductID(product.getProductID());
                productDTO.setProductName(product.getProductName());
                productDTO.setActualPrice(product.getActualPrice());
                productDTO.setSellingPrice(product.getSellingPrice());
                productDTO.setDiscount(product.getDiscount());
                productDTO.setClothingType(product.getClothingType());
                productDTO.setSubTotalPrice(product.getSubTotalPrice());
                productDTO.setSellQuantity(sellQuantity);
                productDTO.setInvoice1Date(existingInvoice.getInvoice1Date());
                productDTO.setInvoice1DueDate(existingInvoice.getInvoice1DueDate());
                productDTO.setSubmit(existingInvoice.getSubmit());

                Invoice1 newInvoice = mapper.toInvoiceEntity(productDTO, product, existingInvoice);
                invoice1Repository.save(newInvoice);

                Sell newSellEntity = new Sell();
                newSellEntity.setProductIdl(product.getProductID());
                newSellEntity.setProductSellQuantity(sellQuantity);
                newSellEntity.setDate(invoice1DTO.getInvoice1Date());
                newSellEntity.setInvoice1(newInvoice);
                sellRepository.save(newSellEntity);

                ProductWithInvoicesDTO savedProductDTO = mapper.toProductWithInvoicesDTO(product, newInvoice);
                productsDTOList.add(savedProductDTO);
            }
        }

        return productsDTOList;
    }

}







