package com.slemanski.backend.features.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class MyUserController {

    @GetMapping
    public String home() {
        return "Hello world";
    }

    @PostMapping("/register")
    public String createUser() {
        return "Hello worlddD!";
    }

    @PostMapping("/login")
    public String authenticateUser() {
        return "auth";
    }
}
