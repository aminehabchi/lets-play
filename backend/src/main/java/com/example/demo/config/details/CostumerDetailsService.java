package com.example.demo.config.details;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.features.costumers.CostumerRepository;
import com.example.demo.features.costumers.model.Costumer;


@Service
public class CostumerDetailsService implements UserDetailsService {
    private final CostumerRepository costumerRepository;

    public CostumerDetailsService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Costumer user = costumerRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        System.out.println("------------------------------> Loading user by email: " + user.getEmail());

        return new CostumerDetails(user);
    }
}
