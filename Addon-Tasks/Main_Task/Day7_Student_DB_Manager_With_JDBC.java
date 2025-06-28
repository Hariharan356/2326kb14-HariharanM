package Main_Task;

import java.sql.*;
import java.util.Scanner;


/*
 * CREATE DATABASE IF NOT EXISTS school;

	USE school;

	CREATE TABLE IF NOT EXISTS students (
    	id INT PRIMARY KEY AUTO_INCREMENT,
    	name VARCHAR(100),
    	age INT,
    	grade VARCHAR(10)
	);

 */

public class Day7_Student_DB_Manager_With_JDBC {

    static final String URL = "jdbc:mysql://localhost:3306/school";
    static final String USER = "root";
    static final String PASSWORD = "Danish8072"; // Replace with your MySQL password

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ Connected to MySQL database.");

            do {
                System.out.println("\n📘 Student DB Manager");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Search Student by ID");
                System.out.println("4. Delete Student by ID");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter age: ");
                        int age = scanner.nextInt();
                        System.out.print("Enter grade: ");
                        String grade = scanner.next();

                        String insertSQL = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
                        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                            pstmt.setString(1, name);
                            pstmt.setInt(2, age);
                            pstmt.setString(3, grade);
                            int rows = pstmt.executeUpdate();
                            if (rows > 0) System.out.println("✅ Student added successfully.");
                        }
                        break;

                    case 2:
                        String selectSQL = "SELECT * FROM students";
                        try (Statement stmt = conn.createStatement();
                             ResultSet rs = stmt.executeQuery(selectSQL)) {
                            System.out.println("\n📋 All Students:");
                            System.out.println("ID | Name       | Age | Grade");
                            System.out.println("--------------------------------");
                            while (rs.next()) {
                                System.out.printf("%-3d| %-10s | %-3d | %-5s\n",
                                        rs.getInt("id"), rs.getString("name"),
                                        rs.getInt("age"), rs.getString("grade"));
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter student ID: ");
                        int searchId = scanner.nextInt();
                        String searchSQL = "SELECT * FROM students WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(searchSQL)) {
                            pstmt.setInt(1, searchId);
                            ResultSet rs = pstmt.executeQuery();
                            if (rs.next()) {
                                System.out.println("👤 Student Found:");
                                System.out.println("ID: " + rs.getInt("id"));
                                System.out.println("Name: " + rs.getString("name"));
                                System.out.println("Age: " + rs.getInt("age"));
                                System.out.println("Grade: " + rs.getString("grade"));
                            } else {
                                System.out.println("❌ No student found with ID: " + searchId);
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Enter student ID to delete: ");
                        int deleteId = scanner.nextInt();
                        String deleteSQL = "DELETE FROM students WHERE id = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                            pstmt.setInt(1, deleteId);
                            int rows = pstmt.executeUpdate();
                            if (rows > 0)
                                System.out.println("🗑️ Student deleted.");
                            else
                                System.out.println("❌ Student ID not found.");
                        }
                        break;

                    case 5:
                        System.out.println("👋 Exiting Student DB Manager.");
                        break;

                    default:
                        System.out.println("⚠️ Invalid choice. Try again.");
                }

            } while (choice != 5);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("❌ Error connecting to DB or executing query.");
            e.printStackTrace();
        }
    }
}
