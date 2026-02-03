package com.example.demo.features.products;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.features.products.dto.CreateProduct;
import com.example.demo.features.products.dto.UpdateProcut;
import com.example.demo.features.users.UserService;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }

    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(CreateProduct newProduct, UUID userId) {
        Product p = new Product(newProduct, userId);
        p = this.productRepository.save(p);
        return p;
    }

    public HttpStatus deleteProduct(UUID productId, UUID currentUserId) {
        Optional<Product> productOpt = getProductById(productId);

        if (productOpt.isEmpty()) {
            return HttpStatus.NOT_FOUND;
        }

        Product product = productOpt.get();

        boolean isOwner = product.getUserId().equals(currentUserId);
        boolean isAdmin = userService.isAdmin(currentUserId);

        if (!isOwner && !isAdmin) {
            return HttpStatus.FORBIDDEN;
        }

        productRepository.delete(product);
        return HttpStatus.NO_CONTENT;
    }

    public void updateProduct(Product product, UpdateProcut updateProduct) {

        if (updateProduct.getName() != null) {
            product.setName(updateProduct.getName());
        }
        if (updateProduct.getDescription() != null) {
            product.setDescription(updateProduct.getDescription());
        }
        if (updateProduct.getPrice() != null) {
            product.setPrice(updateProduct.getPrice());
        }

        productRepository.save(product);

    }

}
