package com.slemanski.backend.features.auth.model;

import com.slemanski.backend.features.students.model.StudentProfile;
import com.slemanski.backend.features.tutors.model.TutorProfile;
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

    @OneToOne(mappedBy = "user")
    private TutorProfile tutorProfile;

    @OneToOne(mappedBy = "user")
    private StudentProfile studentProfile;

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", tutorProfile=" + tutorProfile +
                ", studentProfile=" + studentProfile +
                '}';
    }
}
