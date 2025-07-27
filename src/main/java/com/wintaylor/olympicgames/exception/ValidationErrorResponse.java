package com.wintaylor.olympicgames.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ValidationErrorResponse {
    private int status;
    private String message;
    private List<ValidationError> errors;
    private LocalDateTime timestamp;

    public ValidationErrorResponse(int status, String message, List<ValidationError> errors, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = timestamp;
    }
}
