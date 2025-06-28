package main.java.Restaurent_Ordering_System.dao;

import java.sql.*;
import java.util.*;

import main.java.Restaurent_Ordering_System.model.MenuItem;

public class MenuDAO {

    private final Connection connection;

    public MenuDAO(Connection connection) {
        this.connection = connection;
    }

    public List<MenuItem> findAll() {
        List<MenuItem> list = new ArrayList<>();
        String sql = "SELECT id, name, price, category FROM menu_items";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new MenuItem(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category")));
            }
        } catch (SQLException e) {
            System.err.println("Error loading menu: " + e.getMessage());
        }

        return list;
    }

    public Optional<MenuItem> findById(int id) {
        String sql = "SELECT id, name, price, category FROM menu_items WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new MenuItem(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("category")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding item " + id + ": " + e.getMessage());
        }

        return Optional.empty();
    }
}
