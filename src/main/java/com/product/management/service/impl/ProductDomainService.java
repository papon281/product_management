package com.product.management.service.impl;

import com.product.management.exception.RequestValidationException;
import com.product.management.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDomainService {

    public void validateProductUniqueness(String productName, ProductRepository productRepository) {
        if (productRepository.existsByName(productName)) {
            throw new RequestValidationException("Product name must be unique");
        }
    }
}