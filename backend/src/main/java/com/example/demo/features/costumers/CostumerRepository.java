package com.example.demo.features.costumers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.features.costumers.model.Costumer;

@Repository
public interface CostumerRepository extends MongoRepository<Costumer, UUID> {
    Optional<Costumer> findByEmailOrUsername(String email, String username);

}