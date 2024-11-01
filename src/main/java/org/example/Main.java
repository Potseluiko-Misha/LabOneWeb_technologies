package org.example;
import org.example.entity.*;
import org.example.dao.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            String url = "jdbc:mysql://localhost:3306/labone"; // Замените на ваш URL базы данных
            String username = "root"; // Замените на ваше имя пользователя
            String password = "кщще"; // Замените на ваш пароль

            Connection connection = DriverManager.getConnection(url, username, password); // Установка соединения

            PartDao partDao = new PartDao(connection); // Создаем экземпляр класса PartDao

            // Добавление новой детали
            Part newPart = new Part(2, "RAM2");
            partDao.addPart(newPart);

            // Получение всех деталей
            List<Part> parts = partDao.getAllParts();
            for (Part part : parts) {
                System.out.println(part);
            }

            // Получение детали по ID
            Part partById = partDao.getPartById(2);
            System.out.println("Деталь по ID: " + partById);

            // Обновление информации о детали
            //partById.setName("Новое название детали");
            //partDao.updatePart(partById);

            // Удаление детали
            //partDao.deletePart(1);

            connection.close(); // Закрываем соединение

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
