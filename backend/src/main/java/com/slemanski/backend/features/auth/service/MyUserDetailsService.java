package com.slemanski.backend.features.auth.service;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.model.MyUserDetails;
import com.slemanski.backend.features.auth.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser user = repo.findByUsername(username);

        if(user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }

        return new MyUserDetails(user);
    }
}
