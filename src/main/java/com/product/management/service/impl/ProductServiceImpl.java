package com.product.management.service.impl;

import com.product.management.dto.request.ProductRequest;
import com.product.management.dto.request.UpdateStockRequest;
import com.product.management.dto.response.*;
import com.product.management.exception.RequestValidationException;
import com.product.management.model.Product;
import com.product.management.repository.ProductRepository;
import com.product.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDomainService productDomainService;
    private final ProductValidationService productValidationService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDomainService productDomainService,
                              ProductValidationService productValidationService) {
        this.productRepository = productRepository;
        this.productDomainService = productDomainService;
        this.productValidationService = productValidationService;
    }

    @Override
    public CreateProductResponse createProduct(ProductRequest request) {
        productDomainService.validateProductUniqueness(request.getName(), productRepository);
        productValidationService.validateCategory(request.getCategory());
        productValidationService.validatePrice(request.getPrice());
        productValidationService.validateStock(request.getStockQuantity());
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription() != null ? request.getDescription() : null);
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(request.getCategory());
        return CreateProductResponse.from(productRepository.save(product));
    }

    @Override
    public UpdateProductResponse updateProduct(Long id, ProductRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent() && !request.getName().equals(optionalProduct.get().getName())) {
            productDomainService.validateProductUniqueness(request.getName(), productRepository);
            productValidationService.validateCategory(request.getCategory());
            productValidationService.validatePrice(request.getPrice());
            productValidationService.validateStock(request.getStockQuantity());
            optionalProduct.get().setName(request.getName());
            optionalProduct.get().setDescription(request.getDescription() != null ?
                    request.getDescription() : optionalProduct.get().getDescription());
            optionalProduct.get().setPrice(request.getPrice());
            optionalProduct.get().setStockQuantity(request.getStockQuantity());
            optionalProduct.get().setCategory(request.getCategory());
            return UpdateProductResponse.from(productRepository.save(optionalProduct.get()));
        } else {
            throw new RequestValidationException("Product not exist using the id: " + id);
        }
    }

    @Override
    public UpdateProductResponse updateStock(Long id, UpdateStockRequest request) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productValidationService.validateStock(request.getStockQuantity());
            optionalProduct.get().setStockQuantity(request.getStockQuantity());
            return UpdateProductResponse.from(productRepository.save(optionalProduct.get()));
        } else {
            throw new RequestValidationException("Product not exist using the id: " + id);
        }
    }

    @Override
    public GetProductResponse getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return GetProductResponse.from(optionalProduct.get());
        } else {
            throw new RequestValidationException("Product not exist using the id: " + id);
        }
    }

    @Override
    public GetAllProductResponse getAllProduct() {
        List<GetProductResponse> getProductResponseList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            getProductResponseList.add(GetProductResponse.from(product));
        }
        return GetAllProductResponse.from(getProductResponseList);
    }

    @Override
    public DeleteProductResponse deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return DeleteProductResponse.from();
        } else {
            throw new RequestValidationException("Product not exist using the id: " + id);
        }
    }
}