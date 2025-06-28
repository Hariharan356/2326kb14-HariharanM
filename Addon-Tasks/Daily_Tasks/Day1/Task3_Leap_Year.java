package Day1;

import java.util.Scanner;

public class Task3_Leap_Year {


	public static boolean LeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the Year :");
		int year = in.nextInt();

		if (LeapYear(year)) {
			System.out.println(year + " is a leap year.");
		} else {
			System.out.println(year + " is not a leap year.");
		}
		in.close();
	}
}


/* First checking the year is leap year or not by using if condition.
 * In the if condition first the year will divide by 4 and it must remains 0 and at the same time the number will divide by 100 and it must not remain 0.
 * Or another condition the year will divide by 400 and it remains 0.
 * If any one of above condition is satisfy then it will true the condition then it will move on to the next step.
 * In the main function Scanner variable is created to get the input from user.
 * Then with SOP Give a message to user to enter a year.
 * Then get the input from user using scanner variable.
 * Now with if statement to check the above leap year condition is satisfy the user given year.
 * Then it will print the year is leap year otherwise it will say the year is not a leap year
 */
