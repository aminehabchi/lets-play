package com.example.demo.features.auth;

import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityConfig;
import com.example.demo.features.auth.dto.RegisterRequest;
import com.example.demo.features.costumers.CostumerRepository;
import com.example.demo.features.costumers.model.Costumer;

@Service
public class AuthService {

    private final CostumerRepository userRepository;
    private final SecurityConfig securityConfig;

    public AuthService(CostumerRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public Costumer register(RegisterRequest request) {
        request.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));
        return userRepository.save(new Costumer(request));
    }
}
