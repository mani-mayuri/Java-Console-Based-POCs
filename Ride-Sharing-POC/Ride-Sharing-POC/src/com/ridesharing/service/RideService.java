package com.ridesharing.service;

import com.ridesharing.exception.InvalidRideException;
import com.ridesharing.model.CarpoolRide;
import com.ridesharing.model.PremiumRide;
import com.ridesharing.model.RideBooking;
import com.ridesharing.model.StandardRide;

import java.util.ArrayList;

/**
 * Handles creation, storage and display of rides.
 *
 * Uses an ArrayList<RideBooking> to store every ride created so far.
 * Because RideBooking is abstract, this list can hold StandardRide,
 * PremiumRide and CarpoolRide objects at the same time -- a direct,
 * practical demonstration of POLYMORPHISM (one reference type,
 * many concrete behaviors).
 */
public class RideService {

    private static final double DEFAULT_STARTING_FEE = 30.0;

    private ArrayList<RideBooking> rides = new ArrayList<>();

    public RideBooking createStandardRide(String pickup, String drop, double distance)
            throws InvalidRideException {
        validateRideInput(pickup, drop, distance);
        RideBooking ride = new StandardRide(pickup, drop, distance, DEFAULT_STARTING_FEE);
        rides.add(ride);
        return ride;
    }

    public RideBooking createPremiumRide(String pickup, String drop, double distance)
            throws InvalidRideException {
        validateRideInput(pickup, drop, distance);
        RideBooking ride = new PremiumRide(pickup, drop, distance, DEFAULT_STARTING_FEE);
        rides.add(ride);
        return ride;
    }

    public RideBooking createCarpoolRide(String pickup, String drop, double distance, int passengers)
            throws InvalidRideException {
        validateRideInput(pickup, drop, distance);
        if (passengers <= 0) {
            throw new InvalidRideException("Number of passengers must be at least 1.");
        }
        RideBooking ride = new CarpoolRide(pickup, drop, distance, DEFAULT_STARTING_FEE, passengers);
        rides.add(ride);
        return ride;
    }

    private void validateRideInput(String pickup, String drop, double distance) throws InvalidRideException {
        if (pickup == null || pickup.trim().isEmpty()) {
            throw new InvalidRideException("Pickup location cannot be empty.");
        }
        if (drop == null || drop.trim().isEmpty()) {
            throw new InvalidRideException("Drop location cannot be empty.");
        }
        if (distance <= 0) {
            throw new InvalidRideException("Distance cannot be negative or zero.");
        }
    }

    public void displayAllRides() {
        if (rides.isEmpty()) {
            System.out.println("No rides created yet.");
            return;
        }
        System.out.println("---- All Rides Created ----");
        // Polymorphism in action: same reference type (RideBooking),
        // different runtime behavior for calculateFare()/estimatedArrivalTime()
        for (RideBooking ride : rides) {
            ride.displayTripDetails();
        }
    }

    public int getRideCount() {
        return rides.size();
    }
}
