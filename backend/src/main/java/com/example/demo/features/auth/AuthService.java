package com.example.demo.features.auth;

import org.springframework.stereotype.Service;

import com.example.demo.features.auth.dto.RegisterRequest;
import com.example.demo.features.costumers.CostumerRepository;
import com.example.demo.features.costumers.model.Costumer;

@Service
public class AuthService {

    private final CostumerRepository userRepository;

    public AuthService(CostumerRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Costumer register(RegisterRequest request) {
        return userRepository.save(new Costumer(request));
    }
}
