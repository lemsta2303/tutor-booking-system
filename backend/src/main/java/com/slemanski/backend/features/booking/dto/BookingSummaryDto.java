package com.slemanski.backend.features.booking.dto;

import com.slemanski.backend.features.booking.model.Booking;
import com.slemanski.backend.features.subjects.model.Subject;

import java.time.LocalDateTime;

public record BookingSummaryDto(
        long id,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Subject subject,
        String tutorUsername
) {
    static public BookingSummaryDto from(Booking booking) {
        return new BookingSummaryDto(
                booking.getId(),
                booking.getTimeSlot().getStartTime(),
                booking.getTimeSlot().getEndTime(),
                booking.getTimeSlot().getTutorProfile().getSubject(),
                booking.getTimeSlot().getTutorProfile().getUser().getUsername()
        );
    }

}
