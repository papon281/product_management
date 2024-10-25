package com.product.management.service.impl;

import com.product.management.dto.request.ProductRequest;
import com.product.management.dto.request.UpdateStockRequest;
import com.product.management.dto.response.*;
import com.product.management.exception.RequestValidationException;
import com.product.management.model.Product;
import com.product.management.repository.ProductRepository;
import com.product.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());
    private final ProductRepository productRepository;
    private final ProductDomainService productDomainService;
    private final ProductValidationService productValidationService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDomainService productDomainService,
                              ProductValidationService productValidationService) {
        this.productRepository = productRepository;
        this.productDomainService = productDomainService;
        this.productValidationService = productValidationService;
    }

    @Override
    public CreateProductResponse createProduct(ProductRequest request) {
        logger.info("Validating product uniqueness for: " + request.getName());
        productDomainService.validateProductUniqueness(request.getName(), productRepository);

        logger.info("Validating price: " + request.getPrice());
        productValidationService.validatePrice(request.getPrice());

        logger.info("Validating stock quantity: " + request.getStockQuantity());
        productValidationService.validateStock(request.getStockQuantity());

        logger.info("Validating category: " + request.getCategory());
        productValidationService.validateCategory(request.getCategory());

        logger.info("Creating new product object.");
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategory(request.getCategory());

        logger.info("Saving product information to the database.");
        return CreateProductResponse.from(productRepository.save(product));
    }

    @Override
    public UpdateProductResponse updateProduct(Long id, ProductRequest request) {
        logger.info("Retrieving product with ID: " + id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Validating product name uniqueness for update: " + request.getName());
            if (!request.getName().equals(optionalProduct.get().getName())) {
                productDomainService.validateProductUniqueness(request.getName(), productRepository);
            }

            logger.info("Validating price: " + request.getPrice());
            productValidationService.validatePrice(request.getPrice());

            logger.info("Validating stock quantity: " + request.getStockQuantity());
            productValidationService.validateStock(request.getStockQuantity());

            logger.info("Validating category: " + request.getCategory());
            productValidationService.validateCategory(request.getCategory());

            Product product = optionalProduct.get();
            product.setName(request.getName());
            product.setDescription(request.getDescription() != null ? request.getDescription() : product.getDescription());
            product.setPrice(request.getPrice());
            product.setStockQuantity(request.getStockQuantity());
            product.setCategory(request.getCategory());

            logger.info("Updating product information in the database.");
            return UpdateProductResponse.from(productRepository.save(product));
        } else {
            throw new RequestValidationException("Product with ID " + id + " not found.");
        }
    }

    @Override
    public UpdateProductResponse updateStock(Long id, UpdateStockRequest request) {
        logger.info("Retrieving product with ID: " + id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Validating stock quantity: " + request.getStockQuantity());
            productValidationService.validateStock(request.getStockQuantity());
            optionalProduct.get().setStockQuantity(request.getStockQuantity());

            logger.info("Updating stock quantity for product ID: " + id);
            return UpdateProductResponse.from(productRepository.save(optionalProduct.get()));
        } else {
            throw new RequestValidationException("Product with ID " + id + " not found.");
        }
    }

    @Override
    public GetProductResponse getProduct(Long id) {
        logger.info("Retrieving product with ID: " + id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Returning product information for ID: " + id);
            return GetProductResponse.from(optionalProduct.get());
        } else {
            throw new RequestValidationException("Product with ID " + id + " not found.");
        }
    }

    @Override
    public GetAllProductResponse getAllProduct() {
        logger.info("Retrieving all products from the database.");
        List<GetProductResponse> getProductResponseList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            getProductResponseList.add(GetProductResponse.from(product));
        }

        logger.info("Returning list of all products.");
        return GetAllProductResponse.from(getProductResponseList);
    }

    @Override
    public DeleteProductResponse deleteProduct(Long id) {
        logger.info("Retrieving product with ID: " + id);
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            logger.info("Deleting product ID: " + id);
            productRepository.delete(optionalProduct.get());
            return DeleteProductResponse.from();
        } else {
            throw new RequestValidationException("Product with ID " + id + " not found.");
        }
    }
}