package main.java.Restaurent_Ordering_System.dao;

import java.sql.*;

import main.java.Restaurent_Ordering_System.model.User;

public class UserDAO {

    private final Connection connection;

    // ✅ Add this constructor
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public User login(String username, String password) {
        String sql = "SELECT id, username, password, role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username.trim());
            ps.setString(2, password.trim());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        }

        return null;
    }

    // ✅ Add this helper method (needed by OrderDAO)
    public String getUserRoleById(int userId) {
        String sql = "SELECT role FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("role");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting user role: " + e.getMessage());
        }
        return null;
    }
}
