package com.slemanski.backend.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleApiException(ApiException exc) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage(exc.getMessage());
        error.setErrorCode(exc.getErrorCode().name());
        error.setStatus(exc.getErrorCode().status().value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, exc.getErrorCode().status());
    }

    // fallback for exceptions beside rest pai
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnyException(Exception exc) {
        ErrorResponse error = new ErrorResponse();
        error.setMessage("Unexpected error");
        error.setErrorCode("INTERNAL_ERROR");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
