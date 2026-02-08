package com.slemanski.backend.shared.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    //validation error handling
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException exc) {
        ErrorResponse error = new ErrorResponse();
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        error.setStatus(errorCode.status().value());
        error.setErrorCode(errorCode.name());
        error.setMessage("Validation failed");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // fallback for other exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAnyException(Exception exc) {
        ErrorResponse error = new ErrorResponse();
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        error.setMessage(exc.getMessage());
        error.setStatus(errorCode.status().value());
        error.setErrorCode(errorCode.name());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
