package com.product.management.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class UpdateStockRequest {
    @Valid
    @NotNull
    private Integer stockQuantity;
}