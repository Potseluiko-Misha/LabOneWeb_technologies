package org.example.dao;
import org.example.entity.Computer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputerDao {
    private Connection connection;

    public ComputerDao(Connection connection) {
        this.connection = connection;
    }

    public void addComputer(Computer computer) {
        String query = "INSERT INTO Computer (computer_id, brand) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, computer.getComputerId());
            statement.setString(2, computer.getBrand());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Computer> getAllComputers() {
        List<Computer> computers = new ArrayList<>();
        String query = "SELECT * FROM Computer";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int computerId = resultSet.getInt("computer_id");
                String brand = resultSet.getString("brand");
                Computer computer = new Computer(computerId, brand);
                computers.add(computer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return computers;
    }

    public Computer getComputerById(int computerId) {
        String query = "SELECT * FROM Computer WHERE computer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, computerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String brand = resultSet.getString("brand");
                    return new Computer(computerId, brand);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateComputer(Computer computer) {
        String query = "UPDATE Computer SET brand = ? WHERE computer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, computer.getBrand());
            statement.setInt(2, computer.getComputerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteComputer(int computerId) {
        String query = "DELETE FROM Computer WHERE computer_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, computerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
