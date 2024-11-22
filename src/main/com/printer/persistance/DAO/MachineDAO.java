package src.main.com.printer.persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.main.com.printer.entities.Machine;
import src.main.com.printer.persistence.db.DbContext;

public class MachineDAO {
    private String table = "machines";

    public void addMachine(Machine machine) {
        String SQL = String.format("INSERT INTO %s (model, serial_number, status) VALUES (?, ?, ?);", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setString(1, machine.getModel());
            statement.setString(2, machine.getSerialNumber());
            statement.setString(3, machine.getStatus());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Machine> getMachines(int page, int pageSize) {
        List<Machine> machines = new ArrayList<>();
        int offset = (page - 1) * pageSize;
        String SQL = String.format("SELECT * FROM %s LIMIT ? OFFSET ?;", table);
        try (Connection con = DbContext.getConnection();
             PreparedStatement statement = con.prepareStatement(SQL)) {
            statement.setInt(1, pageSize);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Machine machine = new Machine(
                    resultSet.getInt("id"),
                    resultSet.getString("model"),
                    resultSet.getString("serial_number"),
                    resultSet.getString("status")
                );
                machines.add(machine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return machines;
    }
}
