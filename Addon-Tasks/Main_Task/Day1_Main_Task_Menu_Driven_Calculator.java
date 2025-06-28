package Main_Task;

import java.util.Scanner;

public class Day1_Main_Task_Menu_Driven_Calculator {

	public static double add(double a, double b) {
		return a + b;
	}

	public static double subtract(double a, double b) {
		return a - b;
	}

	public static double multiply(double a, double b) {
		return a * b;
	}

	public static double divide(double a, double b) {
		if (b == 0) {
			System.out.println("Error: Cannot divide by zero.");
			return Double.NaN;
		}
		return a / b;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		double num1, num2, result;

		do {
			System.out.println("\n--- Calculator Menu ---");
			System.out.println("1. Addition");
			System.out.println("2. Subtraction");
			System.out.println("3. Multiplication");
			System.out.println("4. Division");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			
			if (choice == 5) {
				System.out.println("Exiting calculator...");
				break;
			}

			System.out.print("Enter first number: ");
			num1 = scanner.nextDouble();
			System.out.print("Enter second number: ");
			num2 = scanner.nextDouble();

			switch (choice) {
			case 1:
				result = add(num1, num2);
				System.out.println("Result: " + result);
				break;
			case 2:
				result = subtract(num1, num2);
				System.out.println("Result: " + result);
				break;
			case 3:
				result = multiply(num1, num2);
				System.out.println("Result: " + result);
				break;
			case 4:
				result = divide(num1, num2);
				if (!Double.isNaN(result)) {
					System.out.println("Result: " + result);
				}
				break;
			default:
				System.out.println("Invalid choice. Please select from 1 to 5.");
			}
		} while (true);

		scanner.close();    
	}
}


/* In the menu driven calculator Getting all input data from user in the double format because the calculator will calculate the result either in decimal or not in decimal.
 * Then use four functions for addition subtraction multiplication and division calculation.
 * In the division there is an if condition if the user's data is 1/0 or anything divided by 0 will give an error.
 * Because of this in division part the condition will check the divisor must not equals to 0.
 * Then create scanner variable to get input from user and a int variable for choosing the option.
 * Then an another choice is if the user choose the 5th option it will exit the calculator.
 * Then get the input from user with two variable.
 * In switch case according to user's choice the switch will switch the calculator. 
 * Then the will loop will run the program infinite time until it false.
 * 
 */
