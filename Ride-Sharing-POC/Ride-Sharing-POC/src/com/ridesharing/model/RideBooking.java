package com.ridesharing.model;

/**
 * Abstract base class for all ride types.
 *
 * Demonstrates ABSTRACTION: it defines WHAT every ride must be able to do
 * (calculateFare, estimatedArrivalTime) without exposing HOW each ride type
 * calculates its own fare. Callers simply call ride.calculateFare() and do
 * not need to know the internal formula.
 *
 * Demonstrates INHERITANCE: StandardRide, PremiumRide and CarpoolRide all
 * extend this class and reuse its common fields/behavior (IS-A relationship).
 */
public abstract class RideBooking {

    private String pickupLocation;
    private String dropLocation;
    private double totalTripDistance;
    private double startingFee;
    private double discountPercentage; // set via applyPromoCode()

    protected RideBooking(String pickupLocation, String dropLocation,
                           double totalTripDistance, double startingFee) {
        setPickupLocation(pickupLocation);
        setDropLocation(dropLocation);
        setTotalTripDistance(totalTripDistance);
        setStartingFee(startingFee);
        this.discountPercentage = 0.0;
    }

    // ---------- Abstract methods (Abstraction + Polymorphism) ----------

    /**
     * Each ride type implements its own fare formula.
     */
    public abstract double calculateFare();

    /**
     * Each ride type implements its own ETA estimate.
     */
    public abstract String estimatedArrivalTime();

    // ---------- Common behavior shared by all ride types ----------

    public void displayTripDetails() {
        System.out.println("Pickup Location : " + pickupLocation);
        System.out.println("Drop Location    : " + dropLocation);
        System.out.println("Distance (KM)    : " + totalTripDistance);
        System.out.println("Ride Type        : " + getClass().getSimpleName());
        System.out.println("Estimated Fare   : Rs. " + String.format("%.2f", getFinalFare()));
        System.out.println("Estimated Arrival: " + estimatedArrivalTime());
    }

    /**
     * Returns the fare after applying any promo code discount.
     * calculateFare() itself stays untouched/pure per ride type.
     */
    public double getFinalFare() {
        double fare = calculateFare();
        double discount = fare * (discountPercentage / 100.0);
        return fare - discount;
    }

    // ---------- Method Overloading (Polymorphism) ----------

    /**
     * Apply a direct percentage discount, e.g. applyPromoCode(15.0)
     */
    public void applyPromoCode(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100.");
        }
        this.discountPercentage = percentage;
    }

    /**
     * Apply a discount using a known promo code string, e.g. applyPromoCode("SAVE10")
     */
    public void applyPromoCode(String promoCode) {
        if (promoCode == null) {
            this.discountPercentage = 0.0;
            return;
        }
        switch (promoCode.trim().toUpperCase()) {
            case "SAVE10":
                this.discountPercentage = 10.0;
                break;
            case "SAVE20":
                this.discountPercentage = 20.0;
                break;
            default:
                this.discountPercentage = 0.0;
        }
    }

    // ---------- Getters / Setters (Encapsulation) ----------

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        if (pickupLocation == null || pickupLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Pickup location cannot be empty.");
        }
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        if (dropLocation == null || dropLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Drop location cannot be empty.");
        }
        this.dropLocation = dropLocation;
    }

    public double getTotalTripDistance() {
        return totalTripDistance;
    }

    public void setTotalTripDistance(double totalTripDistance) {
        if (totalTripDistance <= 0) {
            throw new IllegalArgumentException("Distance cannot be negative or zero.");
        }
        this.totalTripDistance = totalTripDistance;
    }

    public double getStartingFee() {
        return startingFee;
    }

    public void setStartingFee(double startingFee) {
        if (startingFee < 0) {
            throw new IllegalArgumentException("Starting fee cannot be negative.");
        }
        this.startingFee = startingFee;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
