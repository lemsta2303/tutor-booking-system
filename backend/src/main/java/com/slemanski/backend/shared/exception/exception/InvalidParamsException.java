package com.slemanski.backend.shared.exception.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class InvalidParamsException extends ApiException {
    public InvalidParamsException(String message) {
        super(
                ErrorCode.VALIDATION_ERROR,
                message
        );
    }
}
