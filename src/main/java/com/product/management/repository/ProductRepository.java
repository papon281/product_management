package com.product.management.repository;

import com.product.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Method to check if a product exists by name
    boolean existsByName(String name);
}