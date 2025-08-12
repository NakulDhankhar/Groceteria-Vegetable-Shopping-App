package com.groceteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Exception thrown when access is forbidden.
 * Maps to HTTP 403 status code.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    
    public ForbiddenException(String message) {
        super(message);
    }
    
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
} 