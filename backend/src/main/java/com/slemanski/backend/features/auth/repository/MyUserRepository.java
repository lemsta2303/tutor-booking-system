package com.slemanski.backend.features.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.slemanski.backend.features.auth.model.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
