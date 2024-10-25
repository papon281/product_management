package com.product.management.dto.response;


import com.product.management.constant.Category;
import com.product.management.model.Product;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class GetProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static GetProductResponse from(Product product) {
        return GetProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription() != null ? product.getDescription() : null)
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .category(product.getCategory())
                .createdAt(product.getCreatedAt() != null ? product.getCreatedAt() : null)
                .updatedAt(product.getUpdatedAt() != null ? product.getUpdatedAt() : null)
                .build();
    }
}