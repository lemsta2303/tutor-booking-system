package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.infrastructure.security.jwt.JwtService;
import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import com.slemanski.backend.features.auth.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private MyUserRepository repo;
    private AuthenticationManager authManager;
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthService(MyUserRepository repo, AuthenticationManager authManager, JwtService jwtService) {
        this.repo = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public MyUser register(MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(MyUser user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(!authentication.isAuthenticated()) {
            throw new RuntimeException("Invalid Login");
        }

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        String role = userDetails != null ? Objects.requireNonNull(userDetails
                        .getAuthorities()
                        .iterator()
                        .next()
                        .getAuthority())
                        .replace("ROLE_", "") : null;

        return jwtService.generateToken(
                userDetails.getUsername(),
                role
        );
    }
}
