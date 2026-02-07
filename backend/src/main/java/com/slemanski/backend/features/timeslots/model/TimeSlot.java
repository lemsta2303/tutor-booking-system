package com.slemanski.backend.features.timeslots.model;

import com.slemanski.backend.features.tutors.model.TutorProfile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="time_slots")
@Getter
@Setter
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="tutor_profile_id", nullable = false)
    private TutorProfile tutorProfile;

    @Column(name="start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name="end_time", nullable = false)
    private LocalDateTime endTime;




}
