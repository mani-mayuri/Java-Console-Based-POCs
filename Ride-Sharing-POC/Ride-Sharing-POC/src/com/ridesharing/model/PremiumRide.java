package com.ridesharing.model;

/**
 * PremiumRide IS-A RideBooking (INHERITANCE).
 * Charges a higher per-KM rate than StandardRide (POLYMORPHISM).
 */
public class PremiumRide extends RideBooking {

    private static final double RATE_PER_KM = 20.0;

    public PremiumRide(String pickupLocation, String dropLocation,
                        double totalTripDistance, double startingFee) {
        super(pickupLocation, dropLocation, totalTripDistance, startingFee);
    }

    @Override
    public double calculateFare() {
        return getStartingFee() + (getTotalTripDistance() * RATE_PER_KM);
    }

    @Override
    public String estimatedArrivalTime() {
        // Premium rides are modeled as slightly faster (priority pickup)
        int minutes = (int) Math.ceil(getTotalTripDistance() * 1.5);
        return minutes + " mins";
    }
}
