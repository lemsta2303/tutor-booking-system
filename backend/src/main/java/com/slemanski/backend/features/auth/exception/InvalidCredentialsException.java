package com.slemanski.backend.features.auth.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class InvalidCredentialsException extends ApiException {
    public InvalidCredentialsException() {
        super(
                ErrorCode.INVALID_CREDENTIALS,
                "Credentials are invalid"
        );
    }
}
