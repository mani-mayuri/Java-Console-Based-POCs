package com.ridesharing.model;

/**
 * StandardRide IS-A RideBooking (INHERITANCE).
 * Provides its own fare formula (POLYMORPHISM via method overriding).
 */
public class StandardRide extends RideBooking {

    private static final double RATE_PER_KM = 10.0;

    public StandardRide(String pickupLocation, String dropLocation,
                         double totalTripDistance, double startingFee) {
        super(pickupLocation, dropLocation, totalTripDistance, startingFee);
    }

    @Override
    public double calculateFare() {
        return getStartingFee() + (getTotalTripDistance() * RATE_PER_KM);
    }

    @Override
    public String estimatedArrivalTime() {
        int minutes = (int) Math.ceil(getTotalTripDistance() * 2); // ~2 min per KM
        return minutes + " mins";
    }
}
