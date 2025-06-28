package Day2;

import java.util.Scanner;

public class Task2_Vowels_And_Consonants {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        int vowels = 0, consonants = 0;

        for (int i = 0; i < input.length(); i++) {
        	char ch = input.charAt(i);

        	if (Character.isLetter(ch)) {	
        		ch = Character.toLowerCase(ch);
        		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
        			vowels++;
        		} else {
        			consonants++;
        		}
        	}
        }

        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        
        sc.close();
	}
}



/*
*/