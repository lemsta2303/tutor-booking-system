package com.slemanski.backend.features.tutors.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class TutorNotFoundException extends ApiException {
    public TutorNotFoundException(long tutorId) {
        super(
                ErrorCode.USER_NOT_FOUND,
                "Tutor with id '" + tutorId + "' was not found."
        );
    }
}
