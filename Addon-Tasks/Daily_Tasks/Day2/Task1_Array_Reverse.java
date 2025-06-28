package Day2;

import java.util.Scanner;

public class Task1_Array_Reverse {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.print("Enter the number of elements: ");
		int n = in.nextInt();

		int[] arr = new int[n];

		System.out.println("Enter " + n + " numbers:");
		for (int i = 0; i < n; i++) {
			arr[i] = in.nextInt();
		}

		System.out.println("Array in reverse order:");
		for (int i = n - 1; i >= 0; i--) {
			System.out.print(arr[i] + " ");
		}

		in.close();
	}
}


