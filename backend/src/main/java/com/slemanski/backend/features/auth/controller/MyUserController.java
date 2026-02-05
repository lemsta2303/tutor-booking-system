package com.slemanski.backend.features.auth.controller;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class MyUserController {

    MyUserService myUserService;

    @Autowired
    public MyUserController(MyUserService myUserService) {
        this.myUserService = myUserService;
    }

    @GetMapping
    public List<MyUser> getAllUsers() {
        return this.myUserService.getAllUsers();
    }
}
