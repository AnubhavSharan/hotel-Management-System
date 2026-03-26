# Hotel Management System

A console-based hotel reservation application built in Java. This system allows users to find and reserve rooms, view their reservations, create accounts, and provides administrative functions for managing the hotel.

## Features

- **Find and Reserve Rooms**: Search for available rooms by date and reserve them.
- **View Reservations**: See your existing reservations.
- **Create Account**: Register as a new customer.
- **Admin Panel**: Administrative functions for managing rooms, customers, and reservations.

## Project Structure

- `MainMenu.java`: Main entry point with the user interface menu.
- `api/`: Contains API classes for hotel and admin resources.
  - `hotelResource.java`: Handles hotel-related operations.
  - `AdminResource.java`: Handles administrative operations.
- `model1/`: Data models.
  - `Customer.java`: Customer information.
  - `Room.java`: Room details.
  - `Reservation.java`: Reservation data.
  - `IRoom.java`: Room interface.
  - `RoomType.java`: Enumeration for room types.
  - Other supporting classes.
- `service/`: Service classes for business logic.
  - `CustomerService.java`: Customer management.
  - `ReservationService.java`: Reservation management.

## Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system.

## Installation

1. Clone or download the project files to your local machine.
2. Ensure JDK is installed and `java` and `javac` are in your PATH.

## Compilation

Compile all Java files from the root directory:

```
javac -d . *.java api/*.java model1/*.java service/*.java
```

This will compile all source files and place the class files in the appropriate package structure.

## Running the Application

Run the main class:

```
java MainMenu
```

## Usage

1. Upon running, you'll see the main menu with options:
   - 1. Find and reserve a room
   - 2. See my reservations
   - 3. Create an account
   - 4. Admin
   - 5. Exit

2. Follow the on-screen prompts to navigate through the application.

3. For admin functions, select option 4 and follow the admin menu.

## Notes

- This is a console-based application with text input/output.
- Ensure valid email format when creating accounts (e.g., user@example.com).
- Dates should be entered in the format expected by the application.

## Troubleshooting

- If compilation fails, ensure all Java files are present and JDK is properly installed.
- If runtime errors occur, check for valid input formats and ensure no null pointer exceptions from missing data.

## Contributing

Feel free to contribute by improving the code, adding features, or fixing bugs.