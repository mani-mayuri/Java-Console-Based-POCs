package com.ridesharing;

import com.ridesharing.exception.BookingNotFoundException;
import com.ridesharing.exception.DriverNotAvailableException;
import com.ridesharing.exception.InvalidRideException;
import com.ridesharing.model.Booking;
import com.ridesharing.model.Customer;
import com.ridesharing.model.Driver;
import com.ridesharing.model.RideBooking;
import com.ridesharing.service.BookingService;
import com.ridesharing.service.DriverService;
import com.ridesharing.service.RideService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Entry point of the Ride-Sharing / Ride Booking Core Java POC.
 *
 * Customer storage lives here (ArrayList<Customer> + HashSet<Integer> for
 * unique customer IDs), while ride, driver and booking logic are delegated
 * to their respective service classes to keep responsibilities separated.
 */
public class RideSharingApplication {

    private static ArrayList<Customer> customers = new ArrayList<>();
    private static HashSet<Integer> customerIds = new HashSet<>();
    private static int nextCustomerId = 1;
    private static int nextDriverId = 1;

    private static RideService rideService = new RideService();
    private static DriverService driverService = new DriverService();
    private static BookingService bookingService = new BookingService();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("================================================");
        System.out.println("          RIDE-SHARING SYSTEM (Core Java POC)");
        System.out.println("================================================");

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    registerDriver();
                    break;
                case 3:
                    viewCustomers();
                    break;
                case 4:
                    driverService.viewDrivers();
                    break;
                case 5:
                    driverService.viewAvailableDrivers();
                    break;
                case 6:
                    bookRide("STANDARD");
                    break;
                case 7:
                    bookRide("PREMIUM");
                    break;
                case 8:
                    bookRide("CARPOOL");
                    break;
                case 9:
                    viewBooking();
                    break;
                case 10:
                    bookingService.displayAllBookings();
                    break;
                case 11:
                    completeBooking();
                    break;
                case 12:
                    cancelBooking();
                    break;
                case 13:
                    running = false;
                    System.out.println("Thank you for using the Ride-Sharing System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 13.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("1.  Register Customer");
        System.out.println("2.  Register Driver");
        System.out.println("3.  View Customers");
        System.out.println("4.  View Drivers");
        System.out.println("5.  View Available Drivers");
        System.out.println("6.  Book Standard Ride");
        System.out.println("7.  Book Premium Ride");
        System.out.println("8.  Book Carpool Ride");
        System.out.println("9.  View Booking");
        System.out.println("10. View All Bookings");
        System.out.println("11. Complete Booking");
        System.out.println("12. Cancel Booking");
        System.out.println("13. Exit");
        System.out.println("------------------------------------------------");
    }

    // ---------------------- Customer Management ----------------------

    private static void registerCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        try {
            int id = nextCustomerId++;
            Customer customer = new Customer(id, name, phone);
            customers.add(customer);
            customerIds.add(id);
            System.out.println("Customer registered successfully! Customer ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register customer: " + e.getMessage());
        }
    }

    private static void viewCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
            return;
        }
        System.out.println("---- All Customers ----");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private static Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }

    // ---------------------- Driver Management ----------------------

    private static void registerDriver() {
        System.out.print("Enter driver name: ");
        String name = scanner.nextLine();
        System.out.print("Enter vehicle number: ");
        String vehicle = scanner.nextLine();

        try {
            int id = nextDriverId++;
            Driver driver = new Driver(id, name, vehicle);
            driverService.registerDriver(driver);
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register driver: " + e.getMessage());
        }
    }

    // ---------------------- Booking Flow ----------------------

    private static void bookRide(String rideType) {
        if (customers.isEmpty()) {
            System.out.println("Please register a customer first.");
            return;
        }

        viewCustomers();
        int customerId = readInt("Enter your Customer ID: ");
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Invalid Customer ID.");
            return;
        }

        System.out.print("Enter pickup location: ");
        String pickup = scanner.nextLine();
        System.out.print("Enter drop location: ");
        String drop = scanner.nextLine();
        double distance = readDouble("Enter distance in KM: ");

        try {
            RideBooking ride;
            if (rideType.equals("STANDARD")) {
                ride = rideService.createStandardRide(pickup, drop, distance);
            } else if (rideType.equals("PREMIUM")) {
                ride = rideService.createPremiumRide(pickup, drop, distance);
            } else {
                int passengers = readInt("Enter number of passengers sharing this ride: ");
                ride = rideService.createCarpoolRide(pickup, drop, distance, passengers);
            }

            Driver driver = driverService.findAvailableDriver();
            driverService.assignDriver(driver);

            Booking booking = bookingService.createBooking(customer, driver, ride);

            System.out.println("\nBooking Confirmed!");
            booking.displayBookingDetails();

        } catch (InvalidRideException e) {
            System.out.println("Ride booking failed: " + e.getMessage());
        } catch (DriverNotAvailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    private static void viewBooking() {
        int bookingId = readInt("Enter Booking ID: ");
        try {
            Booking booking = bookingService.findBooking(bookingId);
            booking.displayBookingDetails();
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void completeBooking() {
        int bookingId = readInt("Enter Booking ID to complete: ");
        try {
            bookingService.completeBooking(bookingId);
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void cancelBooking() {
        int bookingId = readInt("Enter Booking ID to cancel: ");
        try {
            bookingService.cancelBooking(bookingId);
        } catch (BookingNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    // ---------------------- Input Helpers ----------------------

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
