

package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoice1;
import com.spring.jwt.Mapper.ProductWithInvoicesMapper;
import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;
import com.spring.jwt.dto.TransactionDTO;
import com.spring.jwt.entity.*;
import com.spring.jwt.repository.CustomersRepository;
import com.spring.jwt.repository.Invoice1Repository;
import com.spring.jwt.repository.ProductsRepository;
import com.spring.jwt.repository.SellRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class Invoice1Service implements IInvoice1 {

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
    public List<TransactionDTO> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
       List<Invoice1> invoice1= invoice1Repository.findInvoicesByDateRange(startDate, endDate);

        List<TransactionDTO> transactionDTOS=new ArrayList<>();
      for(int i=0;i < invoice1.size();i++){
          Invoice1 invoice=invoice1.get(i);
          TransactionDTO  transactionDTO=new TransactionDTO();
          transactionDTO.setInvoice1ID(invoice.getInvoice1ID());
          transactionDTO.setInvoiceNumber(invoice.getInvoiceNumber());
          transactionDTO.setInvoice1Date(invoice.getInvoice1Date());
          transactionDTO.setPaymentMethod(invoice.getPaymentMethod());
          transactionDTO.setGrandTotal(invoice.getGrandTotal());
          transactionDTO.setCustomerName(invoice.getCustomer().getFullName());
          transactionDTO.setCGstInRs(invoice.getCGstInRs());
          transactionDTO.setSGstInRs(invoice.getSGstInRs());
          transactionDTO.setCGstInPercent(invoice.getCGstInPercent());
          transactionDTO.setSGstInPercent(invoice.getSGstInPercent());

          transactionDTOS.add(transactionDTO);

      }
      return transactionDTOS;

    }

    @Override
    public List<Invoice1> getInvoicesByID(UUID id) {
        return invoice1Repository.findByInvoice1ID(id);
    }



    @Override
    public List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities, List<Double> sellingPrice) {
        // Validate input sizes
        if (productNames.size() != sellQuantities.size() || productNames.size() != sellingPrice.size()) {
            throw new RuntimeException("The number of product names, sell quantities, and selling prices must match.");
        }

        // Calculate total tax and base price
        Double totalTax = invoice1DTO.getCGstInRs() + invoice1DTO.getSGstInRs();
        Double basePrice = invoice1DTO.getGrandTotal() / (1 + (totalTax / 100));

        // Calculate GST in Rs
        Double gstInRs = Math.round(basePrice * (invoice1DTO.getCGstInRs() / 100) * 100.0) / 100.0;
        Double sgstInRs = Math.round(basePrice * (invoice1DTO.getSGstInRs() / 100) * 100.0) / 100.0;

        // Create and save customer
        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class);
        Customers savedCustomer = customersRepository.save(customer);

        // Create new invoice and save it before processing products
        Invoice1 invoice = new Invoice1();
        invoice.setInvoice1ID(invoice1DTO.getInvoice1ID());
        invoice.setInvoiceNumber(invoice1DTO.getInvoiceNumber());
        invoice.setInvoice1Date(invoice1DTO.getInvoice1Date());
        invoice.setInvoice1DueDate(invoice1DTO.getInvoice1DueDate());
        invoice.setCustomer(savedCustomer);
        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
        invoice.setCGstInRs(gstInRs);
        invoice.setSGstInRs(sgstInRs);
        invoice.setCGstInPercent(invoice1DTO.getCGstInRs());
        invoice.setSGstInPercent(invoice1DTO.getSGstInRs());
        invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());

        // Save the invoice to generate its ID
        Invoice1 savedInvoice = invoice1Repository.save(invoice);

        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            Double sellQuantity = sellQuantities.get(i);
            Double sellingPrices = sellingPrice.get(i);

            // Check for null selling price
            if (sellingPrices == null) {
                throw new RuntimeException("Selling price cannot be null for product: " + productName);
            }

            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
            if (foundProducts.isEmpty()) {
                throw new RuntimeException("Product with name '" + productName + "' not found.");
            }

            for (Products product : foundProducts) {
                Double totalStock = product.getStockQuantities()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .sum();

                if (totalStock < sellQuantity) {
                    throw new RuntimeException("Insufficient stock for product: " + productName);
                }

                // Update stock quantities
                List<Double> updatedStockQuantities = new ArrayList<>(product.getStockQuantities());
                Double remainingQuantity = sellQuantity;

                for (int j = 0; j < updatedStockQuantities.size() && remainingQuantity > 0; j++) {
                    Double availableStock = updatedStockQuantities.get(j);
                    if (availableStock >= remainingQuantity) {
                        updatedStockQuantities.set(j, availableStock - remainingQuantity);
                        remainingQuantity = 0.0;
                    } else {
                        updatedStockQuantities.set(j, 0.0);
                        remainingQuantity -= availableStock;
                    }
                }

                product.setStockQuantities(updatedStockQuantities);
                productsRepository.save(product); // Save updated product

                // Create and add ProductDetails to the invoice
                ProductDetails productDetails = new ProductDetails();
                productDetails.setProductID(product.getProductID());
                productDetails.setProductName(product.getProductName());
                productDetails.setSellingPrice(sellingPrices); // Use the selling price from the input
                productDetails.setDiscount(product.getDiscount());
                productDetails.setClothingType(product.getClothingType());
                productDetails.setSubTotalPrice(sellingPrices * sellQuantity); // Use sellingPrices for subtotal
                productDetails.setSellQuantity(sellQuantity);
                productDetails.setColor(product.getColor());
                productDetails.setSize(product.getSize());

                savedInvoice.getProducts().add(productDetails); // Add to saved invoice's products list

                // Create and save Sell entity
                Sell newSellEntity = new Sell();
                newSellEntity.setProductId(product.getProductID());
                newSellEntity.setProductSellQuantity(sellQuantity);
                newSellEntity.setDate(invoice1DTO.getInvoice1Date());
                newSellEntity.setProductSubtotal(sellingPrices * sellQuantity); // Use sellingPrices for subtotal
                newSellEntity.setInvoice1(savedInvoice); // Link to saved invoice
                sellRepository.save(newSellEntity);

                // Prepare DTO for the response
                ProductWithInvoicesDTO productDTO = modelMapper.map(product, ProductWithInvoicesDTO.class);
                productDTO.setSellQuantity(sellQuantity);
                productDTO.setInvoice1ID(savedInvoice.getInvoice1ID());
                productDTO.setInvoiceNumber(savedInvoice.getInvoiceNumber());
                productDTO.setInvoice1Date(savedInvoice.getInvoice1Date());
                productDTO.setInvoice1DueDate(savedInvoice.getInvoice1DueDate());
                productDTO.setGrandTotal(invoice1DTO.getGrandTotal());

                productDTO.setCGstInRs(gstInRs);
                productDTO.setSGstInRs(sgstInRs);

                productDTO.setCGstInPercent(invoice1DTO.getCGstInRs());
                productDTO.setSGstInPercent(invoice1DTO.getSGstInRs());
                
                productDTO.setPaymentMethod(invoice1DTO.getPaymentMethod());


                productsDTOList.add(productDTO);
            }
        }

        // Final save of the updated invoice with the products list
        invoice1Repository.save(savedInvoice);

        return productsDTOList;
    }




}