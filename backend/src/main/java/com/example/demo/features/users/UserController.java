package com.example.demo.features.users;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.features.common.ApiResponse;
import com.example.demo.features.users.dto.UpdateRequest;
import com.example.demo.features.users.model.User;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<User>>> getUsers() {
        return ResponseEntity.ok(ApiResponse.success(userService.getUsers()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable UUID id, Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();

        if (!userId.equals(id)) {
            if (!this.userService.isAdmin(userId)) {
                return ResponseEntity.ok(ApiResponse.error("", HttpStatus.FORBIDDEN));
            }
        }

        return userService.getUserById(id)
                .map(p -> ResponseEntity.ok(ApiResponse.success(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("User not found", HttpStatus.NOT_FOUND)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(userService.getUserById(userId).get()));
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteMe(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK));
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUser(Authentication auth,@Valid @RequestBody UpdateRequest updateRequest) {
        UUID userId = (UUID) auth.getPrincipal();
        User user = this.userService.getUserById(userId).orElse(null);

        System.out.println(updateRequest.getUsername() + "  " + user.getUsername());
        this.userService.updateUser(user, updateRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK));
    }

}
