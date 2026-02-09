package com.slemanski.backend.features.tutors.dto;

import com.slemanski.backend.features.subjects.model.Subject;

public record TutorProfileUpdateDto(
        Subject subject,
        Integer hourlyRate,
        String description
) {
}
