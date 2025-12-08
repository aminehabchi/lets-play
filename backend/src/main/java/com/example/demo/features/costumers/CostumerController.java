package com.example.demo.features.costumers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Costumers")
public class CostumerController {

    @GetMapping(value = "")
    public String getCostumers() {
        System.out.println("Fetching users details");
        return "Costumers details";
    }

    @GetMapping(value = "/{id}")
    public String getCostumer(@PathVariable String id) {
        System.out.println("Fetching Costumers details");
        return "Costumers details";
    }

    @PostMapping(value = "")
    public String createCostumer() {
        System.out.println("Creating new Costumer");
        return "Costumer created";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteCostumer(@PathVariable String id) {
        System.out.println("Deleting Costumer");
        return "Costumer deleted";
    }

    @PutMapping(value = "/{id}")
    public String updateCostumer(@PathVariable String id) {
        System.out.println("Updating Costumer");
        return "Costumer updated";
    }

}
