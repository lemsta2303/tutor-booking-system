package com.slemanski.backend.shared.exception.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class AccessDeniedException extends ApiException {
    public AccessDeniedException(String message) {
        super(
                ErrorCode.ACCESS_DENIED,
                message
        );
    }
}
