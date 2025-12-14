package com.example.demo.features.products;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.products.dto.CreateProduct;
import com.mongodb.lang.NonNull;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getProducts() {
        System.out.println("Fetching all Products details");
        return "Products details";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable UUID id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(ApiResponse.success(product)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Product not found.", 404)));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> createProduct(@NonNull CreateProduct createProduct,
            Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        Product product = this.productService.createProduct(createProduct, userId);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable UUID id, Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(ApiResponse.successStatus(this.productService.deleteProduct(userId, userId).value()));
    }

    @PutMapping(value = "/{id}")
    public String updateProduct(@PathVariable UUID id) {
        System.out.println("Updating Product");
        return "Product updated";
    }

}
