package com.groceteria.exception;

import java.util.function.Supplier;

/**
 * Utility class for common exception scenarios.
 * Provides convenient methods for throwing exceptions with consistent messaging.
 */
public class ExceptionUtils {

    /**
     * Throw ResourceNotFoundException if condition is true.
     */
    public static void throwIfNotFound(boolean condition, String resourceName, String fieldName, Object fieldValue) {
        if (condition) {
            throw new ResourceNotFoundException(resourceName, fieldName, fieldValue);
        }
    }

    /**
     * Throw ResourceNotFoundException if object is null.
     */
    public static <T> T throwIfNotFound(T object, String resourceName, String fieldName, Object fieldValue) {
        if (object == null) {
            throw new ResourceNotFoundException(resourceName, fieldName, fieldValue);
        }
        return object;
    }

    /**
     * Throw BadRequestException if condition is true.
     */
    public static void throwIfBadRequest(boolean condition, String message) {
        if (condition) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Throw BadRequestException if condition is true.
     */
    public static void throwIfBadRequest(boolean condition, String message, String errorCode) {
        if (condition) {
            throw new BadRequestException(message, errorCode);
        }
    }

    /**
     * Throw UnauthorizedException if condition is true.
     */
    public static void throwIfUnauthorized(boolean condition, String message) {
        if (condition) {
            throw new UnauthorizedException(message);
        }
    }

    /**
     * Throw ForbiddenException if condition is true.
     */
    public static void throwIfForbidden(boolean condition, String message) {
        if (condition) {
            throw new ForbiddenException(message);
        }
    }

    /**
     * Throw ConflictException if condition is true.
     */
    public static void throwIfConflict(boolean condition, String message) {
        if (condition) {
            throw new ConflictException(message);
        }
    }

    /**
     * Throw ValidationException if condition is true.
     */
    public static void throwIfInvalid(boolean condition, String message) {
        if (condition) {
            throw new ValidationException(message);
        }
    }

    /**
     * Throw PaymentProcessingException if condition is true.
     */
    public static void throwIfPaymentFailed(boolean condition, String message) {
        if (condition) {
            throw new PaymentProcessingException(message);
        }
    }

    /**
     * Throw exception if object is null, otherwise return the object.
     */
    public static <T> T requireNonNull(T object, String message) {
        if (object == null) {
            throw new BadRequestException(message);
        }
        return object;
    }

    /**
     * Throw exception if string is null or empty.
     */
    public static String requireNonEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new BadRequestException(message);
        }
        return value;
    }

    /**
     * Throw exception if condition is false.
     */
    public static void requireTrue(boolean condition, String message) {
        if (!condition) {
            throw new BadRequestException(message);
        }
    }

    /**
     * Throw exception if condition is false.
     */
    public static void requireTrue(boolean condition, Supplier<String> messageSupplier) {
        if (!condition) {
            throw new BadRequestException(messageSupplier.get());
        }
    }

    /**
     * Throw exception if value is null or less than minimum.
     */
    public static <T extends Comparable<T>> T requireMinValue(T value, T minValue, String message) {
        if (value == null || value.compareTo(minValue) < 0) {
            throw new BadRequestException(message);
        }
        return value;
    }

    /**
     * Throw exception if value is null or greater than maximum.
     */
    public static <T extends Comparable<T>> T requireMaxValue(T value, T maxValue, String message) {
        if (value == null || value.compareTo(maxValue) > 0) {
            throw new BadRequestException(message);
        }
        return value;
    }

    /**
     * Throw exception if value is not in the specified range.
     */
    public static <T extends Comparable<T>> T requireInRange(T value, T minValue, T maxValue, String message) {
        if (value == null || value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0) {
            throw new BadRequestException(message);
        }
        return value;
    }
} 