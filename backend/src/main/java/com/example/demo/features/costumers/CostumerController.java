package com.example.demo.features.costumers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.costumers.model.Costumer;


@RestController
@RequestMapping("/api/Costumers")
public class CostumerController {
    private final CostumerService costumerService;

    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @GetMapping()
    public String getCostumers() {
        System.out.println("Fetching users details");
        return "Costumers details";
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Costumer>> getCostomer(@PathVariable UUID id) {
        return costumerService.getCustumerById(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Customer not found", HttpStatus.NOT_FOUND.value())));
    }

    @DeleteMapping()
    public ResponseEntity<ApiResponse<Costumer>> deleteCostumer(Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();

        this.costumerService.deleteCostumer(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.successStatus(HttpStatus.NO_CONTENT.value()));
    }

    @PutMapping(value = "/{id}")
    public String updateCostumer(@PathVariable String id) {
        System.out.println("Updating Costumer");
        return "Costumer updated";
    }

}
