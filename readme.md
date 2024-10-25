# Product Management API

## Overview

The Product Management API is a RESTful service built with Spring Boot that allows users to manage products in an eCommerce application. It implements Domain-Driven Design (DDD) principles to ensure a well-structured and maintainable codebase.

## Features

- **CRUD Operations**: Create, Read, Update, and Delete products.
- **Unique Product Names**: Validation to ensure product names are unique.
- **Category Management**: Support for different product categories.
- **Stock Management**: Validate and update product stock quantities.
- **Price Validation**: Ensure that product prices are valid.
- **In-Memory Database**: Uses H2 for in-memory storage, ideal for testing and development.

## Technologies Used

- **Java 17**: The project is developed using Java 17.
- **Spring Boot 3.1.2**: For building the RESTful API.
- **H2 Database**: An in-memory database for development and testing.
- **Lombok**: Reduces boilerplate code.
- **JUnit**: For unit testing.

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven

### Installation

1. **Clone the Repository**:
   ```bash
   https://github.com/papon281/product_management.git
2. **Navigate to the Project Directory**:
   ```bash
   cd product_management 
3. **Build the Project**:
   ```bash
   mvn clean install
4. **Run the Application**:
   ```bash
   mvn spring-boot:run

The API will be available at http://localhost:8080

## API Endpoints
**Create a Product**:  
**Endpoint:** `POST /api/products`  
**Request Body:**
```json
    {
        "name": "Product Name",
        "description": "Product Description",
        "price": 99.99,
        "stockQuantity": 100,
        "category": "Category Name"
    }
```

**Get All Products**:  
**Endpoint**:`GET /api/products`  
**Response**:
```json
    [
        {
            "id": 1,
            "name": "Product Name",
            "description": "Product Description",
            "price": 99.99,
            "stockQuantity": 100,
            "category": "Category Name"
        }
    ]
```

**Get a Product**:  
**Endpoint**:`GET /api/products/{id}`  
**Response**:
```json
    {
        "id": 1,
        "name": "Product Name",
        "description": "Product Description",
        "price": 99.99,
        "stockQuantity": 100,
        "category": "Category Name"
    }
```

**Update a Product**  
**Endpoint**: `PUT /api/products/{id}`  
**Request Body**:
```json
    {
        "name": "Updated Product Name",
        "description": "Updated Description",
        "price": 89.99,
        "stockQuantity": 50,
        "category": "Updated Category"
    }
```

**Update a Product Stock Quantity**  
**Endpoint**: `PATCH /api/products/{id}/update-stock`  
**Request Body**:
```json
    {
      "stockQuantity": 50
    }
```

**Delete a Product**  
**Endpoint**: `DELETE /api/products/{id}`  
**Response**:
```json
    {
      "code": 200,
      "message": "Product deleted successfully."
    }
```