package com.printer.controllers;

import com.printer.models.Machine;
import com.printer.persistence.DAO.MachineDAO;

import java.util.List;

public class MachineController {
    private MachineDAO machineDAO;

    public MachineController() {
        machineDAO = new MachineDAO();
    }

    // Register a new machine
    public void addMachine(String model, String serialNumber, String status) {
        Machine machine = new Machine(model, serialNumber, status);
        this.machineDAO.addMachine(machine);
    }

    // Get all machines with pagination
    public List<Machine> getAllMachines(int page, int pageSize) {
        return this.machineDAO.getMachines(page, pageSize);
    }
}
