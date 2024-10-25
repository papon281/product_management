package com.product.management.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class GetAllProductResponse {
    private List<GetProductResponse> getProductResponseList;

    public static GetAllProductResponse from(List<GetProductResponse> getProductResponseList) {
        return GetAllProductResponse.builder().
                getProductResponseList(getProductResponseList)
                .build();
    }
}