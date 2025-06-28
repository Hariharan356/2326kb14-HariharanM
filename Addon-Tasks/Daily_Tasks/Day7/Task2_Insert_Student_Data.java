package Day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
 * CREATE DATABASE IF NOT EXISTS school;

	USE school;

	CREATE TABLE IF NOT EXISTS students (
    	id INT AUTO_INCREMENT PRIMARY KEY,
    	name VARCHAR(100),
    	age INT,
    	grade VARCHAR(10)
	);
	
 */


public class Task2_Insert_Student_Data {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/school"; // DB name: school
        String user = "root";
        String password = "Danish8072"; // 🔁 Replace with your actual MySQL password

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load the JDBC driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to database.");

            // Prepare SQL insert statement
            String insertQuery = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";

            stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, "Hari");
            stmt.setInt(2, 30);
            stmt.setString(3, "A");

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Student data inserted successfully.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ SQL Exception occurred.");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
