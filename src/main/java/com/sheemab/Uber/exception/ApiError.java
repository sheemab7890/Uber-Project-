package com.sheemab.Uber.exception;

import jakarta.annotation.security.DenyAll;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@Data
public class ApiError {
    private Instant timestamp;
    private int status;
    private String errorCode;
    private String message;
    private String path;

    public ApiError(int status, String errorCode, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
    }
}
