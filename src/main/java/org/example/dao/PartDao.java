package org.example.dao;
import org.example.entity.Part;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartDao {
    private Connection connection;

    public PartDao(Connection connection) {
        this.connection = connection;
    }

    public void addPart(Part part) {
        String query = "INSERT INTO Part (part_id, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, part.getPartId());
            statement.setString(2, part.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Part> getAllParts() {
        List<Part> parts = new ArrayList<>();
        String query = "SELECT * FROM Part";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int partId = resultSet.getInt("part_id");
                String name = resultSet.getString("name");
                Part part = new Part(partId, name);
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parts;
    }

    public Part getPartById(int partId) {
        String query = "SELECT * FROM Part WHERE part_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, partId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    return new Part(partId, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePart(Part part) {
        String query = "UPDATE Part SET name = ? WHERE part_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, part.getName());
            statement.setInt(2, part.getPartId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePart(int partId) {
        String query = "DELETE FROM Part WHERE part_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, partId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}