package com.product.management.controller;

import com.product.management.dto.request.ProductRequest;
import com.product.management.dto.request.UpdateStockRequest;
import com.product.management.dto.response.*;
import com.product.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody @Valid @NotNull ProductRequest request) {
        CreateProductResponse response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid @NotNull ProductRequest request) {
        UpdateProductResponse response = productService.updateProduct(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(
            value = "/{id}/update-stock",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UpdateProductResponse> updateStock(@PathVariable Long id, @RequestBody @Valid @NotNull UpdateStockRequest request) {
        UpdateProductResponse response = productService.updateStock(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable Long id) {
        GetProductResponse response = productService.getProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<GetAllProductResponse> getAllProduct() {
        GetAllProductResponse response = productService.getAllProduct();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable Long id) {
        DeleteProductResponse response = productService.deleteProduct(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}