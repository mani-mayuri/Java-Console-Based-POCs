package com.ridesharing.exception;

/**
 * Thrown when ride details (distance, locations, ride type, etc.) are invalid.
 */
public class InvalidRideException extends Exception {

    public InvalidRideException(String message) {
        super(message);
    }
}
