package Day1;

public class Task1_Even_Numbers {
	public static void main(String[] args) {
		System.out.println("Even numbers from 1 to 100:");

		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 1) {
				System.out.println(i);
	        }
	    }
	}
}


/*
 * First using SOP to give a message to user.
 * Then by using for loop to satisfy the condition of increasing the values from 1 to 100.
 * Now using if condition to find the even numbers between 1 to 100 by checking which numbers are divisible by to and remains 0.
 * Then print the values which are even with using the variable i.
 */
