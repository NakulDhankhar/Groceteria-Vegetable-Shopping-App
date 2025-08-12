package com.groceteria.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standardized API error response structure.
 * Used for consistent error responses across the application.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "API Error Response")
public class ApiError {

    @Schema(description = "HTTP status code", example = "400")
    private HttpStatus status;
    
    @Schema(description = "Error message", example = "Bad Request")
    private String message;
    
    @Schema(description = "Detailed error messages")
    private List<String> errors;
    
    @Schema(description = "Timestamp when error occurred", example = "2024-01-15T10:30:00")
    private LocalDateTime timestamp;
    
    @Schema(description = "Request path that caused the error", example = "/api/v1/users")
    private String path;
    
    @Schema(description = "Error code for client handling", example = "VALIDATION_ERROR")
    private String errorCode;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
        this.timestamp = LocalDateTime.now();
    }
    
    public ApiError(HttpStatus status, String message, String error, String path) {
        this.status = status;
        this.message = message;
        this.errors = Arrays.asList(error);
        this.timestamp = LocalDateTime.now();
        this.path = path;
    }
    
    public ApiError(HttpStatus status, String message, List<String> errors, String path, String errorCode) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.errorCode = errorCode;
    }
}