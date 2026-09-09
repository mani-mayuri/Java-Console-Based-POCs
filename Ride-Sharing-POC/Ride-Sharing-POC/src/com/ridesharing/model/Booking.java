package com.ridesharing.model;

/**
 * Represents a confirmed booking that ties together a Customer, a Driver,
 * a RideBooking (the ride type/fare details) and a BookingStatus.
 */
public class Booking {

    private int bookingId;
    private Customer customer;
    private Driver driver;
    private RideBooking ride;
    private BookingStatus bookingStatus;
    private double fare;

    public Booking(int bookingId, Customer customer, Driver driver, RideBooking ride) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.driver = driver;
        this.ride = ride;
        this.fare = ride.getFinalFare();
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public RideBooking getRide() {
        return ride;
    }

    public double getFare() {
        return fare;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void displayBookingDetails() {
        System.out.println("------------------------------------------------");
        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Status         : " + bookingStatus);
        System.out.println("Customer       : " + customer.getCustomerName() +
                " (" + customer.getPhoneNumber() + ")");
        System.out.println("Driver         : " + driver.getDriverName() +
                " | Vehicle: " + driver.getVehicleNumber());
        ride.displayTripDetails();
        System.out.println("Final Fare     : Rs. " + String.format("%.2f", fare));
        System.out.println("------------------------------------------------");
    }
}
