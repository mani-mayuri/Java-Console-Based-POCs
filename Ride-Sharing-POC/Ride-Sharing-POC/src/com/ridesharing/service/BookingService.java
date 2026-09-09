package com.ridesharing.service;

import com.ridesharing.exception.BookingNotFoundException;
import com.ridesharing.model.Booking;
import com.ridesharing.model.BookingStatus;
import com.ridesharing.model.Customer;
import com.ridesharing.model.Driver;
import com.ridesharing.model.RideBooking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Handles booking creation, cancellation, completion and lookup.
 *
 * Uses a HashMap<Integer, Booking> so a booking can be retrieved instantly
 * by its bookingId (O(1) lookup) instead of scanning a list.
 * Also keeps an ArrayList<Booking> as a simple, ordered booking history log.
 */
public class BookingService {

    private HashMap<Integer, Booking> bookings = new HashMap<>();
    private ArrayList<Booking> bookingHistory = new ArrayList<>();
    private int nextBookingId = 1001;

    public Booking createBooking(Customer customer, Driver driver, RideBooking ride) {
        int bookingId = nextBookingId++;
        Booking booking = new Booking(bookingId, customer, driver, ride);
        bookings.put(bookingId, booking);
        bookingHistory.add(booking);
        return booking;
    }

    public Booking findBooking(int bookingId) throws BookingNotFoundException {
        if (!bookings.containsKey(bookingId)) {
            throw new BookingNotFoundException("No booking found with ID: " + bookingId);
        }
        return bookings.get(bookingId);
    }

    public void cancelBooking(int bookingId) throws BookingNotFoundException {
        Booking booking = findBooking(bookingId);
        if (booking.getBookingStatus() == BookingStatus.COMPLETED) {
            System.out.println("Booking " + bookingId + " is already completed and cannot be cancelled.");
            return;
        }
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            System.out.println("Booking " + bookingId + " is already cancelled.");
            return;
        }
        booking.setBookingStatus(BookingStatus.CANCELLED);
        booking.getDriver().markAvailable();
        System.out.println("Booking " + bookingId + " has been cancelled. Driver released.");
    }

    public void completeBooking(int bookingId) throws BookingNotFoundException {
        Booking booking = findBooking(bookingId);
        if (booking.getBookingStatus() != BookingStatus.CONFIRMED) {
            System.out.println("Only CONFIRMED bookings can be completed.");
            return;
        }
        booking.setBookingStatus(BookingStatus.COMPLETED);
        booking.getDriver().markAvailable();
        System.out.println("Booking " + bookingId + " marked as COMPLETED. Driver released.");
    }

    public void displayBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("No bookings have been made yet.");
            return;
        }
        System.out.println("---- Booking History ----");
        // Demonstrates Iterator usage over a Collection
        Iterator<Booking> iterator = bookingHistory.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            booking.displayBookingDetails();
        }
    }

    public void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        System.out.println("---- All Bookings (by Booking ID) ----");
        for (Map.Entry<Integer, Booking> entry : bookings.entrySet()) {
            System.out.println("Booking ID " + entry.getKey() + " -> Status: "
                    + entry.getValue().getBookingStatus());
        }
    }

    public int getTotalBookingCount() {
        return bookings.size();
    }
}
