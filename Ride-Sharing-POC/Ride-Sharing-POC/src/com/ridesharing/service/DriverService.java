package com.ridesharing.service;

import com.ridesharing.exception.DriverNotAvailableException;
import com.ridesharing.model.Driver;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Handles all driver-related operations.
 *
 * Uses an ArrayList<Driver> to store drivers in registration order,
 * and a HashSet<Integer> to guarantee driver IDs are unique
 * (a Set rejects duplicates automatically, unlike a List).
 */
public class DriverService {

    private ArrayList<Driver> drivers = new ArrayList<>();
    private HashSet<Integer> driverIds = new HashSet<>();

    public void registerDriver(Driver driver) {
        if (driverIds.contains(driver.getDriverId())) {
            System.out.println("Driver ID " + driver.getDriverId() + " already exists. Registration skipped.");
            return;
        }
        drivers.add(driver);
        driverIds.add(driver.getDriverId());
        System.out.println("Driver registered successfully: " + driver.getDriverName());
    }

    public void viewDrivers() {
        if (drivers.isEmpty()) {
            System.out.println("No drivers registered yet.");
            return;
        }
        System.out.println("---- All Drivers ----");
        for (Driver driver : drivers) {
            System.out.println(driver);
        }
    }

    public void viewAvailableDrivers() {
        boolean found = false;
        System.out.println("---- Available Drivers ----");
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                System.out.println(driver);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No drivers are currently available.");
        }
    }

    /**
     * Finds the first available driver.
     * Throws DriverNotAvailableException when none are free.
     */
    public Driver findAvailableDriver() throws DriverNotAvailableException {
        for (Driver driver : drivers) {
            if (driver.isAvailable()) {
                return driver;
            }
        }
        throw new DriverNotAvailableException("No available driver at the moment. Please try again later.");
    }

    public void assignDriver(Driver driver) {
        driver.markUnavailable();
    }

    public void releaseDriver(Driver driver) {
        driver.markAvailable();
    }

    public boolean isDriverIdTaken(int driverId) {
        return driverIds.contains(driverId);
    }

    public int getDriverCount() {
        return drivers.size();
    }
}
