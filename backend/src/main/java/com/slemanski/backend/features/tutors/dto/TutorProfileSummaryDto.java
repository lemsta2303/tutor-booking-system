package com.slemanski.backend.features.tutors.dto;

import com.slemanski.backend.features.subjects.model.Subject;
import com.slemanski.backend.features.tutors.model.TutorProfile;

public record TutorProfileSummaryDto(long tutorId, String username, Subject subject) {

    public static TutorProfileSummaryDto from(TutorProfile tutor) {
        return new TutorProfileSummaryDto(
                tutor.getTutorId(),
                tutor.getUser().getUsername(),
                tutor.getSubject()
        );
    }
}
