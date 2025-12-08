package com.example.demo.features.costumers;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.features.costumers.model.Costumer;

@Repository
public interface CostumerRepository extends MongoRepository<Costumer, String> {
    Optional<Costumer> findByEmail(String email);
}