package com.product.management.service.impl;

import com.product.management.constant.Category;
import com.product.management.exception.RequestValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductValidationService {

    public void validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RequestValidationException("Price must be greater than zero");
        }
    }

    public void validateCategory(Category category) {
        if (category == null) {
            throw new RequestValidationException("Category should be ELECTRONICS, CLOTHING " +
                    ", FOOD or BOOKS");
        }
    }

    public void validateStock(int stock) {
        if (stock < 0) {
            throw new RequestValidationException("Stock cannot be negative");
        }
    }
}