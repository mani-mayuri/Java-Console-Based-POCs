package com.ridesharing.exception;

/**
 * Thrown when there is no available driver to assign to a booking.
 */
public class DriverNotAvailableException extends Exception {

    public DriverNotAvailableException(String message) {
        super(message);
    }
}
