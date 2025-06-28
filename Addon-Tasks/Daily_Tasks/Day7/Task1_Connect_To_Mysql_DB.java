package Day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Task1_Connect_To_Mysql_DB {

	/*
	 * create database mydatabase;
	 */
	
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydatabase"; // Replace with your DB name
        String user = "root";      // Replace with your DB user_name
        String password = "Hari@123"; // Replace with your DB password

        Connection conn = null;

        try {
            // Load MySQL JDBC Driver (optional for JDBC 4.0+)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(url, user, password);

            if (conn != null) {
                System.out.println("✅ Connected to the MySQL database!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed.");
            e.printStackTrace();
        } finally {
            // Close connection
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
