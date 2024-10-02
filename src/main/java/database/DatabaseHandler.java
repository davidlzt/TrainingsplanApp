package database;

import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection conn = DatabaseHandler.connect();
        DatabaseHandler.disconnect();
    }

    // Tabelle erstellen, falls sie nicht existiert
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(255), " +
                "email VARCHAR(255), " +
                "password VARCHAR(255), " +
                "gewicht DOUBLE, " +
                "age INT, " +
                "groesse DOUBLE, " +
                "geschlecht VARCHAR(10))";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle erstellt oder bereits vorhanden.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Benutzer in die Datenbank einfügen
    public void insertUser(String username, String email, String password, double gewicht, int age, double groesse, String geschlecht) {
        String sql = "INSERT INTO users (username, email, password, gewicht, age, groesse, geschlecht) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setDouble(4, gewicht);
            pstmt.setInt(5, age);
            pstmt.setDouble(6, groesse);
            pstmt.setString(7, geschlecht);
            pstmt.executeUpdate();
            System.out.println("User successfully registered!");

        } catch (SQLException e) {
            System.out.println("Insertion failed: " + e.getMessage());
        }
    }
}
