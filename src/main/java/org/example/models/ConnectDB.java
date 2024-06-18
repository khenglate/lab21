
package org.example.models;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;


public class ConnectDB {

    private Connection connection;


    public ConnectDB() {

        String url = "jdbc:sqlite:mydb.db"; // Specify your database URL

        try {

            connection = DriverManager.getConnection(url);

            System.out.println("connection Successful");

        } catch (SQLException e) {

            System.out.println("Error Connecting to Database");

            e.printStackTrace();

        }

    }


    public Connection getConnection() {

        return connection;

    }


    public void closeConnection() {

        try {

            if (connection != null) {

                System.out.println("Connection Closed");

                connection.close();

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
