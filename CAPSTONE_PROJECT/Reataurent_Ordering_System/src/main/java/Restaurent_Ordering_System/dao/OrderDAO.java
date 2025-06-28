package main.java.Restaurent_Ordering_System.dao;

import java.sql.*;
import java.time.LocalDateTime;

import main.java.Restaurent_Ordering_System.model.Order;
import main.java.Restaurent_Ordering_System.model.OrderItem;
import main.java.Restaurent_Ordering_System.service.ServiceFactory;

public class OrderDAO {

    private final Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // Save order for client or staff based on role
    public int saveOrder(Order order, int userId) {
        int orderId = -1;

        // Handle guest client (userId == -1)
        String role = (userId == -1) ? "client" : ServiceFactory.getUserDAO().getUserRoleById(userId);
        if (role == null) {
            System.err.println("Unknown user role.");
            return -1;
        }

        String insertOrderSQL;
        String insertItemSQL;

        if ("client".equalsIgnoreCase(role)) {
            insertOrderSQL = "INSERT INTO client_orders (user_id, total_amount, order_time, status) VALUES (?, ?, ?, ?)";
            insertItemSQL = "INSERT INTO client_order_items (order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        } else {
            insertOrderSQL = "INSERT INTO staff_orders (user_id, total_amount, order_time, status) VALUES (?, ?, ?, ?)";
            insertItemSQL = "INSERT INTO staff_order_items (order_id, item_id, quantity, subtotal) VALUES (?, ?, ?, ?)";
        }

        try (PreparedStatement ps = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setDouble(2, order.getTotal());
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(4, "placed");
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            try (PreparedStatement itemStmt = connection.prepareStatement(insertItemSQL)) {
                for (OrderItem oi : order.getItems()) {
                    itemStmt.setInt(1, orderId);
                    itemStmt.setInt(2, oi.getItem().getId());
                    itemStmt.setInt(3, oi.getQuantity());
                    itemStmt.setDouble(4, oi.getSubtotal());
                    itemStmt.addBatch();
                }
                itemStmt.executeBatch();
            }

        } catch (SQLException e) {
            System.err.println("Error saving order: " + e.getMessage());
        }

        return orderId;
    }

    public boolean cancelClientOrderById(int orderId, int cancelledById) {
        String sql = "UPDATE client_orders SET status = 'cancelled', status_updated_at = NOW(), cancelled_by = ? WHERE id = ? AND status = 'placed'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cancelledById);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error cancelling client order: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelStaffOrderById(int orderId, int cancelledById) {
        String sql = "UPDATE staff_orders SET status = 'cancelled', status_updated_at = NOW(), cancelled_by = ? WHERE id = ? AND status = 'placed'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cancelledById);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error cancelling staff order: " + e.getMessage());
            return false;
        }
    }

    public boolean restoreCancelledClientOrderById(int orderId, int restoredById) {
        String sql = "UPDATE client_orders SET status = 'restored', status_updated_at = NOW(), restored_by = ? WHERE id = ? AND status = 'cancelled'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, restoredById);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error restoring client order: " + e.getMessage());
            return false;
        }
    }

    public boolean restoreCancelledStaffOrderById(int orderId, int restoredById) {
        String sql = "UPDATE staff_orders SET status = 'restored', status_updated_at = NOW(), restored_by = ? WHERE id = ? AND status = 'cancelled'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, restoredById);
            ps.setInt(2, orderId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error restoring staff order: " + e.getMessage());
            return false;
        }
    }

    public void viewAllOrders() {
        try {
            System.out.println("\n--- 📋 Client Orders ---");
            String sqlClient = """
                SELECT o.id, o.user_id, u.username, o.total_amount, o.status, o.order_time,
                       o.cancelled_by, o.restored_by,
                       c.username AS cancelled_by_user, r.username AS restored_by_user
                FROM client_orders o
                LEFT JOIN users u ON o.user_id = u.id
                LEFT JOIN users c ON o.cancelled_by = c.id
                LEFT JOIN users r ON o.restored_by = r.id
                ORDER BY o.id DESC
            """;

            try (PreparedStatement ps = connection.prepareStatement(sqlClient);
                 ResultSet rs = ps.executeQuery()) {

                System.out.printf("%-10s %-12s %-12s %-10s %-15s %-15s %-20s%n",
                        "Order ID", "User", "Amount (₹)", "Status", "Cancelled By", "Restored By", "Timestamp");
                System.out.println("----------------------------------------------------------------------------------------------------");

                while (rs.next()) {
                    int orderId = rs.getInt("id");
                    String username = rs.getString("username") != null ? rs.getString("username") : "Client";
                    double amount = rs.getDouble("total_amount");
                    String status = rs.getString("status");
                    String cancelledBy = rs.getString("cancelled_by_user");
                    String restoredBy = rs.getString("restored_by_user");
                    LocalDateTime time = rs.getTimestamp("order_time").toLocalDateTime();

                    System.out.printf("%-10d %-12s %-12.2f %-10s %-15s %-15s %-20s%n",
                            orderId,
                            username,
                            amount,
                            status,
                            cancelledBy != null ? cancelledBy : "-",
                            restoredBy != null ? restoredBy : "-",
                            time);
                }
            }

            System.out.println("\n--- 📋 Staff Orders ---");
            String sqlStaff = """
                SELECT o.id, u.username, o.total_amount, o.status, o.order_time,
                       o.cancelled_by, o.restored_by,
                       c.username AS cancelled_by_user, r.username AS restored_by_user
                FROM staff_orders o
                LEFT JOIN users u ON o.user_id = u.id
                LEFT JOIN users c ON o.cancelled_by = c.id
                LEFT JOIN users r ON o.restored_by = r.id
                ORDER BY o.id DESC
            """;

            try (PreparedStatement ps = connection.prepareStatement(sqlStaff);
                 ResultSet rs = ps.executeQuery()) {

                System.out.printf("%-10s %-12s %-12s %-10s %-15s %-15s %-20s%n",
                        "Order ID", "User", "Amount (₹)", "Status", "Cancelled By", "Restored By", "Timestamp");
                System.out.println("----------------------------------------------------------------------------------------------------");

                while (rs.next()) {
                    int orderId = rs.getInt("id");
                    String username = rs.getString("username");
                    double amount = rs.getDouble("total_amount");
                    String status = rs.getString("status");
                    String cancelledBy = rs.getString("cancelled_by_user");
                    String restoredBy = rs.getString("restored_by_user");
                    LocalDateTime time = rs.getTimestamp("order_time").toLocalDateTime();

                    System.out.printf("%-10d %-12s %-12.2f %-10s %-15s %-15s %-20s%n",
                            orderId,
                            username,
                            amount,
                            status,
                            cancelledBy != null ? cancelledBy : "-",
                            restoredBy != null ? restoredBy : "-",
                            time);
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error fetching orders: " + e.getMessage());
        }
    }
}
