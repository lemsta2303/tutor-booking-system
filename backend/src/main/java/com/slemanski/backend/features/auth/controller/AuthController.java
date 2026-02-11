package com.slemanski.backend.features.auth.controller;

import com.slemanski.backend.features.auth.dto.InfoAboutMeDto;
import com.slemanski.backend.features.auth.dto.LoginRequestDto;
import com.slemanski.backend.features.auth.dto.LoginResponseDto;
import com.slemanski.backend.features.auth.dto.RegisterRequestDto;
import com.slemanski.backend.features.auth.service.AuthService;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody RegisterRequestDto user
    ) {
        authService.register(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto user) {

        LoginResponseDto response = authService.verify(user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<InfoAboutMeDto> getInfoAboutMe(
            @AuthenticationPrincipal MyUserDetails me
            ) {
        authService.checkIfUserExists(me);
        return ResponseEntity.ok(
                InfoAboutMeDto.from(me.getUser())
        );
    }


}
