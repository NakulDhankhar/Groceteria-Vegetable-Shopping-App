package com.groceteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Exception thrown when payment processing fails.
 * Maps to HTTP 500 status code.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class PaymentProcessingException extends RuntimeException {
    
    public PaymentProcessingException(String message) {
        super(message);
    }
    
    public PaymentProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
} 