package com.example.demo.features.auth;

import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityConfig;
import com.example.demo.features.auth.dto.RegisterRequest;
import com.example.demo.features.users.UserRepository;
import com.example.demo.features.users.model.User;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public AuthService(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public User register(RegisterRequest request) {
        request.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));
        return userRepository.save(new User(request));
    }
}
