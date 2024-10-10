package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://localhost:3306/project";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection;

    private static Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
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

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255), "
                + "email VARCHAR(255), "
                + "password VARCHAR(255), "
                + "gewicht DOUBLE, "
                + "age INT, "
                + "groesse DOUBLE, "
                + "geschlecht VARCHAR(10), "
                + "role VARCHAR(32))";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabelle erstellt oder bereits vorhanden.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle: " + e.getMessage());
        }
    }

    private boolean isValidRole(String role) {
        List<String> validRoles = Arrays.asList("admin", "moderator", "user", "vip");
        return validRoles.contains(role);
    }

    public void insertUser(String username, String email, String password, double gewicht, int age, double groesse, String geschlecht, String role) {
        if (!isValidRole(role)) {
            System.out.println("Ungültige Rolle! Zulässige Werte: admin, moderator, user, vip.");
            return;
        }

        String sql = "INSERT INTO users (username, email, password, gewicht, age, groesse, geschlecht, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setDouble(4, gewicht);
            pstmt.setInt(5, age);
            pstmt.setDouble(6, groesse);
            pstmt.setString(7, geschlecht);
            pstmt.setString(8, role);
            pstmt.executeUpdate();
            System.out.println("User erfolgreich registriert!");
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Users: " + e.getMessage());
        }
    }

    public boolean validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Login-Validierung fehlgeschlagen: " + e.getMessage());
            return false;
        }
    }

    public boolean isAdmin(String username) {
        String sql = "SELECT role FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    return "admin".equalsIgnoreCase(role);
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Admin-Überprüfung: " + e.getMessage());
        }
        return false;
    }

    public void updateUserRole(String adminUsername, int userId, String newRole) {
        if (!isAdmin(adminUsername)) {
            System.out.println("Nur Admins können die Rolle ändern.");
            return;
        }

        if (!isValidRole(newRole)) {
            System.out.println("Ungültige Rolle.");
            return;
        }

        String sql = "UPDATE users SET role = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newRole);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
            System.out.println("Rolle erfolgreich geändert.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Aktualisieren der Rolle: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users ORDER BY role";
        List<User> users = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getDouble("gewicht"), rs.getInt("age"), rs.getInt("groesse"), rs.getString("geschlecht"), rs.getString("role"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen der Nutzer: " + e.getMessage());
        }

        return users;
    }
}
