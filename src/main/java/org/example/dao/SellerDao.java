package org.example.dao;
import org.example.entity.Seller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDao {
    private Connection connection;

    public SellerDao(Connection connection) {
        this.connection = connection;
    }

    public void addSeller(Seller seller) {
        String query = "INSERT INTO Seller (seller_id, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, seller.getSellerId());
            statement.setString(2, seller.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Seller> getAllSellers() {
        List<Seller> sellers = new ArrayList<>();
        String query = "SELECT * FROM Seller";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int sellerId = resultSet.getInt("seller_id");
                String name = resultSet.getString("name");
                Seller seller = new Seller(sellerId, name);
                sellers.add(seller);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sellers;
    }

    public Seller getSellerById(int sellerId) {
        String query = "SELECT * FROM Seller WHERE seller_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sellerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    return new Seller(sellerId, name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSeller(Seller seller) {
        String query = "UPDATE Seller SET name = ? WHERE seller_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, seller.getName());
            statement.setInt(2, seller.getSellerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeller(int sellerId) {
        String query = "DELETE FROM Seller WHERE seller_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sellerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}