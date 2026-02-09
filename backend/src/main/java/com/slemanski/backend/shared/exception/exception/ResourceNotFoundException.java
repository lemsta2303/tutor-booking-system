package com.slemanski.backend.shared.exception.exception;

import com.slemanski.backend.shared.exception.ApiException;
import com.slemanski.backend.shared.exception.ErrorCode;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String message) {
        super(
                ErrorCode.RESOURCE_NOT_FOUND,
                message
        );
    }
}
