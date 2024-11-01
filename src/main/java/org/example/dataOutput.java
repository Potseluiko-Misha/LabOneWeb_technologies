package org.example;
import java.sql.*;
import java.util.Scanner;

public class dataOutput {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/labone";
        String username = "root";
        String password = "кщще";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Введите номер part_id, с которого начать вывод данных из таблицы part:");
            int startingId = scanner.nextInt();

            String queryPart = "SELECT * FROM part WHERE part_id >= ? LIMIT 10";
            PreparedStatement statementPart = connection.prepareStatement(queryPart);
            statementPart.setInt(1, startingId);
            ResultSet resultSetPart = statementPart.executeQuery();

            System.out.println("Данные из таблицы part:");
            while (resultSetPart.next()) {
                System.out.println("ID: " + resultSetPart.getInt("part_id") + ", Name: " + resultSetPart.getString("name"));
            }

            System.out.println("\nВведите номер part_id, с которого начать вывод данных из таблицы sale:");
            startingId = scanner.nextInt();

            String querySale = "SELECT * FROM sale WHERE part_id >= ? LIMIT 10";
            PreparedStatement statementSale = connection.prepareStatement(querySale);
            statementSale.setInt(1, startingId);
            ResultSet resultSetSale = statementSale.executeQuery();

            System.out.println("\nДанные из таблицы sale:");
            while (resultSetSale.next()) {
                System.out.println("Sale ID: " + resultSetSale.getInt("sale_id") + ", Customer ID: " + resultSetSale.getInt("customer_id") +
                        ", Part ID: " + resultSetSale.getInt("part_id") + ", Computer ID: " + resultSetSale.getInt("computer_id") +
                        ", Seller ID: " + resultSetSale.getInt("seller_id") + ", Sale Date: " + resultSetSale.getDate("sale_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}