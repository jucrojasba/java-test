package com.printer.controllers;

import com.printer.entities.Client;
import com.printer.persistence.DAO.ClientDAO;

import java.util.List;

public class ClientController {
    private ClientDAO clientDAO;

    public ClientController() {
        clientDAO = new ClientDAO();
    }

    // Register a new client
    public void addClient(String name, String email, String phone, String address) {
        Client client = new Client(name, email, phone, address);
        this.clientDAO.addClient(client);
    }

    // Get all clients
    public List<Client> getAllClients() {
        return this.clientDAO.getAllClients();
    }
}
