package com.example.demo.features.costumers;

import java.util.List;
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

    public List<Costumer> getCustumers(){
        return this.costumerRepository.findAll();
    }

    public void deleteCostumer(UUID id) {
        this.costumerRepository.deleteById(id);
    }

}
