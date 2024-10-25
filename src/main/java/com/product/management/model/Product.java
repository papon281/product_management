package com.product.management.model;

import com.product.management.constant.Category;
import com.product.management.constant.db.DbProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = DbProduct.TABLE_NAME)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DbProduct.ID, nullable = false)
    private Long id;

    @Column(name = DbProduct.NAME, unique = true, nullable = false)
    private String name;

    @Column(name = DbProduct.DESCRIPTION)
    private String description;

    @Column(name = DbProduct.PRICE, nullable = false)
    private BigDecimal price;

    @Column(name = DbProduct.STOCK_QUANTITY)
    private Integer stockQuantity;

    @Enumerated(value = EnumType.STRING)
    @Column(name = DbProduct.CATEGORY, nullable = false)
    private Category category;

    @CreationTimestamp
    @Column(name = DbProduct.CREATED_AT, nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = DbProduct.UPDATED_AT)
    private LocalDateTime updatedAt;
}