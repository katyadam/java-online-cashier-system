package com.example.onlinecashiersystem.auth.jwt;

import jakarta.servlet.ServletException;

public class UnauthorizedAccessException extends ServletException {
    public UnauthorizedAccessException() {
    }

    public UnauthorizedAccessException(String message) {
        super(message);
    }

    public UnauthorizedAccessException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public UnauthorizedAccessException(Throwable rootCause) {
        super(rootCause);
    }
}
