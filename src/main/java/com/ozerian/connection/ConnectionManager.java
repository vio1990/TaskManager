package com.ozerian.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String URL = "jdbc:mysql://localhost:3306/taskmanager";
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            try {
                connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL Exception " + e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Driver is not found!");
        }
        return connection;
    }

}
