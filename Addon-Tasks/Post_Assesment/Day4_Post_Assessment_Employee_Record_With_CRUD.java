package Post_Assesment;

import java.io.*;
import java.util.*;

public class Day4_Post_Assessment_Employee_Record_With_CRUD {
	private static final String FILE_NAME = "C:\\Users\\Danish K S\\eclipse-workspace\\JAVA\\src\\Post_Assesment\\employees.txt"; // File path

	// Employee record class
	static class Employee {
		String id;
		String name;
		String department;
		double salary;

		Employee(String id, String name, String department, double salary) {
			this.id = id;
			this.name = name;
			this.department = department;
			this.salary = salary;
		}

		@Override
		public String toString() {
			return String.format("ID: %s | Name: %s | Department: %s | Salary: %.2f", id, name, department, salary);
		}

		// Convert employee to CSV line
		String toCSV() {
			return id + "," + name + "," + department + "," + salary;
		}
	}

	// Load all employees from file
	private static List<Employee> loadEmployees() {
		List<Employee> employees = new ArrayList<>();
		File file = new File(FILE_NAME);
		if (!file.exists()) {
			// File doesn't exist yet, return empty list
			return employees;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",", -1);
				if (parts.length == 4) {
					String id = parts[0];
					String name = parts[1];
					String department = parts[2];
					double salary = Double.parseDouble(parts[3]);
					Employee emp = new Employee(id, name, department, salary);
					employees.add(emp);
				}
			}
		} catch (IOException e) {
			System.out.println("Error loading employee records: " + e.getMessage());
		}
		return employees;
	}

	// Save all employees to file
	private static void saveEmployees(List<Employee> employees) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Employee emp : employees) {
				bw.write(emp.toCSV());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error saving employee records: " + e.getMessage());
		}
	}

	// Add new employee
	private static void addEmployee(Scanner scanner) {
		System.out.println("Enter new employee details :");
		System.out.print("ID: ");
		String id = scanner.nextLine().trim();

		List<Employee> employees = loadEmployees();
		// Check duplicate ID
		for (Employee emp : employees) {
			if (emp.id.equals(id)) {
				System.out.println("Employee ID already exists. Cannot add duplicate.");
				return;
			}
		}

		System.out.print("Name: ");
		String name = scanner.nextLine().trim();
		System.out.print("Department: ");
		String department = scanner.nextLine().trim();
		System.out.print("Salary: ");
		double salary;
		try {
			salary = Double.parseDouble(scanner.nextLine().trim());
		} catch (NumberFormatException e) {
			System.out.println("Invalid salary input. Aborting add.");
			return;
		}

		Employee newEmp = new Employee(id, name, department, salary);
		employees.add(newEmp);
		saveEmployees(employees);
		System.out.println("Employee added successfully.");
	}

	// List all employees
	private static void listEmployees() {
		List<Employee> employees = loadEmployees();
		if (employees.isEmpty()) {
			System.out.println("No employee records found.");
			return;
		}
		System.out.println("Employee Records:");
		for (Employee emp : employees) {
			System.out.println(emp);
		}
	}

	// Update employee by id
	private static void updateEmployee(Scanner scanner) {
		System.out.print("Enter employee ID to update: ");
		String id = scanner.nextLine().trim();
		List<Employee> employees = loadEmployees();
		Employee empToUpdate = null;
		for (Employee emp : employees) {
			if (emp.id.equals(id)) {
				empToUpdate = emp;
				break;
			}
		}
		if (empToUpdate == null) {
			System.out.println("Employee ID not found.");
			return;
		}

		System.out.println("Current details: " + empToUpdate);

		System.out.print("Enter new name (leave blank to keep current): ");
		String name = scanner.nextLine().trim();
		if (!name.isEmpty()) {
			empToUpdate.name = name;
		}

		System.out.print("Enter new department (leave blank to keep current): ");
		String department = scanner.nextLine().trim();
		if (!department.isEmpty()) {
			empToUpdate.department = department;
		}

		System.out.print("Enter new salary (leave blank to keep current): ");
		String salaryInput = scanner.nextLine().trim();
		if (!salaryInput.isEmpty()) {
			try {
				double salary = Double.parseDouble(salaryInput);
				empToUpdate.salary = salary;
			} catch (NumberFormatException e) {
				System.out.println("Invalid salary input. Salary not updated.");
			}
		}

		saveEmployees(employees);
		System.out.println("Employee updated successfully.");
	}

	// Delete employee by id
	private static void deleteEmployee(Scanner scanner) {
		System.out.print("Enter employee ID to delete: ");
		String id = scanner.nextLine().trim();
		List<Employee> employees = loadEmployees();
		boolean removed = employees.removeIf(emp -> emp.id.equals(id));
		if (removed) {
			saveEmployees(employees);
			System.out.println("Employee deleted successfully.");
		} else {
			System.out.println("Employee ID not found.");
		}
	}

	// Main menu loop
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("\n=== Employee Record System ===");
			System.out.println("1. Add Employee");
			System.out.println("2. List Employees");
			System.out.println("3. Update Employee");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.print("Choose an option (1-5): ");

			String choice = scanner.nextLine().trim();

			switch (choice) {
			case "1":
				addEmployee(scanner);
				break;
			case "2":
				listEmployees();
				break;
			case "3":
				updateEmployee(scanner);
				break;
			case "4":
				deleteEmployee(scanner);
				break;
			case "5":
				System.out.println("Exiting program. Goodbye!");
				scanner.close();
				return;
			default:
				System.out.println("Invalid option. Please choose between 1-5.");
			}
		}
	}
}
