# Let's Play - RESTful CRUD API

A secure RESTful API built with Spring Boot and MongoDB for managing users and products with JWT-based authentication and role-based access control.

## Overview

Let's Play is a backend API for a small modern backend development best practices. The application allows administrators to manage all users and products, while regular users can manage only their own products. The system emphasizes security, proper REST design, and comprehensive error handling.

## Project Goals

This project demonstrates proficiency in:

1. **Spring Boot & RESTful Design**: Building scalable REST APIs following industry standards
2. **MongoDB Integration**: Working with NoSQL databases in Spring applications
3. **Security Implementation**: Applying Spring Security and JWT for authentication/authorization
4. **Error Handling**: Creating robust applications with comprehensive exception handling
5. **Clean Code Practices**: Writing maintainable, modular, and well-structured code
6. **Role-Based Access Control**: Implementing fine-grained permission systems

## Key Features

- **User Management**: Complete CRUD operations for user accounts with role-based permissions
- **Product Management**: Full product lifecycle management with owner-based access control
- **JWT Authentication**: Secure token-based authentication system
- **Role-Based Authorization**: Separate permissions for Admin and User roles
- **Password Security**: BCrypt hashing and salting for all passwords
- **Global Exception Handling**: Comprehensive error handling with meaningful HTTP responses
- **MongoDB Integration**: NoSQL database for flexible data storage
- **Input Validation**: Request validation and sanitization to prevent injection attacks


## Technology Stack

- **Framework**: Spring Boot 4.0.0
- **Language**: Java 17
- **Database**: MongoDB
- **Security**: Spring Security + JWT (jsonwebtoken 0.11.5)
- **Build Tool**: Gradle
- **Additional Libraries**: Lombok, Spring Validation

## Project Structure (feature-based)

```
backend/
├── src/main/java/com/example/demo/
│   ├── Main.java
│   ├── config/
│   │   ├── SecurityConfig.java
│   │   ├── filters/JwtAuthFilter.java
│   │   └── details/
│   ├── features/
│   │   ├── auth/
│   │   │   ├── AuthController.java
│   │   │   ├── AuthService.java
│   │   │   └── dto/
│   │   ├── users/
│   │   │   ├── UserController.java
│   │   │   ├── UserService.java
│   │   │   ├── UserRepository.java
│   │   │   └── model/User.java
│   │   ├── products/
│   │   │   ├── ProductController.java
│   │   │   ├── ProductService.java
│   │   │   ├── ProductRepository.java
│   │   │   └── Product.java
│   │   └── common/
│   ├── exceptions/GlobalExceptionHandler.java
│   └── utils/JwtUtil.java
```

## Database Schema

```
User
├── id: String
├── name: String
├── email: String
├── password: String (hashed)
└── role: String (ADMIN/USER)

Product
├── id: String
├── name: String
├── description: String
├── price: Double
└── userId: String (owner reference)
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login and receive JWT token

### Products
- `GET /api/products` - Get all products (public)
- `POST /api/products` - Create a product (authenticated)
- `GET /api/products/{id}` - Get product by ID (public)
- `PUT /api/products/{id}` - Update product (owner/admin only)
- `DELETE /api/products/{id}` - Delete product (owner/admin only)

### Users
- `GET /api/users` - Get all users (admin only)
- `GET /api/users/{id}` - Get user by ID (admin only)
- `PUT /api/users/{id}` - Update user (admin only)
- `DELETE /api/users/{id}` - Delete user (admin only)

### API Response Format

All API endpoints in this project return a standard response using the ApiResponse<T> record. This ensures consistency across the backend and makes it easier for clients to handle responses.

```java
public record ApiResponse<T>(
        boolean success,
        T data,
        String message,
        int statusCode,
        Map<String, String> errors) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, 200, null);
    }

    public static <T> ApiResponse<T> successStatus(HttpStatus status) {
        return new ApiResponse<>(true, null, null, status.value(), null);
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus status) {
        return new ApiResponse<>(false, null, message, status.value(), null);
    }
}
```


## Security Features

- **Password Hashing**: All passwords are hashed using BCrypt before storage
- **JWT Tokens**: Stateless authentication using JSON Web Tokens
- **Role-Based Access Control**: Endpoints protected based on user roles
- **Input Validation**: Request DTOs validated to prevent malicious input
- **Sensitive Data Exclusion**: Passwords and sensitive fields never exposed in responses



## Development Best Practices

- Secure coding standards (OWASP guidelines)
- Proper HTTP status code usage
- Clear and meaningful error messages
- RESTful resource naming conventions
- Separation of concerns (Controllers, Services, Repositories)
- DTO pattern for request/response handling


