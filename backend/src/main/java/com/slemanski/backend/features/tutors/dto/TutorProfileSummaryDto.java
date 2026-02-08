package com.slemanski.backend.features.tutors.dto;

import com.slemanski.backend.features.subjects.model.Subject;
import com.slemanski.backend.features.tutors.model.TutorProfile;

public record TutorProfileSummaryDto(long id, String username, Subject subject) {

    public static TutorProfileSummaryDto from(TutorProfile tutor) {
        return new TutorProfileSummaryDto(
                tutor.getId(),
                tutor.getUser().getUsername(),
                tutor.getSubject()
        );
    }
}
