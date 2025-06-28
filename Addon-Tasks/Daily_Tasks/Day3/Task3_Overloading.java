package Day3;

import java.util.Scanner;

public class Task3_Overloading {

    public int sum(int a, int b) {
        return a + b;
    }

    public double sum(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task3_Overloading ovr = new Task3_Overloading();

        System.out.print("Enter first integer: ");
        int intA = scanner.nextInt();
        System.out.print("Enter second integer: ");
        int intB = scanner.nextInt();
        int intResult = ovr.sum(intA, intB);

        System.out.print("Enter first double: ");
        double doubleA = scanner.nextDouble();
        System.out.print("Enter second double: ");
        double doubleB = scanner.nextDouble();
        double doubleResult = ovr.sum(doubleA, doubleB);

        System.out.println("Sum of integers: " + intResult);
        System.out.println("Sum of doubles: " + doubleResult);

        scanner.close();
    }
}
