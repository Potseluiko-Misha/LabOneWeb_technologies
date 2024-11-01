package org.example.dao;
import org.example.entity.Sale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleDao {
    private Connection connection;

    public SaleDao(Connection connection) {
        this.connection = connection;
    }

    public void addSale(Sale sale) {
        String query = "INSERT INTO Sale (sale_id, customer_id, part_id, computer_id, seller_id, sale_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getSaleId());
            statement.setInt(2, sale.getCustomerId());
            statement.setInt(3, sale.getPartId());
            statement.setInt(4, sale.getComputerId());
            statement.setInt(5, sale.getSellerId());
            statement.setDate(6, sale.getSaleDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM Sale";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int saleId = resultSet.getInt("sale_id");
                int customerId = resultSet.getInt("customer_id");
                int partId = resultSet.getInt("part_id");
                int computerId = resultSet.getInt("computer_id");
                int sellerId = resultSet.getInt("seller_id");
                Date saleDate = resultSet.getDate("sale_date");
                Sale sale = new Sale(saleId, customerId, partId, computerId, sellerId, saleDate);
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    public Sale getSaleById(int saleId) {
        String query = "SELECT * FROM Sale WHERE sale_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int customerId = resultSet.getInt("customer_id");
                    int partId = resultSet.getInt("part_id");
                    int computerId = resultSet.getInt("computer_id");
                    int sellerId = resultSet.getInt("seller_id");
                    Date saleDate = resultSet.getDate("sale_date");
                    return new Sale(saleId, customerId, partId, computerId, sellerId, saleDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSale(Sale sale) {
        String query = "UPDATE Sale SET customer_id = ?, part_id = ?, computer_id = ?, seller_id = ?, sale_date = ? WHERE sale_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sale.getCustomerId());
            statement.setInt(2, sale.getPartId());
            statement.setInt(3, sale.getComputerId());
            statement.setInt(4, sale.getSellerId());
            statement.setDate(5, sale.getSaleDate());
            statement.setInt(6, sale.getSaleId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSale(int saleId) {
        String query = "DELETE FROM Sale WHERE sale_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, saleId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}