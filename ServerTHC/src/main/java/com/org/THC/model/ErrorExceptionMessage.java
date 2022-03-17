package com.org.THC.model;

import lombok.Data;

@Data
public class ErrorExceptionMessage {
    private String timestamp;
    private String status;
    private String error;
    private String exception;
    private String message;
    private String path;

    public ErrorExceptionMessage(String timestamp, String status, String error, String exception, String message, String path) {
        super();
        this.message = message;
        this.status = status;
        this.error=error;
        this.exception = exception;
        this.timestamp=timestamp;
        this.path=path;
    }

    public ErrorExceptionMessage(String status, String error, String message) {
        super();
        this.message = message;
        this.status = status;
        this.error=error;
        this.exception = exception;
        this.timestamp=timestamp;
        this.path=path;
    }
}
