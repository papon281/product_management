package com.product.management.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.apache.hc.core5.http.HttpStatus;

@Data
@Builder
@ToString
public class DeleteProductResponse {
    private Integer code;
    private String message;

    public static DeleteProductResponse from() {
        return DeleteProductResponse.builder()
                .code(HttpStatus.SC_OK)
                .message("Product deleted successfully.")
                .build();
    }
}