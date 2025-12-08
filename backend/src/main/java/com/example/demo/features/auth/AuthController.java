package com.example.demo.features.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.auth.dto.RegisterRequest;
import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.costumers.model.Costumer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/register")
    public ApiResponse<Costumer> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success(authService.register(request));
    }

    @PostMapping(value = "/login")
    public String login() {
        System.out.println("User login");
        return "User logged in";
    }

}
