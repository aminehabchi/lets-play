package com.example.demo.features.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.auth.dto.LoginRequest;
import com.example.demo.features.auth.dto.RegisterRequest;
import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.users.model.User;
import com.example.demo.utils.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/register")
    public ApiResponse<User> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success(authService.register(request));
    }

    @PostMapping(value = "/login")
    public ApiResponse<String> login(@RequestBody LoginRequest request) {
        // Authentication auth = authenticationManager.authenticate(
        // new UsernamePasswordAuthenticationToken(request.getEmailOrUsername(),
        // request.getPassword()));

        String token = jwtUtil.generateToken(request.getEmailOrUsername());
        return ApiResponse.success(token);
    }

}
