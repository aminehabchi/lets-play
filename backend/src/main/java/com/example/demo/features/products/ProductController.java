package com.example.demo.features.products;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.products.dto.CreateProduct;
import com.example.demo.features.products.dto.UpdateProcut;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @PermitAll
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        return ResponseEntity.ok(
                ApiResponse.success(this.productService.getAllProducts())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable("id") UUID id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(ApiResponse.success(product)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Product not found.", HttpStatus.NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(
            @Valid @RequestBody CreateProduct createProduct,
            Authentication authentication) {

        UUID userId = (UUID) authentication.getPrincipal();
        Product product = this.productService.createProduct(createProduct, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(
            @PathVariable("id") UUID id,
            Authentication authentication) {

        UUID userId = (UUID) authentication.getPrincipal();
        var result = this.productService.deleteProduct(id, userId);

        return ResponseEntity.status(result.value())
                .body(ApiResponse.successStatus(result.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateProduct(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateProcut updateProcut,
            Authentication authentication) {

        Optional<Product> productOpt = this.productService.getProductById(id);

        if (productOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Product not found", HttpStatus.NOT_FOUND));
        }

        UUID userId = (UUID) authentication.getPrincipal();
        Product product = productOpt.get();

        if (!product.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(ApiResponse.error("Not authorized", HttpStatus.FORBIDDEN));
        }

        this.productService.updateProduct(product, updateProcut);

        return ResponseEntity.ok(ApiResponse.successStatus(HttpStatus.OK));
    }
}
