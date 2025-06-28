package Post_Assesment;

import java.util.Scanner;


public class Day1_Post_Assessment_Students_Mark_Sheet {


	public static String getGrade(int marks) {
		if (marks >= 90 && marks <= 100) {
			return "A+";
		} else if (marks >= 80) {
			return "A";
		} else if (marks >= 70) {
			return "B";
		} else if (marks >= 60) {
			return "C";
		} else if (marks >= 50) {
			return "D";
		} else if (marks >= 35) {
			return "E";
		} else {
			return "F (Fail)";
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter student's mark (0-100): ");
		int marks = scanner.nextInt();

		if (marks < 0 || marks > 100) {
			System.out.println("Invalid marks entered. Please enter a value between 0 and 100.");
		} else {
			
			String grade = getGrade(marks);
			System.out.println("The student's grade is: " + grade);
		}

		scanner.close();
	}
}


/*In the Student mark sheet is will get the marks from user and show the result in grade
 * By using if else statement it will segregate the marks to the grades.
 * If mark is greater than 90 and at the same time less than 100 it will show grade A+.
 * If the mark is greater than 80 it will A grade.
 * If the mark is greater than 70 it will B grade.
 * If the mark is greater than 60 it will C grade.
 * If the mark is greater than 50 it will D grade.
 * If the mark is greater than 35 it will E grade.
 * If all the above condition is false it will say fail.
 * Then in the main function an if condition is used to say the marks must not less than 0 and must not greater than 100.
 * Then print the result as a message with grades. 
 */
