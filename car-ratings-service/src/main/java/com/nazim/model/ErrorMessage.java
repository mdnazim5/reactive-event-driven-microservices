package com.nazim.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Class represent the error message returned from the service.
 */
public class ErrorMessage {

    private String message;

    private int status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    /**
     * Constructor.
     *
     * @param status Http response status
     *
     * @param message the error message explaining the cause
     */
    public ErrorMessage(final int status, final String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
