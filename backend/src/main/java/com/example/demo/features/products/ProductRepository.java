package com.example.demo.features.products;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
    List<Product> findByCustomerId(String customerId);
}