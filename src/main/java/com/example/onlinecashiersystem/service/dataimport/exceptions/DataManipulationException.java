package com.example.onlinecashiersystem.service.dataimport.exceptions;

public class DataManipulationException extends RuntimeException {
    public DataManipulationException() {
    }

    public DataManipulationException(String message) {
        super(message);
    }

    public DataManipulationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataManipulationException(Throwable cause) {
        super(cause);
    }

    public DataManipulationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
