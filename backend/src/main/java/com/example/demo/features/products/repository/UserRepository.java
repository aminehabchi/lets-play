package com.example.demo.features.products.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.features.products.model.Product;

public interface UserRepository extends MongoRepository<Product, String> {
}