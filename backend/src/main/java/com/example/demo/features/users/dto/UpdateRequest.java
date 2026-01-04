package com.example.demo.features.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {

    @Size(max = 255)
    private String username;

    @Email(message = "Email must be valid")
    @Size(max = 255)
    private String email;

    @Size(min = 6, max = 255, message = "Password must be at least 6 characters")
    private String password;
}
