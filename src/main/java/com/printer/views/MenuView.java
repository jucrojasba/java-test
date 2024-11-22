package com.printer.views;

import com.printer.controllers.ClientController;
import com.printer.controllers.MachineController;
import com.printer.controllers.RentalController;
import com.printer.models.Client;
import com.printer.models.Machine;
import com.printer.models.Rental;

import java.sql.Date;
import java.util.Scanner;

public class MenuView {
    private Scanner scanner;
    private ClientController clientController;
    private MachineController machineController;
    private RentalController rentalController;

    public MenuView() {
        this.scanner = new Scanner(System.in);
        this.clientController = new ClientController();
        this.machineController = new MachineController();
        this.rentalController = new RentalController();
    }

    public void displayMenu() {
        System.out.println("----- MENU -----");
        System.out.println("1. Add Client");
        System.out.println("2. Add Machine");
        System.out.println("3. Add Rental");
        System.out.println("4. View Rentals");
        System.out.println("5. Exit");
    }

    public void showMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    addMachine();
                    break;
                case 3:
                    addRental();
                    break;
                case 4:
                    viewRentals();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Add a new client
    private void addClient() {
        System.out.println("Enter client name: ");
        String name = scanner.nextLine();
        System.out.println("Enter client email: ");
        String email = scanner.nextLine();
        System.out.println("Enter client phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter client address: ");
        String address = scanner.nextLine();
        Client client = new Client(name, email, phone, address);
        clientController.addClient(client);
        System.out.println("Client added successfully!");
    }

    // Add a new machine
    private void addMachine() {
        System.out.println("Enter machine model: ");
        String model = scanner.nextLine();
        System.out.println("Enter machine serial number: ");
        String serialNumber = scanner.nextLine();
        System.out.println("Enter machine status: ");
        String status = scanner.nextLine();
        Machine machine = new Machine(model, serialNumber, status);
        machineController.addMachine(machine);
        System.out.println("Machine added successfully!");
    }

    // Add a new rental
    private void addRental() {
        System.out.println("Enter client ID: ");
        int clientId = scanner.nextInt();
        System.out.println("Enter machine ID: ");
        int machineId = scanner.nextInt();
        System.out.println("Enter start date (YYYY-MM-DD): ");
        String startDateStr = scanner.next();
        Date startDate = Date.valueOf(startDateStr);
        System.out.println("Enter end date (YYYY-MM-DD): ");
        String endDateStr = scanner.next();
        Date endDate = Date.valueOf(endDateStr);
        Rental rental = new Rental(clientId, machineId, startDate, endDate, "Active");
        rentalController.addRental(rental);
        System.out.println("Rental added successfully!");
    }

    // View all rentals
    private void viewRentals() {
        System.out.println("View rentals (1: Active, 2: All): ");
        int viewOption = scanner.nextInt();
        boolean includeInactive = (viewOption == 2);
        rentalController.getRentals(includeInactive).forEach(System.out::println);
    }
}
