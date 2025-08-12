package com.groceteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Exception thrown when there is a conflict with existing resources.
 * Maps to HTTP 409 status code.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    
    public ConflictException(String message) {
        super(message);
    }
    
    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
} 