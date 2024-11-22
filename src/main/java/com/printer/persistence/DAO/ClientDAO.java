package com.printer.persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.main.java.com.printer.models.Client;
import src.main.java.com.printer.persistence.db.DbContext;

public class ClientDAO {
    private String table = "clients";
    private static final Logger logger = Logger.getLogger(ClientDAO.class.getName());

    // Register a new client
    public void addClient(Client client) {
        String SQL = String.format("INSERT INTO %s (name, email, phone, address) VALUES (?, ?, ?, ?);", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getAddress());
            statement.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error adding client to database", e);
        }
    }

    // Get all clients
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String SQL = String.format("SELECT * FROM %s;", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client(
                    resultSet.getInt("id"), 
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
                );
                clients.add(client);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching clients from database", e);
        }
        return clients;
    }
}
