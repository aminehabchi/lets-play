package com.example.demo.features.products;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping(value = "")
    public String getProducts() {
        System.out.println("Fetching all Products details");
        return "Products details";
    }

    @GetMapping(value = "/{id}")
    public String getProduct(@PathVariable String id) {
        System.out.println("Fetching one Product details");
        return "Products details";
    }

    @PostMapping(value = "")
    public String createProduct() {
        System.out.println("Creating new Product");
        return "Product created";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteProduct(@PathVariable String id) {
        System.out.println("Deleting Product");
        return "Product deleted";
    }

    @PutMapping(value = "/{id}")
    public String updateProduct(@PathVariable String id) {
        System.out.println("Updating Product");
        return "Product updated";
    }

}
