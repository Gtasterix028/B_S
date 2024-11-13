package com.spring.jwt.controller;

import com.spring.jwt.Interfaces.IProducts;
import com.spring.jwt.dto.ProductsDTO;
import com.spring.jwt.dto.Response;
import com.spring.jwt.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private IProducts productsInterface;

    @GetMapping("/getByID")
    public ResponseEntity<Response> getProductById(@RequestParam UUID id) {
        try {
            ProductsDTO productsDTO = productsInterface.getProductByID(id);
            return ResponseEntity.ok(new Response("Product retrieved successfully", productsDTO, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllProducts() {
        try {
            List<ProductsDTO> productsList = productsInterface.getAllProducts();
            return ResponseEntity.ok(new Response("All products retrieved successfully", productsList, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @PostMapping("/saveInformation")
    public ResponseEntity<Response> createProduct(@RequestBody ProductsDTO productsDTO) {
        try {
            ProductsDTO createdProduct = productsInterface.saveInformation(productsDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response("Product created successfully", createdProduct, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @PatchMapping("/updateAny")
    public ResponseEntity<Response> updateProduct(@RequestParam UUID id, @RequestBody ProductsDTO productsDTO) {
        try {
            ProductsDTO updatedProduct = productsInterface.updateAny(id, productsDTO);
            return ResponseEntity.ok(new Response("Product updated successfully", updatedProduct, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @DeleteMapping("/deleteByID")
    public ResponseEntity<Response> deleteProductByID(@RequestParam UUID id) {
        try {
            productsInterface.deleteProduct(id);
            return ResponseEntity.ok(new Response("Product deleted successfully", null, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductsDTO>> getProducts(
            @RequestParam(required = false) UUID productId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {

        List<ProductsDTO> products = productsInterface.getProducts(productId, productName, description, price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductsDTO>> searchProducts(@RequestParam String searchCharacter) {
        List<ProductsDTO> products=productsInterface.searchProductsByName(searchCharacter);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/getByClothingType")
    public ResponseEntity<Response> getProductsByClothingType(@RequestParam String ClothingType) {
        try {
            List<ProductsDTO> productsList = productsInterface.getProductsByClothingType(ClothingType);
            return ResponseEntity.ok(new Response("Products retrieved successfully", productsList, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @GetMapping("/totalStockQuantity")
    public ResponseEntity<Response> getTotalStockQuantity() {
        try {
            Integer totalStock = productsInterface.getTotalStockQuantity();
            return ResponseEntity.ok(new Response("Total stock quantity retrieved successfully", totalStock, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @GetMapping("/stockQuantityByProductId")
    public ResponseEntity<Response> getStockQuantityByProductId(@RequestParam UUID productId) {
        try {
            Integer stockQuantity = productsInterface.getStockQuantityByProductId(productId);
            return ResponseEntity.ok(new Response("Stock quantity for product retrieved successfully", stockQuantity, false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

    @PostMapping("/saveMultipleInformation")
    public ResponseEntity<Response> createProductsList(@RequestBody List<ProductsDTO> productsDTOList){
        try{
            List<ProductsDTO> list=productsInterface.saveProduct(productsDTOList);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Response("product list get", list,false));

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response("An error occurred", e.getMessage(), true));
        }
    }

//    @GetMapping("/SortByName")
//    public ResponseEntity<Response> SortByName(@RequestParam String productName){
//        try {
//            Products products = productsInterface.SortByName(productName);
//            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sort Succesfuly", products, false));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.OK).body(new Response("Sort Failed", e.getMessage(), true));
//        }
//    }
    @GetMapping("/filter")
    public ResponseEntity<List<ProductsDTO>> getProductsByFilter(
            @RequestParam String clothingType,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order)

            {
        List<ProductsDTO> products = productsInterface.getProductsByFilter(clothingType, sortBy, order);
        return ResponseEntity.ok(products);
    }


//    @GetMapping("/filterStock")
//    public ResponseEntity<List<ProductsDTO>>getProductByStockQuantity(
//        @RequestParam(required = false, defaultValue = "quantity") Integer stockQuantities)
//
//        {
//            List<ProductsDTO> stockQuantity = productsInterface.getProductsByFilterstock(stockQuantities);
//            return ResponseEntity.ok(stockQuantity);



    @GetMapping("/filterStock")
    public ResponseEntity<List<ProductsDTO>> getProductByStockQuantity(
            @RequestParam(defaultValue = "asc") String order) {

        List<ProductsDTO> stockQuantity = productsInterface.getProductsByFilterstock(order);
        return ResponseEntity.ok(stockQuantity);
    }
}

