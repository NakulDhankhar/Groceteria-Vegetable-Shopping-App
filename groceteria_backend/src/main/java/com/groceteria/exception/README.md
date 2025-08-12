# Exception Handling System

## Overview

The Groceteria application implements a comprehensive exception handling system that provides consistent error responses and proper HTTP status codes. This system ensures that all errors are handled uniformly across the application.

## Exception Classes

### Core Exception Classes

#### 1. `ApiError`
- **Purpose**: Standardized error response structure
- **Features**: 
  - HTTP status code
  - Error message
  - Detailed error list
  - Timestamp
  - Request path
  - Error code for client handling
- **Usage**: Used by the global exception handler to format error responses

#### 2. `ResourceNotFoundException`
- **HTTP Status**: 404 Not Found
- **Purpose**: Thrown when a requested resource is not found
- **Usage**: For missing users, items, orders, etc.

#### 3. `BadRequestException`
- **HTTP Status**: 400 Bad Request
- **Purpose**: Thrown for invalid requests
- **Usage**: Invalid input data, malformed requests

#### 4. `UnauthorizedException`
- **HTTP Status**: 401 Unauthorized
- **Purpose**: Thrown for authentication failures
- **Usage**: Invalid credentials, missing authentication

#### 5. `ForbiddenException`
- **HTTP Status**: 403 Forbidden
- **Purpose**: Thrown for authorization failures
- **Usage**: Insufficient permissions, role-based access control

#### 6. `ConflictException`
- **HTTP Status**: 409 Conflict
- **Purpose**: Thrown for resource conflicts
- **Usage**: Duplicate resources, concurrent modifications

#### 7. `ValidationException`
- **HTTP Status**: 400 Bad Request
- **Purpose**: Thrown for validation failures
- **Usage**: Data validation errors, business rule violations

#### 8. `PaymentProcessingException`
- **HTTP Status**: 500 Internal Server Error
- **Purpose**: Thrown for payment processing failures
- **Usage**: Payment gateway errors, transaction failures

## Global Exception Handler

### `CustomizedResponseEntityExceptionHandler`

This class extends `ResponseEntityExceptionHandler` and provides centralized exception handling for the entire application.

#### Features:
- **Validation Error Handling**: Handles `@Valid` annotation errors
- **Resource Not Found**: Handles missing resources
- **Bad Request**: Handles invalid requests
- **Authentication/Authorization**: Handles security-related errors
- **Constraint Violations**: Handles JPA constraint violations
- **Type Mismatch**: Handles parameter type conversion errors
- **Generic Error Handling**: Catches unexpected exceptions

#### Error Response Format:
```json
{
  "status": "BAD_REQUEST",
  "message": "Validation failed",
  "errors": [
    "email: must be a well-formed email address",
    "password: must not be empty"
  ],
  "timestamp": "2024-01-15T10:30:00",
  "path": "/api/v1/users/register",
  "errorCode": "VALIDATION_ERROR"
}
```

## Utility Classes

### `ExceptionUtils`

Provides convenient methods for throwing exceptions with consistent messaging.

#### Key Methods:
- `throwIfNotFound()`: Throw ResourceNotFoundException
- `throwIfBadRequest()`: Throw BadRequestException
- `throwIfUnauthorized()`: Throw UnauthorizedException
- `throwIfForbidden()`: Throw ForbiddenException
- `throwIfConflict()`: Throw ConflictException
- `throwIfInvalid()`: Throw ValidationException
- `throwIfPaymentFailed()`: Throw PaymentProcessingException
- `requireNonNull()`: Check for null values
- `requireNonEmpty()`: Check for empty strings
- `requireTrue()`: Check boolean conditions
- `requireMinValue()`: Check minimum values
- `requireMaxValue()`: Check maximum values
- `requireInRange()`: Check value ranges

### `ErrorConstants`

Contains standardized error messages and codes for consistent error handling.

#### Categories:
- **User-related errors**: Authentication, registration, profile management
- **Item-related errors**: Product management, inventory
- **Order-related errors**: Order processing, status management
- **Cart-related errors**: Shopping cart operations
- **Payment-related errors**: Payment processing
- **Validation errors**: Data validation
- **Security errors**: Authentication and authorization
- **General errors**: Common application errors

## Usage Examples

### 1. Throwing ResourceNotFoundException
```java
// In service layer
User user = userRepository.findById(userId)
    .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
```

### 2. Using ExceptionUtils
```java
// Check for null
User user = ExceptionUtils.requireNonNull(
    userRepository.findById(userId).orElse(null),
    "User not found"
);

// Validate email
String email = ExceptionUtils.requireNonEmpty(userEmail, "Email is required");

// Check permissions
ExceptionUtils.throwIfForbidden(
    !user.getRole().equals("VENDOR"),
    "Only vendors can add items"
);
```

### 3. Custom Exception Handling
```java
// In controller
@PostMapping("/register")
public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
    try {
        UserDTO registeredUser = userService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    } catch (ConflictException e) {
        // Handled by global exception handler
        throw e;
    }
}
```

## Error Codes

The application uses standardized error codes for client-side error handling:

- `USER_NOT_FOUND`: User not found
- `USER_EXISTS`: User already exists
- `INVALID_CREDENTIALS`: Invalid login credentials
- `VENDOR_ONLY`: Operation restricted to vendors
- `ITEM_NOT_FOUND`: Item not found
- `OUT_OF_STOCK`: Item out of stock
- `ORDER_NOT_FOUND`: Order not found
- `PAYMENT_FAILED`: Payment processing failed
- `VALIDATION_ERROR`: Data validation error
- `AUTHENTICATION_FAILED`: Authentication error
- `AUTHORIZATION_FAILED`: Authorization error

## Best Practices

1. **Use Specific Exceptions**: Choose the most appropriate exception type
2. **Provide Clear Messages**: Error messages should be user-friendly
3. **Include Error Codes**: Use standardized error codes for client handling
4. **Log Exceptions**: Always log exceptions for debugging
5. **Don't Expose Internals**: Avoid exposing sensitive information in error messages
6. **Use ExceptionUtils**: Leverage utility methods for common scenarios
7. **Consistent Formatting**: Use ErrorConstants for consistent messaging

## Testing

When testing exception handling:

1. **Test Exception Scenarios**: Verify correct HTTP status codes
2. **Test Error Messages**: Ensure error messages are clear and helpful
3. **Test Error Codes**: Verify error codes are consistent
4. **Test Validation**: Test input validation error handling
5. **Test Security**: Test authentication and authorization error handling

## Future Enhancements

1. **Internationalization**: Support for multiple languages
2. **Error Tracking**: Integration with error tracking services
3. **Rate Limiting**: Handle rate limiting exceptions
4. **Circuit Breaker**: Handle service unavailability
5. **Audit Logging**: Enhanced error logging for compliance 