package com.manos.Calculator.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

    private String message;
    private HttpStatus status;

    public ErrorDetails(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
