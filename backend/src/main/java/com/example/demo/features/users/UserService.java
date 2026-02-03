package com.example.demo.features.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityConfig;
import com.example.demo.features.users.dto.UpdateRequest;
import com.example.demo.features.users.model.Role;
import com.example.demo.features.users.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public UserService(UserRepository userRepository, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
    }

    public Optional<User> getUserByUsernameOrEmail(String usernameOrEmail) {
        return this.userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail);
    }

    public Optional<User> getUserById(UUID id) {
        return this.userRepository.findById(id);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }

    public boolean isAdmin(UUID id) {

        return this.userRepository.existsByIdAndRole(id, Role.ROLE_ADMIN);
    }

    public void updateUser(User user, UpdateRequest req) {
    
        if (req.getUsername() != null && !req.getUsername().isBlank()) {
            user.setUsername(req.getUsername());
        }

        if (req.getEmail() != null) {
            user.setEmail(req.getEmail());
        }

        if (req.getPassword() != null) {
            user.setPassword(securityConfig.passwordEncoder().encode(req.getPassword()));
        }

        this.userRepository.save(user);
    }

}
