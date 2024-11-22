# Machine Rental Management System 📋

This project is a **Machine Rental Management System** designed for a company that rents out multifunctional machines such as copiers, printers, and scanners. The system is intended to be used by an administrator to manage the rental process, including registering clients, machines, and rentals, as well as handling soft deletions and managing machine availability.

## 🛠️ Technologies Used

- **Java SE v >= 11**
- **JDBC** (Java Database Connectivity)
- **Maven** (for dependency management)
- **PostgreSQL** (for the database)
- **Apache POI** (for the Excel Importer)

## 📥 Installation

### 1. Clone the repository
To get started, clone the project to your local machine:

```bash
git clone https://github.com/jucrojasba/java-test.git
```

### 2. Build the project
Navigate to the root directory of the project and run the following Maven commands:

```bash
mvn clean install
```

### 3. Run the project
To start the application, run the following command:

```bash
mvn exec:java
```

Once the application is running, an interactive menu will appear in the console, allowing you to use the system.

##Relational Model

![image](https://github.com/user-attachments/assets/53d9aded-f207-43a4-9257-596193104238)


## 📂 Excel Importer

If you want to import machines using an Excel file, the file must have the following structure:

| Model       | Serial Number | Status    |
|-------------|---------------|-----------|
| Copier 1    | SN12345       | Available |
| Printer 2   | SN12346       | Available |
| Scanner 3   | SN12347       | Available |

The Excel file should be saved in `.xlsx` format.

### Example Excel File Path

If the Excel file is located in your `Downloads` folder, the file path might look something like this:

```bash
C:/Users/YourUsername/Downloads/machines.xlsx
```

## 🎮 Main Features

The system provides the following features for managing clients, machines, and rentals:

### 1. Client Management

- Register new clients with the following data:
  - Full name
  - Email
  - Phone number
  - Address
- View the list of registered clients.

### 2. Machine Management

- Register multifunctional machines with the following data:
  - Model
  - Serial number (unique)
  - Status (Available or Rented)
- View the list of registered machines (with pagination, 5 machines per page).
- Import machines from an Excel file.

### 3. Rental Management

- Register a new rental by linking a client to an available machine.
  - Include rental start and end dates.
  - Change the machine's status to "Rented" once the rental is registered.
- Perform a soft delete of a rental (mark it as "Deactivated").
  - Change the machine's status to "Available" when the rental is deactivated.
- View all registered rentals (only active rentals by default, but can toggle to include deactivated rentals).

## 🛠️ Console Menu

Once the project is running, you will interact with the system via a console menu with the following options:

1. Register a Client
2. Register a Machine
3. Register a Rental
4. View Clients (Paginated)
5. View Rentals (Paginated)
6. View Machines (Paginated)
7. Deactivate a Rental
8. Importer Machines from excel
0. Exit

Use the interactive menu to perform the desired actions, such as registering clients, machines, rentals, and viewing information in a paginated format.

