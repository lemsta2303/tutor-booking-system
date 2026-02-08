package com.slemanski.backend.shared.exception.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super(
                ErrorCode.USER_NOT_FOUND,
                "User with given id not found."
        );
    }
}
