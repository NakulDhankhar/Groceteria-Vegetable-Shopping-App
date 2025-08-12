package com.groceteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Exception thrown when a bad request is made.
 * Maps to HTTP 400 status code.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    
    private String errorCode;
    
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
} 