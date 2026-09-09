# Ride-Sharing / Ride Booking System (Core Java POC)

A **Core Java + OOP + Collections console-based application** that simulates a
simple ride-sharing/ride-booking platform. Built as a beginner-friendly proof
of concept to demonstrate OOP fundamentals and the Java Collections Framework
for interview purposes.

> **No frameworks. No database. No external dependencies.**
> Runs with nothing but the JDK — `javac` and `java`.

---

## 1. Project Overview

The application lets a customer register, browse available drivers, book a
ride (Standard, Premium, or Carpool), get a fare estimate, have a driver
auto-assigned, and manage the resulting booking (view / complete / cancel).
Everything runs through a simple text menu in the console.

## 2. Problem Statement

Ride-hailing platforms need to: manage customers and drivers, offer multiple
ride types with different pricing, assign an available driver to a request,
calculate fares, and track the lifecycle of a booking (confirmed → completed
or cancelled). This POC recreates that core workflow using only Core Java —
no web layer, no persistence layer — so the object-oriented design and use of
Collections are front and center.

## 3. Features

- Register customers and drivers
- View all customers / all drivers / only available drivers
- Book a Standard, Premium, or Carpool ride
- Automatic fare calculation per ride type
- Automatic assignment of an available driver
- View a specific booking by ID
- View all bookings (status summary) and full booking history
- Complete or cancel a booking (releases the driver back to "available")
- Promo code / discount support on fares (method overloading)

## 4. Technologies Used

- Java (Core Java only — JDK 8+)
- Object-Oriented Programming (Encapsulation, Abstraction, Inheritance, Polymorphism)
- Java Collections Framework (`ArrayList`, `HashMap`, `HashSet`, `Iterator`)
- Custom Exception Handling (`try` / `catch` / `throw` / `throws`)
- `java.util.Scanner` for console I/O

No Spring, no Hibernate/JPA/JDBC, no Maven/Gradle, no database, no external
libraries of any kind.

## 5. Project Structure

```text
Ride-Sharing-POC/
│
├── src/
│   └── com/
│       └── ridesharing/
│           │
│           ├── RideSharingApplication.java   (main class, console menu)
│           │
│           ├── model/
│           │   ├── RideBooking.java     (abstract base class)
│           │   ├── StandardRide.java
│           │   ├── PremiumRide.java
│           │   ├── CarpoolRide.java
│           │   ├── Customer.java
│           │   ├── Driver.java
│           │   ├── Booking.java
│           │   └── BookingStatus.java   (enum)
│           │
│           ├── service/
│           │   ├── RideService.java
│           │   ├── DriverService.java
│           │   └── BookingService.java
│           │
│           └── exception/
│               ├── InvalidRideException.java
│               ├── DriverNotAvailableException.java
│               └── BookingNotFoundException.java
│
├── README.md
└── .gitignore
```

Customer registration/storage is handled directly in `RideSharingApplication`
(using an `ArrayList<Customer>` + `HashSet<Integer>`) since it is simple
enough not to need its own service class, while ride, driver, and booking
logic each get a dedicated service class to keep responsibilities separated.

## 6. OOP Concept Mapping

```text
+---------------------------------------------------------------+
|                    RIDE-SHARING POC                           |
+---------------------------------------------------------------+
| 1. ENCAPSULATION                                               |
|    Private fields + getters/setters + validation               |
|    (Customer, Driver, RideBooking, Booking)                    |
|                                                                 |
| 2. ABSTRACTION                                                 |
|    Abstract RideBooking class hides fare-calculation details.  |
|    Callers just call ride.calculateFare() / ride.getFinalFare()|
|                                                                 |
| 3. INHERITANCE                                                 |
|    StandardRide, PremiumRide, CarpoolRide extend RideBooking   |
|    (IS-A relationship)                                         |
|                                                                 |
| 4. POLYMORPHISM                                                |
|    A RideBooking reference invokes different fare/ETA logic    |
|    depending on the actual runtime (child) object.              |
|    Method overloading: applyPromoCode(double) / (String)        |
+---------------------------------------------------------------+
```

| Pillar | Where it lives | What it buys you |
|---|---|---|
| Encapsulation | `Customer`, `Driver`, `RideBooking`, `Booking` — all fields `private`, validated setters | Data protection — invalid distances, empty names, or negative fees are rejected at the source |
| Abstraction | `RideBooking` is `abstract`; declares `calculateFare()` / `estimatedArrivalTime()` | Complexity hiding — calling code never needs to know *how* a fare is computed, only that it can call `calculateFare()` |
| Inheritance | `StandardRide`, `PremiumRide`, `CarpoolRide extends RideBooking` | Code reuse — pickup/drop/distance/fee fields and `displayTripDetails()` are written once in the parent |
| Polymorphism | `RideBooking ride = new PremiumRide(...);` then `ride.calculateFare();` | One reference type, multiple behaviors — the correct override runs at runtime based on the actual object |

### Encapsulation in detail

All model classes keep their fields `private` and expose them only through
getters and validating setters. For example, `RideBooking.setTotalTripDistance()`
rejects zero/negative distances, `Customer.setCustomerName()` rejects blank
names, and `Driver`'s `available` flag can only change via
`markAvailable()` / `markUnavailable()` — never set directly. This protects
ride and booking data from ever entering an invalid state.

## 7. Collections Used

```text
ArrayList
    ↓
Stores customers, drivers, rides, and booking history
(ordered, allows duplicates by value, simple sequential access)

HashMap<Integer, Booking>
    ↓
Stores bookingId → Booking for instant O(1) lookup by ID

HashSet<Integer>
    ↓
Maintains unique customer IDs and driver IDs
(rejects duplicates automatically — perfect for ID uniqueness)
```

| Collection | Used for | Why this Collection |
|---|---|---|
| `ArrayList<Customer>` | All registered customers | Simple ordered list; customers are looked up by scanning, which is fine at this scale and keeps the code beginner-friendly |
| `ArrayList<Driver>` | All registered drivers | Same reasoning — ordered registration list, iterated to find available drivers |
| `ArrayList<RideBooking>` | Every ride created (`RideService`) | Holds mixed `StandardRide`/`PremiumRide`/`CarpoolRide` objects via the shared `RideBooking` type — demonstrates polymorphism in a collection |
| `ArrayList<Booking>` | Booking history (`BookingService`) | Preserves the exact order bookings were created, ideal for a history log |
| `HashMap<Integer, Booking>` | Active booking lookup by ID (`BookingService`) | Booking IDs are unique keys; a `HashMap` gives near-instant `get`/`put`/`containsKey`, far better than scanning a list every time a booking is viewed, completed, or cancelled |
| `HashSet<Integer>` | Customer IDs & Driver IDs | A `Set` inherently disallows duplicates, so it's the natural structure to guarantee no two customers/drivers ever share an ID — an `ArrayList` would need manual duplicate checks |

Collections traversal used throughout: `for-each` loops (`for (Driver d : drivers)`),
an explicit `Iterator` in `BookingService.displayBookingHistory()`, and standard
methods like `add()`, `get()`, `contains()`, `isEmpty()`, `size()`, `put()`,
`containsKey()`, and `entrySet()`.

## 8. Exception Handling

| Exception | Thrown when | Handled in |
|---|---|---|
| `InvalidRideException` | Empty pickup/drop location, distance ≤ 0, invalid passenger count | `RideSharingApplication.bookRide()` via `try/catch` |
| `DriverNotAvailableException` | No driver is currently free | `RideSharingApplication.bookRide()` via `try/catch` |
| `BookingNotFoundException` | A booking ID doesn't exist in the `HashMap` | `viewBooking()`, `completeBooking()`, `cancelBooking()` |

All three are custom checked exceptions (`extends Exception`), declared with
`throws` on the methods that can raise them, and caught close to the console
menu so the user always gets a clear message instead of a stack trace.
Simple input validation (e.g., non-numeric menu input) is handled separately
with plain `try/catch` around `Integer.parseInt` / `Double.parseDouble` —
exceptions are not used for ordinary control flow.

## 9. Application Flow

```text
Start Application
       ↓
Register Customer(s)
       ↓
Register Driver(s)
       ↓
Choose an action from the menu (loops until Exit)
```

## 10. Booking Flow

```text
Select Ride Type (Standard / Premium / Carpool)
       ↓
Enter Pickup Location
       ↓
Enter Drop Location
       ↓
Enter Distance
       ↓
Validate Input           --> InvalidRideException if invalid
       ↓
Find Available Driver    --> DriverNotAvailableException if none free
       ↓
Create Ride Object (StandardRide / PremiumRide / CarpoolRide)
       ↓
Calculate Fare (polymorphic calculateFare())
       ↓
Create Booking, store in HashMap<Integer, Booking>
       ↓
Mark Driver Unavailable
       ↓
Display Booking Details
```

## 11. Sample Console Output

```text
================================================
          RIDE-SHARING SYSTEM (Core Java POC)
================================================

------------------------------------------------
1. Register Customer
2. Register Driver
3. View Customers
4. View Drivers
5. Book Ride
6. View Available Drivers
7. View Ride Details
8. Cancel Ride
9. Complete Ride
10. View Customer Ride History
11. View Driver Ride History
12. Calculate Ride Fare
13. Exit
------------------------------------------------
Enter your choice: 7
---- All Customers ----
Customer{id=1, name='Mani', phone='9876543210'}
Enter your Customer ID: 1
Enter pickup location: Kurnool Bus Stand
Enter drop location: Railway Station
Enter distance in KM: 8

Booking Confirmed!
------------------------------------------------
Booking ID     : 1001
Status         : CONFIRMED
Customer       : Mani (9876543210)
Driver         : Ravi | Vehicle: AP21AB1234
Pickup Location : Kurnool Bus Stand
Drop Location    : Railway Station
Distance (KM)    : 8.0
Ride Type        : PremiumRide
Estimated Fare   : Rs. 190.00
Estimated Arrival: 12 mins
Final Fare     : Rs. 190.00
------------------------------------------------
```

## 12. How to Compile

From the project root:

```bash
javac -d bin $(find src -name "*.java")
```

(On Windows, without `find`, use: `javac -d bin src\com\ridesharing\*.java src\com\ridesharing\model\*.java src\com\ridesharing\service\*.java src\com\ridesharing\exception\*.java`)

## 13. How to Run

```bash
java -cp bin com.ridesharing.RideSharingApplication
```

No Maven, no Gradle, no database, no external JARs — just the JDK.


## 15. Future Enhancements

- Persist data to a file or database between runs
- Add ride ratings/reviews
- Support multiple simultaneous bookings per customer with a booking queue
- Add distance/fare estimation based on real coordinates
- Add unit tests (JUnit) for service classes
