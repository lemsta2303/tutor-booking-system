package com.slemanski.backend.features.tutors.model;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.subjects.model.Subject;
import com.slemanski.backend.features.timeslots.model.TimeSlot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tutor_profiles")
@Getter
@Setter
public class TutorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tutorId;

    @Column(name="subject")
    private Subject subject;

    @Column(name="hourly_rate")
    private int hourlyRate;

    @Column(name="description")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private MyUser user;

    @OneToMany(mappedBy = "tutorProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeSlot> timeSlots = new ArrayList<>();




}
