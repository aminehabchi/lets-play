package com.example.demo.features.auth;

import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityConfig;
import com.example.demo.features.auth.dto.LoginRequest;
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
        if (userRepository.existsByUsername(request.getUsername())
                || userRepository.existsByEmail(request.getUsername())) {
            return null;
        }

        request.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));
        return userRepository.save(new User(request));
    }

    public boolean comparePassword(User u1, LoginRequest u2) {
        return securityConfig.passwordEncoder().matches(u2.getPassword(), u1.getPassword());
    }

}
