package com.slemanski.backend.features.auth.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException(String username) {
        super(
                ErrorCode.USER_ALREADY_EXISTS,
                "User with username '" + username + "' already exists."
        );
    }
}
