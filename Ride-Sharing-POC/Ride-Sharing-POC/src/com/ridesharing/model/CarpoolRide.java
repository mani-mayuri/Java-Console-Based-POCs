package com.ridesharing.model;

/**
 * CarpoolRide IS-A RideBooking (INHERITANCE).
 * Demonstrates shared-ride pricing: the total fare is split among
 * the number of passengers sharing the ride (POLYMORPHISM).
 */
public class CarpoolRide extends RideBooking {

    private static final double RATE_PER_KM = 6.0;
    private int numberOfPassengers;

    public CarpoolRide(String pickupLocation, String dropLocation,
                        double totalTripDistance, double startingFee,
                        int numberOfPassengers) {
        super(pickupLocation, dropLocation, totalTripDistance, startingFee);
        setNumberOfPassengers(numberOfPassengers);
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        if (numberOfPassengers <= 0) {
            throw new IllegalArgumentException("Number of passengers must be at least 1.");
        }
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    @Override
    public double calculateFare() {
        double totalFare = getStartingFee() + (getTotalTripDistance() * RATE_PER_KM);
        return totalFare / numberOfPassengers;
    }

    @Override
    public String estimatedArrivalTime() {
        // Carpool rides take a bit longer due to multiple pickups/drops
        int minutes = (int) Math.ceil(getTotalTripDistance() * 2.5);
        return minutes + " mins";
    }
}
