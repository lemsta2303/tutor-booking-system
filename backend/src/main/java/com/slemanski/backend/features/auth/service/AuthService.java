package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.features.auth.dto.LoginRequestDto;
import com.slemanski.backend.features.auth.dto.RegisterRequestDto;
import com.slemanski.backend.features.auth.exception.InvalidCredentialsException;
import com.slemanski.backend.features.auth.exception.UserAlreadyExistsException;
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

    public void register(RegisterRequestDto userDto) {
        if(myUserRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistsException(userDto.getUsername());
        }
        MyUser newUser = new MyUser();
        newUser.setUsername(userDto.getUsername());
        newUser.setRole(userDto.getRole());
        newUser.setPassword(encoder.encode(userDto.getPassword()));
        myUserRepository.save(newUser);
    }

    public String verify(LoginRequestDto userDto) {
        Authentication authentication;
        try {
            authentication =
                    authManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        } catch(Exception exc) {
            throw new InvalidCredentialsException();
        }

        if(!authentication.isAuthenticated()) {
            throw new InvalidCredentialsException();
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
