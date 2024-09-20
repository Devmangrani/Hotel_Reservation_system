# Hotel Reservation System 🏨

Welcome to the Hotel Reservation System, a Java-based application for managing hotel reservations efficiently. Whether you're running a small inn or a boutique hotel, this system simplifies the reservation process, enhances guest management, and keeps your business organized.

## Features 🌟

- **Reserve a Room:** Easily make new reservations by providing guest details, room numbers, and contact information.

- **View Reservations:** Get an overview of all current reservations, including guest names, room numbers, contact details, and reservation dates.

- **Edit Reservation Details:** Update guest names, room numbers, and contact information for existing reservations.

- **Delete Reservations:** Remove reservations that are no longer needed.

## Getting Started 🚀

### Prerequisites

- Java Development Kit (JDK)
- MySQL Database
- MySQL Connector/J (Java)


2. **JDBC Driver**: The MySQL JDBC driver should be added to your project. You can download it from the [official website](https://dev.mysql.com/downloads/connector/j/).

3. **Java Environment**: Ensure you have Java installed (preferably JDK 8 or above).

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/hotel-management-system.git
   cd hotel-management-system
   ```

2. **Configure Database Credentials**:
   In the `Main` class, update the following variables with your MySQL database credentials:
   ```java
   private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
   private static final String username = "your_mysql_username";
   private static final String password = "your_mysql_password";
   ```

3. **Compile and Run the Project**:
   - Use your preferred Java IDE (like IntelliJ IDEA, Eclipse) or the command line to compile and run the program.

   Example using the command line:
   ```bash
   javac Main.java
   java Main
   ```

4. **Interact with the System**:
   After running the application, you'll see the following menu:
   
   ```
   Welcome To Our HOTEL MANAGEMENT SYSTEM
   1. Reserve a room
   2. View Reservation
   3. Get Room Number
   4. Update Reservations
   5. Delete Reservations
   6. Exit
   ```

   Choose an option by entering the corresponding number.

## Code Structure

### `Main` Class

- **Database Credentials**: The `url`, `username`, and `password` for connecting to the MySQL database are defined at the start.
  
- **Main Method**:
  - Loads the MySQL JDBC driver and establishes a connection to the database.
  - Displays a menu and processes user input in an infinite loop.
  - Calls different methods (`resRoom`, `viewRes`, `getRoomno`, `upRes`, `delRes`, `exit`) based on user choices.

### Methods

- **`resRoom`**: 
  - Reserves a room by inserting a new record into the `reservations` table.
  - User inputs guest name, room number, and contact number.

- **`viewRes`**: 
  - Retrieves and displays all reservations from the database in a tabular format.

- **`getRoomno`**: 
  - Retrieves the room number for a specific reservation based on the reservation ID and guest name.

- **`upRes`**: 
  - Updates an existing reservation’s guest name, room number, and contact number.

- **`delRes`**: 
  - Deletes a reservation from the database based on the reservation ID.

- **`exit`**: 
  - Exits the system with a loading animation and a thank-you message.

### SQL Injection Risk
Currently, the code uses plain `Statement` objects and string concatenation for SQL queries, which is vulnerable to SQL injection attacks. For improved security, it is recommended to use `PreparedStatement` with parameterized queries.

### Exception Handling
Exception handling is done using `try-catch` blocks to catch and handle `SQLException` and other exceptions. However, it is recommended to improve logging and avoid printing stack traces directly in a production environment.

## Future Improvements

- **Input Validation**: Implement input validation for user entries to ensure data integrity.
- **Prepared Statements**: Replace SQL queries with parameterized queries (`PreparedStatement`) to prevent SQL injection.
- **Enhanced Exception Handling**: Add better logging and error handling mechanisms.
- **Environment Configuration**: Use environment variables or configuration files for sensitive data like database credentials.

## Example Usage

Here’s an example flow of the application:

1. **Reserve a Room**:
   ```
   Enter Guest name: John
   Enter Room Number: 101
   Enter Contact Number: 1234567890
   Reservation Successful
   ```

2. **View Reservations**:
   ```
   +----------------+-----------------+---------------+----------------------+-------------------------+
   | Reservation ID | Guest           | Room Number   | Contact Number       | Reservation Date        |
   +----------------+-----------------+---------------+----------------------+-------------------------+
   | 1              | John             | 101           | 1234567890           | 2024-09-21 12:00:00     |
   +----------------+-----------------+---------------+----------------------+-------------------------+
   ```

3. **Get Room Number**:
   ```
   Enter reservation ID: 1
   Enter guest name: John
   Room number for Reservation ID 1 and Guest John is: 101
   ```

4. **Update Reservation**:
   ```
   Enter Reservation ID to update: 1
   Enter new Guest name: John Smith
   Enter new Room Number: 102
   Enter new Contact no: 9876543210
   Reservation Updated Successfully!
   ```

5. **Delete Reservation**:
   ```
   Enter reservation ID to delete: 1
   Reservation deleted successfully!
   ```
