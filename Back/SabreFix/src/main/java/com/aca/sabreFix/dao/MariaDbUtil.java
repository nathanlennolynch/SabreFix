package com.aca.sabreFix.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MariaDbUtil {
    private static String connectionUrl =
            "jdbc:mariadb://localhost:3306/myweapons?user=root&password=whitebird";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }
    public static void main(String[] args) {
        Connection connection = MariaDbUtil.getConnection();
        if (null != connection) {
            System.out.println("A real connection!!!!");
        } else {
            System.out.println("Help. Connection not working.");
        }
    }

}
