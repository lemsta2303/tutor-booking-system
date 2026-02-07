package com.slemanski.backend.features.students.model;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.booking.model.Booking;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy="studentProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();


}
