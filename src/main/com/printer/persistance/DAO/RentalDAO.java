package src.main.com.printer.persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.main.com.printer.entities.Rental;
import src.main.com.printer.persistence.db.DbContext;

public class RentalDAO {
    private String table = "rentals";

    public void addRental(Rental rental) {
        String SQL = String.format("INSERT INTO %s (client_id, machine_id, start_date, end_date, status) VALUES (?, ?, ?, ?, ?);", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setInt(1, rental.getClientId());
            statement.setInt(2, rental.getMachineId());
            statement.setDate(3, rental.getStartDate());
            statement.setDate(4, rental.getEndDate());
            statement.setString(5, "Active");
            statement.executeUpdate();

            String updateMachineSQL = "UPDATE machines SET status = 'Alquilada' WHERE id = ?;";
            try (PreparedStatement updateStatement = con.prepareStatement(updateMachineSQL)) {
                updateStatement.setInt(1, rental.getMachineId());
                updateStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deactivateRental(int rentalId, int machineId) {
        String SQL = String.format("UPDATE %s SET status = 'Inactive' WHERE id = ?;", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setInt(1, rentalId);
            statement.executeUpdate();

            String updateMachineSQL = "UPDATE machines SET status = 'Disponible' WHERE id = ?;";
            try (PreparedStatement updateStatement = con.prepareStatement(updateMachineSQL)) {
                updateStatement.setInt(1, machineId);
                updateStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Rental> getRentals(boolean includeInactive) {
        List<Rental> rentals = new ArrayList<>();
        String SQL = includeInactive ? String.format("SELECT * FROM %s;", table)
                                     : String.format("SELECT * FROM %s WHERE status = 'Active';", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Rental rental = new Rental(
                    resultSet.getInt("id"),
                    resultSet.getInt("client_id"),
                    resultSet.getInt("machine_id"),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"),
                    resultSet.getString("status")
                );
                rentals.add(rental);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentals;
    }
}
