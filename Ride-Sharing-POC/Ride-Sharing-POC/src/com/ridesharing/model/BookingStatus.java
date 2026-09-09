package com.ridesharing.model;

/**
 * Represents the possible states of a Booking.
 * Using an enum instead of raw Strings avoids invalid status values.
 */
public enum BookingStatus {
    CONFIRMED,
    COMPLETED,
    CANCELLED
}
