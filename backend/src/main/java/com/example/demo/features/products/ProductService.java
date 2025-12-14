package com.example.demo.features.products;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.features.products.dto.CreateProduct;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public Product createProduct(CreateProduct newProduct, UUID userId) {
        Product p = new Product(newProduct, userId);
        p = this.productRepository.save(p);
        return p;
    }

    public HttpStatus deleteProduct(UUID id, UUID userId) {
        Optional<Product> p = getProductById(id);
        if (p.isPresent()) {
            if (p.get().getCustomerId() != userId) {
                return HttpStatus.FORBIDDEN;
            }

            this.productRepository.delete(p.get());
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
    }

}
