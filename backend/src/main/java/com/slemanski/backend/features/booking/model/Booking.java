package com.slemanski.backend.features.booking.model;

import com.slemanski.backend.features.students.model.StudentProfile;
import com.slemanski.backend.features.timeslots.model.TimeSlot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="booking")
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="student_profile_id", nullable = false)
    private StudentProfile studentProfile;

    @OneToOne(optional = false)
    @JoinColumn(name="time_slot_id", nullable=false, unique = true)
    private TimeSlot timeSlot;


}
