package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.features.auth.dto.LoginRequestDto;
import com.slemanski.backend.features.auth.dto.LoginResponseDto;
import com.slemanski.backend.features.auth.dto.RegisterRequestDto;
import com.slemanski.backend.features.auth.exception.InvalidCredentialsException;
import com.slemanski.backend.features.auth.exception.UserAlreadyExistsException;
import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.model.Role;
import com.slemanski.backend.features.auth.repository.MyUserRepository;
import com.slemanski.backend.features.students.model.StudentProfile;
import com.slemanski.backend.features.students.repository.StudentProfileRepository;
import com.slemanski.backend.features.tutors.model.TutorProfile;
import com.slemanski.backend.features.tutors.repository.TutorProfileRepository;
import com.slemanski.backend.infrastructure.security.jwt.JwtService;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    @Value("${security.password.bcrypt.strength}")
    private int bcryptStrength;

    private final MyUserRepository myUserRepository;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(bcryptStrength);
    private final StudentProfileRepository studentProfileRepository;
    private final TutorProfileRepository tutorProfileRepository;

    @Autowired
    public AuthService(MyUserRepository repo, AuthenticationManager authManager, JwtService jwtService, StudentProfileRepository studentProfileRepository, TutorProfileRepository tutorProfileRepository) {
        this.myUserRepository = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.studentProfileRepository = studentProfileRepository;
        this.tutorProfileRepository = tutorProfileRepository;
    }

    @Transactional
    public void register(RegisterRequestDto userDto) {
        if(myUserRepository.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistsException(userDto.getUsername());
        }
        MyUser newUser = new MyUser();
        Role userRole = userDto.getRole();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(encoder.encode(userDto.getPassword()));
        newUser.setRole(userRole);

        myUserRepository.save(newUser);

        if(userRole == Role.STUDENT) {
            StudentProfile newStudentProfile = new StudentProfile();
            newStudentProfile.setUser(newUser);
            studentProfileRepository.save(newStudentProfile);
        } else {
            TutorProfile newTutorProfile = new TutorProfile();
            newTutorProfile.setUser(newUser);
            tutorProfileRepository.save(newTutorProfile);
        }

    }

    public LoginResponseDto verify(LoginRequestDto userDto) {
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

        String token = jwtService.generateToken(userDetails.getUsername(), role);

        return new LoginResponseDto(token);
    }
}
