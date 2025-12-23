package com.example.demo.features.users;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.features.users.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    Optional<User> findByEmailOrUsername(String email, String username);

}