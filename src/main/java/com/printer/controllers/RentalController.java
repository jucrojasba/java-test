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

    // Register a new rental
    public void addRental(int clientId, int machineId, Date startDate, Date endDate) {
        Rental rental = new Rental(clientId, machineId, startDate, endDate, "Active");
        this.rentalDAO.addRental(rental);
    }

    // Deactivate an existing rental
    public void deactivateRental(int rentalId, int machineId) {
        this.rentalDAO.deactivateRental(rentalId, machineId);
    }

    // Get all rentals (can include inactive ones)
    public List<Rental> getRentals(boolean includeInactive) {
        return this.rentalDAO.getRentals(includeInactive);
    }
}
