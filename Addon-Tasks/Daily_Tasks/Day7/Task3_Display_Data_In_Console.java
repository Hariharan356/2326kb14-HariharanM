package Day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


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


public class Task3_Display_Data_In_Console {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school"; // Replace 'school' with your database name
        String user = "root"; // Replace with your DB user_name
        String password = "Hari@123"; // Replace with your DB password

        try {
            // Load JDBC driver (optional in modern JDBC)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to the database.");

            // Create and execute query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            // Display the result
            System.out.println("\n📋 Student Data:");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String grade = rs.getString("grade");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade);
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ SQL Exception occurred.");
            e.printStackTrace();
        }
    }
}
