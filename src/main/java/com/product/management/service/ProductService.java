package com.product.management.service;

import com.product.management.dto.request.ProductRequest;
import com.product.management.dto.request.UpdateStockRequest;
import com.product.management.dto.response.*;

public interface ProductService {
    CreateProductResponse createProduct(ProductRequest request);

    UpdateProductResponse updateProduct(Long id, ProductRequest request);

    UpdateProductResponse updateStock(Long id, UpdateStockRequest request);

    GetProductResponse getProduct(Long id);

    GetAllProductResponse getAllProduct();

    DeleteProductResponse deleteProduct(Long id);
}