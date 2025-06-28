package Post_Assesment;

import java.sql.*;
import java.util.Scanner;


/*
 *CREATE DATABASE IF NOT EXISTS store;

	USE store;

	CREATE TABLE IF NOT EXISTS products (
    	id INT AUTO_INCREMENT PRIMARY KEY,
    	name VARCHAR(100),
    	quantity INT,
    	price DOUBLE
	);
 
 */


public class Day7_JDBC_Inventory_App {

    static final String URL = "jdbc:mysql://localhost:3306/store";
    static final String USER = "root";
    static final String PASSWORD = "Hari@123"; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Connected to MySQL database.");

            do {
                System.out.println("\n📦 Inventory Management Menu");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Search Product by ID");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int qty = scanner.nextInt();
                        System.out.print("Enter price: ");
                        double price = scanner.nextDouble();

                        String insertSQL = "INSERT INTO products (name, quantity, price) VALUES (?, ?, ?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                            pstmt.setString(1, name);
                            pstmt.setInt(2, qty);
                            pstmt.setDouble(3, price);
                            int rows = pstmt.executeUpdate();
                            if (rows > 0) System.out.println("✅ Product added successfully.");
                        }
                        break;

                    case 2:
                        String selectSQL = "SELECT * FROM products";
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(selectSQL)) {
                            System.out.println("\n📋 All Products:");
                            System.out.println("ID | Name       | Qty | Price");
                            System.out.println("----------------------------------");
                            while (rs.next()) {
                                System.out.printf("%-3d| %-10s | %-3d | ₹%.2f\n",
                                        rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getInt("quantity"),
                                        rs.getDouble("price"));
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter product ID: ");
                        int pid = scanner.nextInt();

                        String searchSQL = "SELECT * FROM products WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(searchSQL)) {
                            pstmt.setInt(1, pid);
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                System.out.println("🔍 Product Found:");
                                System.out.println("ID: " + rs.getInt("id"));
                                System.out.println("Name: " + rs.getString("name"));
                                System.out.println("Quantity: " + rs.getInt("quantity"));
                                System.out.println("Price: ₹" + rs.getDouble("price"));
                            } else {
                                System.out.println("❌ No product found with ID " + pid);
                            }
                        }
                        break;

                    case 4:
                        System.out.println("👋 Exiting Inventory App.");
                        break;

                    default:
                        System.out.println("⚠️ Invalid choice. Try again.");
                }

            } while (choice != 4);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Database error occurred.");
            e.printStackTrace();
        }
    }
}
