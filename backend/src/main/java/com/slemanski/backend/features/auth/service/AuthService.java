package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.model.MyUserDetails;
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
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthService(MyUserRepository repo, AuthenticationManager authManager, JWTService jwtService) {
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
                        .getAuthority())   // "ROLE_STUDENT"
                        .replace("ROLE_", "") : null;

        return jwtService.generateToken(
                userDetails.getUsername(),
                role
        );
    }
}
