package com.printer.controllers;

import com.printer.models.Rental;
import com.printer.persistence.DAO.RentalDAO;

import java.sql.Date;
import java.util.List;

public class RentalController {
    private RentalDAO rentalDAO;

    public RentalController() {
        rentalDAO = new RentalDAO();
    }

    // Register a new rental with availability check
    public void addRental(int clientId, int machineId, Date startDate, Date endDate) {
        if (isMachineAvailable(machineId, startDate, endDate)) {
            Rental rental = new Rental(clientId, machineId, startDate, endDate, "Active");
            this.rentalDAO.addRental(rental);
            System.out.println("Rental registered successfully.");
        } else {
            System.out.println("The machine is already rented during the specified period.");
        }
    }

    // Deactivate an existing rental
    public void deactivateRental(int rentalId, int machineId) {
        this.rentalDAO.deactivateRental(rentalId, machineId);
    }

    // Get all rentals (can include inactive ones)
    public List<Rental> getRentals(boolean includeInactive) {
        return this.rentalDAO.getRentals(includeInactive);
    }

    // Check if the machine is available for the specified dates
    public boolean isMachineAvailable(int machineId, Date startDate, Date endDate) {
        List<Rental> rentals = rentalDAO.getRentals(true); 
        for (Rental rental : rentals) {
            if (rental.getMachineId() == machineId && rental.getStatus().equals("Active")) {
                if ((startDate.before(rental.getEndDate()) && endDate.after(rental.getStartDate()))) {
                    return false;  
                }
            }
        }
        return true;  
    }
}
