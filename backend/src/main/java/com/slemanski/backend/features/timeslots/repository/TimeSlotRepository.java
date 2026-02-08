package com.slemanski.backend.features.timeslots.repository;

import com.slemanski.backend.features.timeslots.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

    @Query("""
    select count(ts) > 0 from TimeSlot ts
    where ts.tutorProfile.tutorId = :tutorId
        and ts.startTime < :end
        and ts.endTime > :start
""")
    boolean existsOverlapping(long tutorId, LocalDateTime start, LocalDateTime end);
}
