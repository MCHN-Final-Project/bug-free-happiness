package api.controllers.helpers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlMethods {
    public static void deleteUserById(String key, int value) {
        String dbUrl = "jdbc:mariadb://localhost:3307/wearedb";
        String username = "root";
        String password = "root";
        String deleteTarget = String.format("DELETE FROM users WHERE %s=%d;", key, value);

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(deleteTarget);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void TruncateRequests() {
        String dbUrl = "jdbc:mariadb://localhost:3307/wearedb";
        String username = "root";
        String password = "root";
        String deleteTarget = String.format("TRUNCATE TABLE requests;");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(deleteTarget);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void TruncateConnections() {
        String dbUrl = "jdbc:mariadb://localhost:3307/wearedb";
        String username = "root";
        String password = "root";
        String deleteTarget = String.format("TRUNCATE TABLE connection_users;");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(deleteTarget);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void TruncateUsers() {
        String dbUrl = "jdbc:mariadb://localhost:3307/wearedb";
        String username = "root";
        String password = "root";
        String deleteTarget = String.format("TRUNCATE TABLE users;");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection con = DriverManager.getConnection(dbUrl, username, password)) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate(deleteTarget);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
