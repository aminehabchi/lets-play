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
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        Costumer user = costumerRepository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CostumerDetails(user);
    }
}
