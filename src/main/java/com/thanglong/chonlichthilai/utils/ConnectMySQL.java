package com.thanglong.chonlichthilai.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySQL {
    private static ConnectMySQL instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://45.122.253.162:3306/chonlichthilai";
    private static final String USERNAME = "chonlichthilai";
    private static final String PASSWORD = "thanglong..2030";

    // Private constructor for Singleton pattern
    private ConnectMySQL() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("✅ Database connected successfully!");
        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to connect to database!", e);
        }
    }

    // Get instance of ConnectMySQL (Singleton)
    public static synchronized ConnectMySQL getInstance() {
        if (instance == null) {
            instance = new ConnectMySQL();
        }
        return instance;
    }

    // Get the database connection
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                System.out.println("⚠️ Connection was closed. Reconnecting...");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to reconnect to database!", e);
        }
        return connection;
    }

    // Close the connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
