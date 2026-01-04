package com.example.demo.features.products;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import com.mongodb.lang.NonNull;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    @PermitAll
    public ResponseEntity<ApiResponse<List<Product>>> getProducts() {
        return ResponseEntity.ok(ApiResponse.success(this.productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable UUID id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(ApiResponse.success(product)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Product not found.", HttpStatus.NOT_FOUND)));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<Product>> createProduct(@NonNull @RequestBody CreateProduct createProduct,
            Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        Product product = this.productService.createProduct(createProduct, userId);
        return ResponseEntity.ok(ApiResponse.success(product));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<Product>> deleteProduct(@PathVariable UUID id, Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(ApiResponse.successStatus(this.productService.deleteProduct(id, userId).value()));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ApiResponse<?>> updateProduct(@PathVariable UUID productId,
            @NonNull @RequestBody UpdateProcut updateProcut,
            Authentication authentication) {

        Optional<Product> product = this.productService.getProductById(productId);

        if (product.isEmpty()) {
            return ResponseEntity.ok(ApiResponse.error("Product not found", HttpStatus.NOT_FOUND));
        }

        UUID userId = (UUID) authentication.getPrincipal();
        if (!product.get().getUserId().equals(userId)) {
            return ResponseEntity.ok(ApiResponse.error("Not authorized", HttpStatus.FORBIDDEN));
        }

        this.productService.updateProduct(product.get(), updateProcut);

        return ResponseEntity.ok(ApiResponse.successStatus(HttpStatus.OK));
    }

}
