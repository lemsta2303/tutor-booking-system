package com.slemanski.backend.features.students.model;

import com.slemanski.backend.features.auth.model.MyUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="student_profiles")
@Getter
@Setter
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private MyUser user;


}
