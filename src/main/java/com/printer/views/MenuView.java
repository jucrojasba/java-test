package com.printer.views;

import com.printer.controllers.ClientController;
import com.printer.controllers.MachineController;
import com.printer.controllers.RentalController;
import com.printer.models.Client;
import com.printer.models.Machine;
import com.printer.models.Rental;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuView {

    private static final int PAGE_SIZE = 5;

    public void showMenu() {
        ClientController clientController = new ClientController();
        MachineController machineController = new MachineController();
        RentalController rentalController = new RentalController();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Register a Client");
            System.out.println("2. Register a Machine");
            System.out.println("3. Register a Rental");
            System.out.println("4. View Clients (Paginated)");
            System.out.println("5. View Rentals (Paginated)");
            System.out.println("6. View Machines (Paginated)");
            System.out.println("7. Deactivate a Rental");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerClient(clientController, scanner);
                    break;
                case 2:
                    registerMachine(machineController, scanner);
                    break;
                case 3:
                    registerRental(rentalController, scanner);
                    break;
                case 4:
                    handlePaginatedViewClients(clientController, scanner);
                    break;
                case 5:
                    handlePaginatedViewRentals(rentalController, scanner);
                    break;
                case 6:
                    handlePaginatedViewMachines(machineController, scanner);
                    break;
                case 7:
                    deactivateRental(rentalController, scanner);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void registerClient(ClientController clientController, Scanner scanner) {
        System.out.print("Enter client name: ");
        String name = scanner.next();
        System.out.print("Enter client email: ");
        String email = scanner.next();
        System.out.print("Enter client phone: ");
        String phone = scanner.next();
        System.out.print("Enter client address: ");
        String address = scanner.next();

        clientController.addClient(name, email, phone, address);
        System.out.println("Client registered successfully.");
    }

    private static void registerMachine(MachineController machineController, Scanner scanner) {
        System.out.print("Enter machine model: ");
        String model = scanner.next();
        System.out.print("Enter machine serial number: ");
        String serialNumber = scanner.next();
        System.out.print("Enter machine status: ");
        String status = scanner.next();

        machineController.addMachine(model, serialNumber, status);
        System.out.println("Machine registered successfully.");
    }

    private static void registerRental(RentalController rentalController, Scanner scanner) {
        System.out.print("Enter client ID: ");
        int clientId = scanner.nextInt();
        System.out.print("Enter machine ID: ");
        int machineId = scanner.nextInt();
        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDateInput = scanner.next();
        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDateInput = scanner.next();

        Date startDate = Date.valueOf(startDateInput);
        Date endDate = Date.valueOf(endDateInput);

        // Verificar la disponibilidad de la máquina antes de registrar la renta
        if (rentalController.isMachineAvailable(machineId, startDate, endDate)) {
            rentalController.addRental(clientId, machineId, startDate, endDate);
            System.out.println("Rental registered successfully.");
        } else {
            System.out.println("The machine is already rented during the specified period.");
        }
    }

    private static void handlePaginatedViewClients(ClientController clientController, Scanner scanner) {
        int page = 1;
        List<Client> clients;

        do {
            clients = clientController.getAllClients();
            displayClients(clients, page);

            System.out.print("Enter 'n' for next page, 'p' for previous page, or 'q' to quit: ");
            String input = scanner.next();

            if (input.equals("n")) {
                page++;
            } else if (input.equals("p") && page > 1) {
                page--;
            } else if (input.equals("q")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        } while (!clients.isEmpty());
    }

    private static void handlePaginatedViewRentals(RentalController rentalController, Scanner scanner) {
        int page = 1;
        boolean includeInactive = false; 
        final int pageSize = 5; 
        List<Rental> rentals;
    
        do {
            List<Rental> allRentals = rentalController.getRentals(includeInactive);
            
            int start = (page - 1) * pageSize;
            int end = Math.min(start + pageSize, allRentals.size());
            
            if (start < allRentals.size()) {
                rentals = allRentals.subList(start, end);
            } else {
                rentals = new ArrayList<>();
            }
    
            System.out.println("\nRentals - Page " + page + (includeInactive ? " (Including Inactive)" : ""));
            if (rentals.isEmpty()) {
                System.out.println("No rentals found for the current page.");
            } else {
                displayRentals(rentals, page);  
            }
    
            System.out.print("Enter 'n' for next page, 'p' for previous page, 'i' to toggle include inactive, or 'q' to quit: ");
            String input = scanner.next();
    
            if (input.equals("n")) {
                if (end < allRentals.size()) {
                    page++;
                } else {
                    System.out.println("No more pages available.");
                }
            } else if (input.equals("p")) {
                if (page > 1) {
                    page--;
                } else {
                    System.out.println("You are already on the first page.");
                }
            } else if (input.equals("i")) {
                includeInactive = !includeInactive;
                page = 1; 
                System.out.println(includeInactive ? "Including inactive rentals." : "Showing only active rentals.");
            } else if (input.equals("q")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        } while (true);
    } 

    private static void handlePaginatedViewMachines(MachineController machineController, Scanner scanner) {
        int page = 1;
        List<Machine> machines;

        do {
            machines = machineController.getAllMachines(page, PAGE_SIZE);
            displayMachines(machines, page);

            System.out.print("Enter 'n' for next page, 'p' for previous page, or 'q' to quit: ");
            String input = scanner.next();

            if (input.equals("n")) {
                page++;
            } else if (input.equals("p") && page > 1) {
                page--;
            } else if (input.equals("q")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        } while (!machines.isEmpty());
    }

    private static void deactivateRental(RentalController rentalController, Scanner scanner) {
        System.out.print("Enter rental ID: ");
        int rentalId = scanner.nextInt();
        System.out.print("Enter machine ID: ");
        int machineId = scanner.nextInt();

        rentalController.deactivateRental(rentalId, machineId);
        System.out.println("Rental deactivated successfully.");
    }

    private static void displayClients(List<Client> clients, int page) {
        System.out.println("Clients - Page " + page);
        clients.forEach(client -> System.out.println(client.toString()));
    }

    private static void displayRentals(List<Rental> rentals, int page) {
        System.out.println("Rentals - Page " + page);
        rentals.forEach(rental -> System.out.println(rental.toString()));
    }

    private static void displayMachines(List<Machine> machines, int page) {
        System.out.println("Machines - Page " + page);
        machines.forEach(machine -> System.out.println(machine.toString()));
    }
}
