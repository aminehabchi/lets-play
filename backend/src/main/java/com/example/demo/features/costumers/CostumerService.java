package com.example.demo.features.costumers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.features.costumers.model.Costumer;

@Service
public class CostumerService {
    private final CostumerRepository costumerRepository;

    public CostumerService(CostumerRepository costumerRepository) {
        this.costumerRepository = costumerRepository;
    }

    public Optional<Costumer> getCustumerById(UUID id) {
        return this.costumerRepository.findById(id);
    }

    public void deleteCostumer(UUID id) {
        this.costumerRepository.deleteById(id);
    }

}
