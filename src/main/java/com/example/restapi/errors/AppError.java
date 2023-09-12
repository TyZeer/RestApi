package com.example.restapi.errors;

import org.springframework.http.HttpStatus;

public class AppError {
    private int statusCode;
    private String message;

    public AppError(int value, String serverNotWorking, HttpStatus httpStatus) {
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppError() {
    }

    public AppError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
