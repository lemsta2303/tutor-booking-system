package com.slemanski.backend.features.booking.repository;

import com.slemanski.backend.features.booking.model.Booking;
import com.slemanski.backend.features.timeslots.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking getByTimeSlot(TimeSlot timeSlot);
}
