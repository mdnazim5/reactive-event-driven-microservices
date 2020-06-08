package com.nazim.exception;

import javax.ws.rs.ext.Provider;

/**
 * Exception representing situation where the given car doesn't exists.
 */
@Provider
public class CarNotFoundException extends RuntimeException {
    private int status;

    public CarNotFoundException(final String message, final int status) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
