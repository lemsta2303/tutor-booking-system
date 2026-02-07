package com.slemanski.backend.features.timeslots.repository;

import com.slemanski.backend.features.timeslots.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {

}
