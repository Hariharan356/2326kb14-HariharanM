package Day4;

	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Scanner;

	public class Task3_Write_String_To_File {
	    public static void main(String[] args) {
	        String filePath = "C:\\Users\\Hariharan\\OneDrive\\Desktop\\JAVA\\Core_Java\\src\\Testing_File.txt"; // File path
	        Scanner scanner = new Scanner(System.in);
	        
	        System.out.println("Enter The Text (type 'exit' to finish):");

	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
	            String line;
	            while (true) {
	                line = scanner.nextLine();
	                if (line.equalsIgnoreCase("exit")) {
	                    break; // Exit the loop if the user types 'exit'
	                }
	                bw.write(line);
	                bw.newLine(); // Write a new line after each string
	            }
	            System.out.println("Data written to file successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            scanner.close(); // Close the scanner
	        }
	    }
	}
