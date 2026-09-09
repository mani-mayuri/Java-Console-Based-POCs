package com.ridesharing.model;

/**
 * Represents a customer who books rides.
 * Demonstrates ENCAPSULATION: fields are private and can only be
 * accessed/modified through getters/setters, which also validate input.
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String phoneNumber;

    public Customer(int customerId, String customerName, String phoneNumber) {
        this.customerId = customerId;
        setCustomerName(customerName);
        setPhoneNumber(phoneNumber);
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{id=" + customerId + ", name='" + customerName +
                "', phone='" + phoneNumber + "'}";
    }
}
