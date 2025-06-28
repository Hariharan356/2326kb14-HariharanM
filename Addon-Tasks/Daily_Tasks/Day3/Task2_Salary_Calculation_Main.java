package Day3;

import java.util.Scanner;

public class Task2_Salary_Calculation_Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input employee details
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee age: ");
        int age = scanner.nextInt();

        System.out.print("Enter employee salary: ");
        double salary = scanner.nextDouble();

        // Create Employee object
        Employee emp = new Employee(name, age, salary);

        // Display employee info and salary calculations
        System.out.println("\nEmployee Details:");
        System.out.println("Name: " + emp.getName());
        System.out.println("Age: " + emp.getAge());
        System.out.printf("Gross Salary: $%.2f\n", emp.getSalary());
        System.out.printf("Provident Fund 'PF' (12%%): $%.2f\n", emp.calculatePF());
        System.out.printf("Net Salary: $%.2f\n", emp.calculateNetSalary());

        scanner.close();
    }
}