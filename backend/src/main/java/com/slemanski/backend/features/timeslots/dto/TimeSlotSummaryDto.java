package com.slemanski.backend.features.timeslots.dto;

import com.slemanski.backend.features.timeslots.model.TimeSlot;

import java.time.LocalDateTime;

public record TimeSlotSummaryDto(
        long id,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public static TimeSlotSummaryDto from(TimeSlot timeslot) {
        return new TimeSlotSummaryDto(
                timeslot.getId(),
                timeslot.getStartTime(),
                timeslot.getEndTime()
        );
    }
}
