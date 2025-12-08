package com.example.demo.features.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.features.products.model.Product;
import com.example.demo.features.users.model.User;

public interface UserRepository extends MongoRepository<User, String> {
}