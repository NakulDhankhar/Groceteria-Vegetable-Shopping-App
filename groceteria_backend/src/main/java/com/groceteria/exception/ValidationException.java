package com.groceteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Exception thrown when validation fails.
 * Maps to HTTP 400 status code.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    
    public ValidationException(String message) {
        super(message);
    }
    
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
} 