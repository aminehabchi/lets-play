package com.example.demo.features.products.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping(value = "/")
    public String getProducts() {
        System.out.println("Fetching poroduct details");
        return "Product details";
    }

}
