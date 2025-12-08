package com.example.demo.features.users.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping(value = "/")
    public String getProducts() {
        System.out.println("Fetching users details");
        return "Product details";
    }

}
