package com.groceteria.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * Global exception handler for the Groceteria application.
 * Provides centralized exception handling and standardized error responses.
 */
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle validation errors from @Valid annotations.
     */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
		    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
		        errors.add(error.getField() + ": " + error.getDefaultMessage());
		    }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation failed")
                .errors(errors)
                .path(getRequestPath(request))
                .errorCode("VALIDATION_ERROR")
                .build();
        
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * Handle ResourceNotFoundException.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Resource not found")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("RESOURCE_NOT_FOUND")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle BadRequestException.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Bad request")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("BAD_REQUEST")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle UnauthorizedException.
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.UNAUTHORIZED)
                .message("Authentication failed")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("UNAUTHORIZED")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handle ForbiddenException.
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.FORBIDDEN)
                .message("Access forbidden")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("FORBIDDEN")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    /**
     * Handle ConflictException.
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .message("Resource conflict")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("CONFLICT")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    /**
     * Handle ValidationException.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Validation error")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("VALIDATION_ERROR")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle PaymentProcessingException.
     */
    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<Object> handlePaymentProcessingException(PaymentProcessingException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Payment processing failed")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("PAYMENT_ERROR")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle ConstraintViolationException.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }
        
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Constraint violation")
                .errors(errors)
                .path(getRequestPath(request))
                .errorCode("CONSTRAINT_VIOLATION")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle MethodArgumentTypeMismatchException.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = String.format("Parameter '%s' should be of type %s", 
                ex.getName(), ex.getRequiredType().getSimpleName());
        
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("Type mismatch")
                .errors(List.of(error))
                .path(getRequestPath(request))
                .errorCode("TYPE_MISMATCH")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle NoHandlerFoundException.
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, 
            HttpStatusCode status, WebRequest request) {
        String error = String.format("No handler found for %s %s", ex.getHttpMethod(), ex.getRequestURL());
        
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message("Endpoint not found")
                .errors(List.of(error))
                .path(getRequestPath(request))
                .errorCode("ENDPOINT_NOT_FOUND")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle generic RuntimeException.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Internal server error")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("INTERNAL_ERROR")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle generic Exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An unexpected error occurred")
                .errors(List.of(ex.getMessage()))
                .path(getRequestPath(request))
                .errorCode("UNEXPECTED_ERROR")
                .build();
        
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Extract request path from WebRequest.
     */
    private String getRequestPath(WebRequest request) {
        if (request.getDescription(false) != null) {
            return request.getDescription(false).replace("uri=", "");
        }
        return "Unknown";
    }
}

