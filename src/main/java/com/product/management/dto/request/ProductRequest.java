package com.product.management.dto.request;

import com.product.management.constant.Category;
import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@ToString
public class ProductRequest {
    @Valid
    @NotEmpty
    private String name;
    private String description;
    @Valid
    @NotNull
    private BigDecimal price;
    @Valid
    @NotNull
    private Integer stockQuantity;
    @Valid
    @NotNull
    private Category category;
}