package com.slemanski.backend.features.auth.controller;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public MyUser register(@RequestBody MyUser user) {
        return authService.register(user);
    }


}
