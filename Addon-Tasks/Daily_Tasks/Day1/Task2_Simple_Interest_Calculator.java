package Day1;
import java.util.Scanner;


public class Task2_Simple_Interest_Calculator {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter Principal amount: ");
		double principal = in.nextDouble();

		System.out.print("Enter Rate of Interest: ");
		double rate = in.nextDouble();

		System.out.print("Enter Time (in years): ");
		double time = in.nextDouble();

		double simpleInterest = 0;

		if (principal > 0 && rate > 0 && time > 0) {
			simpleInterest = (principal * rate * time) / 100;
			System.out.println("Simple Interest = " + simpleInterest);
		} 
		else {
			System.out.println("Please enter valid positive values for principal, rate, and time.");
		}

		in.close();
	}
}


/*
 * First get the data from user by using the built-in class called Scanner.
 * Now using SOP to give a message to user.
 * Then get the value of principle from user.
 * Then get the value of Interest from user.
 * Then get the value of time period in "year" from user.
 * Now create a double variable to calculate the Simple-Interest.
 * By using if condition to check all values which are given by user is > 0.
 * Then inside if condition calculate the Sinple-Interest by multiply the " Principle*Rate*Time ".
 * Then print the result of Simple-Interest.
 * By using else Show the message "enter valid data" to user.
 */

