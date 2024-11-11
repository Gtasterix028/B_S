

package com.spring.jwt.service;

import com.spring.jwt.Interfaces.IInvoice1;
import com.spring.jwt.Mapper.ProductWithInvoicesMapper;
import com.spring.jwt.dto.Invoice1DTO;
import com.spring.jwt.dto.ProductWithInvoicesDTO;
import com.spring.jwt.dto.TransactionDTO;
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


//    @Override
//    @Transactional
//    public List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities) {
//
//        if (productNames.size() != sellQuantities.size()) {
//            throw new RuntimeException("The number of product names must match the number of sell quantities.");
//        }
//
//        // Create and save customer
//        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class);
//        Customers savedCustomer = customersRepository.save(customer);
//
//        // Create new invoice
//        Invoice1 invoice = new Invoice1();
//        invoice.setInvoice1ID(invoice1DTO.getInvoice1ID());
//        invoice.setInvoice1Date(invoice1DTO.getInvoice1Date());
//        invoice.setInvoice1DueDate(invoice1DTO.getInvoice1DueDate());
//        invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());
////        invoice.setGst(invoice.getGst());
////        invoice.setSGst(invoice.getSGst());
//        invoice.setCustomer(savedCustomer);
//        invoice.setSubTotalPrice(invoice1DTO.getSubTotalPrice());
//
//        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();
//
//        for (int i = 0; i < productNames.size(); i++) {
//            String productName = productNames.get(i);
//            Double sellQuantity = sellQuantities.get(i);
//
//            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
//            if (foundProducts.isEmpty()) {
//                throw new RuntimeException("Product with name '" + productName + "' not found.");
//            }
//
//            for (Products product : foundProducts) {
//                int totalStock = product.getStockQuantities().stream().mapToInt(Integer::intValue).sum();
//
//                if (totalStock < sellQuantity) {
//                    throw new RuntimeException("Insufficient stock for product: " + productName);
//                }
//
//                // Update stock quantities
//                List<Integer> updatedStockQuantities = new ArrayList<>(product.getStockQuantities());
//                int remainingQuantity = sellQuantity.intValue();
//
//                for (int j = 0; j < updatedStockQuantities.size() && remainingQuantity > 0; j++) {
//                    int availableStock = updatedStockQuantities.get(j);
//                    if (availableStock >= remainingQuantity) {
//                        updatedStockQuantities.set(j, availableStock - remainingQuantity);
//                        remainingQuantity = 0;
//                    } else {
//                        updatedStockQuantities.set(j, 0);
//                        remainingQuantity -= availableStock;
//                    }
//                }
//
//                product.setStockQuantities(updatedStockQuantities);
//                productsRepository.save(product); // Save updated product
//
//                // Create a new Invoice1 entry for this product
//                invoice.setProductID(product.getProductID());
//                invoice.setProductName(product.getProductName());
//                invoice.setSellingPrice(product.getSellingPrice());
//                invoice.setDiscount(product.getDiscount());
//                invoice.setClothingType(product.getClothingType());
//                invoice.setSellQuantity(sellQuantity);
//                invoice.setSubTotalPrice(invoice1DTO.getSubTotalPrice());
//
//                // Save the invoice
//                Invoice1 savedInvoice = invoice1Repository.save(invoice);
//
//                // Create and save Sell entity
//                Sell newSellEntity = new Sell();
//                newSellEntity.setProductId(product.getProductID());
//                newSellEntity.setProductSellQuantity(sellQuantity);
//                newSellEntity.setDate(invoice1DTO.getInvoice1Date());
//                newSellEntity.setProductSubtotal(invoice.getSubTotalPrice());
//                newSellEntity.setInvoice1(savedInvoice);
//                sellRepository.save(newSellEntity);
//
//                // Prepare DTO for the response
//                ProductWithInvoicesDTO productDTO = modelMapper.map(product, ProductWithInvoicesDTO.class);
//                productDTO.setProductID(product.getProductID());
//                productDTO.setProductName(product.getProductName());
//                productDTO.setSellingPrice(product.getSellingPrice());
//                productDTO.setDiscount(product.getDiscount());
//                        productDTO.setClothingType(product.getClothingType());
//                productDTO.setSubTotalPrice(invoice.getSubTotalPrice());
//                productDTO.setSellQuantity(sellQuantity);
//                productDTO.setInvoice1ID(savedInvoice.getInvoice1ID());
//                productDTO.setInvoice1Date(savedInvoice.getInvoice1Date());
//                productDTO.setInvoice1DueDate(savedInvoice.getInvoice1DueDate());
//                productDTO.setGrandTotal(invoice1DTO.getGrandTotal());
//
//                productsDTOList.add(productDTO);
//
//            }
//        }
//
//        // Set Grand Total from the request into the invoice and save
//        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
//        invoice1Repository.save(invoice);
//
//        // Optionally, update Grand Total in Sell entities
//        List<Sell> sells = sellRepository.findByInvoice1(invoice);
//        for (Sell sell : sells) {
//            sell.setGrandTotal(invoice1DTO.getGrandTotal());
//            sellRepository.save(sell);
//        }
//
//        return productsDTOList;
//    }
//
    @Override
    public List<TransactionDTO> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
       List<Invoice1> invoice1= invoice1Repository.findInvoicesByDateRange(startDate, endDate);

        List<TransactionDTO> transactionDTOS=new ArrayList<>();
      for(int i=0;i < invoice1.size();i++){
          Invoice1 invoice=invoice1.get(i);
          TransactionDTO  transactionDTO=new TransactionDTO();
          transactionDTO.setInvoice1ID(invoice.getInvoice1ID());
          transactionDTO.setInvoice1Date(invoice.getInvoice1Date());
          transactionDTO.setPaymentMethod(invoice.getPaymentMethod());
          transactionDTO.setGrandTotal(invoice.getGrandTotal());
          transactionDTO.setCustomerName(invoice.getCustomer().getFirstName());
          transactionDTO.setGst(invoice.getGst());
          transactionDTO.setSGst(invoice.getSGst());
          transactionDTOS.add(transactionDTO);

      }
      return transactionDTOS;

    }

//    @Override
//    @Transactional
//    public List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities) {
//
//        if (productNames.size() != sellQuantities.size()) {
//            throw new RuntimeException("The number of product names must match the number of sell quantities.");
//        }
//
//        // Create and save customer
//        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class);
//        Customers savedCustomer = customersRepository.save(customer);
//
//        // Create new invoice
//        Invoice1 invoice = new Invoice1();
//        invoice.setInvoice1ID(invoice1DTO.getInvoice1ID());
//        invoice.setInvoice1Date(invoice1DTO.getInvoice1Date());
//        invoice.setInvoice1DueDate(invoice1DTO.getInvoice1DueDate());
//        invoice.setCustomer(savedCustomer);
//        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
//        invoice.setGst(invoice1DTO.getGst());
//        invoice.setSGst(invoice1DTO.getSGst());
//        invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());
//
//        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();
//
//        for (int i = 0; i < productNames.size(); i++) {
//            String productName = productNames.get(i);
//            Double sellQuantity = sellQuantities.get(i);
//
//            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
//            if (foundProducts.isEmpty()) {
//                throw new RuntimeException("Product with name '" + productName + "' not found.");
//            }
//
//            for (Products product : foundProducts) {
//                int totalStock = product.getStockQuantities().stream().mapToInt(Integer::intValue).sum();
//
//                if (totalStock < sellQuantity) {
//                    throw new RuntimeException("Insufficient stock for product: " + productName);
//                }
//
//                // Update stock quantities
//                List<Integer> updatedStockQuantities = new ArrayList<>(product.getStockQuantities());
//                int remainingQuantity = sellQuantity.intValue();
//
//                for (int j = 0; j < updatedStockQuantities.size() && remainingQuantity > 0; j++) {
//                    int availableStock = updatedStockQuantities.get(j);
//                    if (availableStock >= remainingQuantity) {
//                        updatedStockQuantities.set(j, availableStock - remainingQuantity);
//                        remainingQuantity = 0;
//                    } else {
//                        updatedStockQuantities.set(j, 0);
//                        remainingQuantity -= availableStock;
//                    }
//                }
//
//                product.setStockQuantities(updatedStockQuantities);
//                productsRepository.save(product); // Save updated product
//
//                // Create a new Invoice1 entry for this product
//                invoice.setProductID(product.getProductID());
//                invoice.setProductName(product.getProductName());
//                invoice.setSellingPrice(product.getSellingPrice());
//                invoice.setDiscount(product.getDiscount());
//                invoice.setClothingType(product.getClothingType());
//                invoice.setSellQuantity(sellQuantity);
//                invoice.setSubTotalPrice(product.getSellingPrice() * sellQuantity);
//                invoice.setGrandTotal(invoice1DTO.getGrandTotal());
//                invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());
//                invoice.setGst(invoice1DTO.getGst());
//                invoice.setSGst(invoice1DTO.getSGst());
//
//                // Save the invoice
//                Invoice1 savedInvoice = invoice1Repository.save(invoice);
//
//                // Create and save Sell entity
//                Sell newSellEntity = new Sell();
//                newSellEntity.setProductId(product.getProductID());
//                newSellEntity.setProductSellQuantity(sellQuantity);
//                newSellEntity.setDate(invoice1DTO.getInvoice1Date());
//                newSellEntity.setProductSubtotal(invoice.getSubTotalPrice());
//                newSellEntity.setInvoice1(savedInvoice);
//                newSellEntity.setGrandTotal(invoice.getGrandTotal());
//                sellRepository.save(newSellEntity);
//
//                // Prepare DTO for the response
//                ProductWithInvoicesDTO productDTO = modelMapper.map(product, ProductWithInvoicesDTO.class);
//                productDTO.setProductID(product.getProductID());
//                productDTO.setProductName(product.getProductName());
//                productDTO.setSellingPrice(product.getSellingPrice());
//                productDTO.setDiscount(product.getDiscount());
//                productDTO.setClothingType(product.getClothingType());
//                productDTO.setSubTotalPrice(invoice.getSubTotalPrice());
//                productDTO.setSellQuantity(sellQuantity);
//                productDTO.setInvoice1ID(savedInvoice.getInvoice1ID());
//                productDTO.setInvoice1Date(savedInvoice.getInvoice1Date());
//                productDTO.setInvoice1DueDate(savedInvoice.getInvoice1DueDate());
//                productDTO.setGrandTotal(invoice1DTO.getGrandTotal());
//                productDTO.setGst(invoice1DTO.getGst());
//                productDTO.setSGst(invoice1DTO.getSGst());
//                productDTO.setPaymentMethod(invoice1DTO.getPaymentMethod());
//
//                productsDTOList.add(productDTO);
//            }
//        }
//
//        // Set Grand Total from the request into the invoice and save
//        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
//        invoice1Repository.save(invoice);
//
//        // Optionally, update Grand Total in Sell entities
//        List<Sell> sells = sellRepository.findByInvoice1(invoice);
//        for (Sell sell : sells) {
//            sell.setGrandTotal(invoice1DTO.getGrandTotal());
//            sellRepository.save(sell);
//        }
//
//        return productsDTOList;
//    }

    @Override
    @Transactional
    public List<ProductWithInvoicesDTO> saveInvoiceAndProducts(Invoice1DTO invoice1DTO, List<String> productNames, List<Double> sellQuantities) {

        if (productNames.size() != sellQuantities.size()) {
            throw new RuntimeException("The number of product names must match the number of sell quantities.");
        }


        Double totalTax = invoice1DTO.getGst() + invoice1DTO.getSGst();
        Double basePrice = invoice1DTO.getGrandTotal() / (1 + (totalTax / 100));

        Double gstInRs = Math.round(basePrice * (invoice1DTO.getGst() / 100) * 100.0) / 100.0;
        Double SgstInRs = Math.round(basePrice * (invoice1DTO.getSGst() / 100) * 100.0) / 100.0;


        // Create and save customer
        Customers customer = modelMapper.map(invoice1DTO.getCustomer(), Customers.class);
        Customers savedCustomer = customersRepository.save(customer);

        // Create new invoice
        Invoice1 invoice = new Invoice1();
        invoice.setInvoice1ID(invoice1DTO.getInvoice1ID());
        invoice.setInvoice1Date(invoice1DTO.getInvoice1Date());
        invoice.setInvoice1DueDate(invoice1DTO.getInvoice1DueDate());
        invoice.setCustomer(savedCustomer);
        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
        invoice.setGst(gstInRs);
        invoice.setSGst(SgstInRs);
        invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());

        List<ProductWithInvoicesDTO> productsDTOList = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            Double sellQuantity = sellQuantities.get(i);

            List<Products> foundProducts = productsRepository.findByProductNameContainingIgnoreCaseOrderByProductNameAsc(productName);
            if (foundProducts.isEmpty()) {
                throw new RuntimeException("Product with name '" + productName + "' not found.");
            }

            for (Products product : foundProducts) {
                int totalStock = product.getStockQuantities().stream().mapToInt(Integer::intValue).sum();

                if (totalStock < sellQuantity) {
                    throw new RuntimeException("Insufficient stock for product: " + productName);
                }

                // Update stock quantities
                List<Integer> updatedStockQuantities = new ArrayList<>(product.getStockQuantities());
                int remainingQuantity = sellQuantity.intValue();

                for (int j = 0; j < updatedStockQuantities.size() && remainingQuantity > 0; j++) {
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
                invoice.setProductID(product.getProductID());
                invoice.setProductName(product.getProductName());
                invoice.setSellingPrice(product.getSellingPrice());
                invoice.setDiscount(product.getDiscount());
                invoice.setClothingType(product.getClothingType());
                invoice.setSellQuantity(sellQuantity);
                invoice.setSubTotalPrice(product.getSellingPrice() * sellQuantity);
                invoice.setGrandTotal(invoice1DTO.getGrandTotal());
                invoice.setPaymentMethod(invoice1DTO.getPaymentMethod());
                invoice.setGst(gstInRs);
                invoice.setSGst(SgstInRs);

                // Save the invoice
                Invoice1 savedInvoice = invoice1Repository.save(invoice);

                // Create and save Sell entity
                Sell newSellEntity = new Sell();
                newSellEntity.setProductId(product.getProductID());
                newSellEntity.setProductSellQuantity(sellQuantity);
                newSellEntity.setDate(invoice1DTO.getInvoice1Date());
                newSellEntity.setProductSubtotal(invoice.getSubTotalPrice());
                newSellEntity.setInvoice1(savedInvoice);
              //  newSellEntity.setGrandTotal(invoice1DTO.getGrandTotal());
                sellRepository.save(newSellEntity);

                // Prepare DTO for the response
                ProductWithInvoicesDTO productDTO = modelMapper.map(product, ProductWithInvoicesDTO.class);
                productDTO.setProductID(product.getProductID());
                productDTO.setProductName(product.getProductName());
                productDTO.setSellingPrice(product.getSellingPrice());
                productDTO.setDiscount(product.getDiscount());
                productDTO.setClothingType(product.getClothingType());
                productDTO.setSubTotalPrice(invoice.getSubTotalPrice());
                productDTO.setSellQuantity(sellQuantity);
                productDTO.setInvoice1ID(savedInvoice.getInvoice1ID());
                productDTO.setInvoice1Date(savedInvoice.getInvoice1Date());
                productDTO.setInvoice1DueDate(savedInvoice.getInvoice1DueDate());
                productDTO.setGrandTotal(invoice1DTO.getGrandTotal());
                productDTO.setGst(gstInRs);
                productDTO.setSGst(SgstInRs);
                productDTO.setPaymentMethod(invoice1DTO.getPaymentMethod());

                productsDTOList.add(productDTO);
            }
        }

        // Set Grand Total from the request into the invoice and save
        invoice.setGrandTotal(invoice1DTO.getGrandTotal());
        invoice1Repository.save(invoice);

//        // Optionally, update Grand Total in Sell entities
//        List<Sell> sells = sellRepository.findByInvoice1(invoice);
//        for (Sell sell : sells) {
//            sell.setGrandTotal(invoice1DTO.getGrandTotal());
//            sellRepository.save(sell);
//        }

        return productsDTOList;
    }


    @Override
    public List<Invoice1> getInvoicesByID(UUID id) {
        return invoice1Repository.findByInvoice1ID(id);
    }


}