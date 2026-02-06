package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.features.auth.exception.UserAlreadyExistsException;
import com.slemanski.backend.infrastructure.security.jwt.JwtService;
import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import com.slemanski.backend.features.auth.repository.MyUserRepository;
import com.slemanski.backend.shared.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private final MyUserRepository myUserRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthService(MyUserRepository repo, AuthenticationManager authManager, JwtService jwtService) {
        this.myUserRepository = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public MyUser register(MyUser user) {
        if(myUserRepository.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        user.setPassword(encoder.encode(user.getPassword()));
        return myUserRepository.save(user);
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
