package com.slemanski.backend.features.tutors.dto;

import com.slemanski.backend.features.subjects.model.Subject;
import com.slemanski.backend.features.tutors.model.TutorProfile;

public record TutorProfileDetailsDto(
        long id,
        Subject subject,
        int hourlyRate,
        String description) {
    public static TutorProfileDetailsDto from(TutorProfile tutor) {
        return new TutorProfileDetailsDto(
                tutor.getId(),
                tutor.getSubject(),
                tutor.getHourlyRate(),
                tutor.getDescription()
        );
    }
}
