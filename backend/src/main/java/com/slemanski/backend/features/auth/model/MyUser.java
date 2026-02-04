package com.slemanski.backend.features.auth.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class MyUser {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, name="username")
    private String username;

    @Column(nullable = false, name="password")
    private String password;

//    @Column(nullable = false, name="role")
//    private Role role;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

//enum Role {
//    Tutor,
//    Student
//}
