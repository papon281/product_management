package com.product.management.dto.response;

import com.product.management.constant.Category;
import com.product.management.model.Product;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class UpdateProductResponse {
    private Integer code;
    private String message;
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UpdateProductResponse from(Product product) {
        return UpdateProductResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Product Updated Successfully.")
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