package com.sheemab.Uber.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // ------------------ 400 BAD REQUEST ------------------
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(
            BadRequestException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage(), request);
    }

    // ------------------ 401 UNAUTHORIZED ------------------
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiError> handleUnauthorized(
            UnauthorizedException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", ex.getMessage(), request);
    }

    // ------------------ 403 FORBIDDEN ------------------
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbidden(
            ForbiddenException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.FORBIDDEN, "FORBIDDEN", ex.getMessage(), request);
    }

    // ------------------ 404 NOT FOUND ------------------
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUND", ex.getMessage(), request);
    }

    // ------------------ 409 CONFLICT ------------------
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflict(
            ConflictException ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.CONFLICT, "CONFLICT", ex.getMessage(), request);
    }

    // ------------------ 422 VALIDATION ERROR ------------------
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Validation failed");

        return buildError(HttpStatus.UNPROCESSABLE_ENTITY,
                "VALIDATION_ERROR",
                message,
                request);
    }

    // ------------------ 500 INTERNAL SERVER ERROR ------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(
            Exception ex,
            HttpServletRequest request) {

        return buildError(HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_SERVER_ERROR",
                "Something went wrong. Please try again.",
                request);
    }

    // ------------------ COMMON BUILDER ------------------
    private ResponseEntity<ApiError> buildError(
            HttpStatus status,
            String errorCode,
            String message,
            HttpServletRequest request) {

        ApiError error = new ApiError(
                status.value(),
                errorCode,
                message,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }
}
