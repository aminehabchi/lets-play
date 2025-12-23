package com.example.demo.features.users;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.features.users.model.User;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(UUID id) {
        return this.userRepository.findById(id);
    }

    public List<User> getUsers(){
        return this.userRepository.findAll();
    }

    public void deleteUser(UUID id) {
        this.userRepository.deleteById(id);
    }

}
