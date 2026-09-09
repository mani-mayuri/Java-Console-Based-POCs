package com.ridesharing.model;

/**
 * Represents a driver who can be assigned to a booking.
 * Demonstrates ENCAPSULATION: the "available" flag can only be changed
 * through controlled methods (markAvailable / markUnavailable), never
 * directly, so booking logic cannot corrupt driver state.
 */
public class Driver {

    private int driverId;
    private String driverName;
    private String vehicleNumber;
    private boolean available;

    public Driver(int driverId, String driverName, String vehicleNumber) {
        this.driverId = driverId;
        setDriverName(driverName);
        setVehicleNumber(vehicleNumber);
        this.available = true; // new drivers start as available
    }

    public int getDriverId() {
        return driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        if (driverName == null || driverName.trim().isEmpty()) {
            throw new IllegalArgumentException("Driver name cannot be empty.");
        }
        this.driverName = driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        if (vehicleNumber == null || vehicleNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle number cannot be empty.");
        }
        this.vehicleNumber = vehicleNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markUnavailable() {
        this.available = false;
    }

    public void markAvailable() {
        this.available = true;
    }

    @Override
    public String toString() {
        return "Driver{id=" + driverId + ", name='" + driverName +
                "', vehicle='" + vehicleNumber + "', available=" + available + "}";
    }
}
