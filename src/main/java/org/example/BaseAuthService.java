package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseAuthService implements AuthService {
    private Connection connection;

    public BaseAuthService() {
        try {
            // Установка соединения с базой данных SQLite
            this.connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Zero2/IdeaProjects/laba21/mydb.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен!");
    }

    @Override
    public void stop() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Сервис аутентификации остановлен!");
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        String query = "SELECT nick FROM users WHERE login = ? AND pass = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("nick");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
