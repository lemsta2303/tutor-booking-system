package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private MyUserRepository repo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public AuthService(MyUserRepository repo) {
        this.repo = repo;
    }

    public MyUser register(MyUser user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }
}
