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

    public List<Product> getAllProducts(){
              return productRepository.findAll();
    }

    public Product createProduct(CreateProduct newProduct, UUID userId) {
        Product p = new Product(newProduct, userId);
        p = this.productRepository.save(p);
        return p;
    }

    public HttpStatus deleteProduct(UUID id, UUID userId) {
        Optional<Product> p = getProductById(id);
        if (p.isPresent()) {
            if (p.get().getUserId() != userId && !this.userService.isAdmin(userId)) {
                return HttpStatus.FORBIDDEN;
            }

            this.productRepository.delete(p.get());
            return HttpStatus.NO_CONTENT;
        }
        return HttpStatus.NOT_FOUND;
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
