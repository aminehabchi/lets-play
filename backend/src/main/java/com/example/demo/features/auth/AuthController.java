package com.example.demo.features.auth;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.auth.dto.LoginRequest;
import com.example.demo.features.auth.dto.RegisterRequest;
import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.users.UserService;
import com.example.demo.features.users.model.User;
import com.example.demo.utils.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    public AuthController(AuthService authService, JwtUtil jwtUtil, UserService userService) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ApiResponse<User> register(@Valid @RequestBody RegisterRequest request) {
        return ApiResponse.success(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@Valid @RequestBody LoginRequest request) {
        Optional<User> user = this.userService.getUserByUsernameOrEmail(request.getEmailOrUsername());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("user not found", HttpStatus.NOT_FOUND));
        }

        if (!this.authService.comparePassword(user.get(), request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Wrong Password", HttpStatus.BAD_REQUEST));
        }

        String jwt = jwtUtil.generateToken(user.get().getUsername());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(jwt));
    }

}
