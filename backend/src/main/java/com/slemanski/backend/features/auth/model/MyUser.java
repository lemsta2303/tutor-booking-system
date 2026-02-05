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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, name="username")
    private String username;

    @Column(nullable = false, name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="role")
    private Role role;

    @PrePersist
    public void prePersist() {
        if(role == null) {
            role = Role.STUDENT;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
