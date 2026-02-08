package com.slemanski.backend.features.tutors.dto;

import com.slemanski.backend.features.subjects.model.Subject;
import com.slemanski.backend.features.tutors.model.TutorProfile;

public record TutorProfileDetailsDto(
        long tutorId,
        Subject subject,
        int hourlyRate,
        String description) {
    public static TutorProfileDetailsDto from(TutorProfile tutor) {
        return new TutorProfileDetailsDto(
                tutor.getTutorId(),
                tutor.getSubject(),
                tutor.getHourlyRate(),
                tutor.getDescription()
        );
    }
}
