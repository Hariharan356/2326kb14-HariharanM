package Main_Task;

import java.util.Scanner;

public class Day2_Main_Task_String_Analyzer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();

        String[] words = sentence.trim().split("\\s+");

        int wordCount = words.length;

        int charCount = 0;
        for (char c : sentence.toCharArray()) {
            if (c != ' ') {
                charCount++;
            }
        }

        String reversed = new StringBuilder(sentence).reverse().toString();

        String longestWord = "";
        for (String word : words) {
            if (word.length() > longestWord.length()) {
                longestWord = word;
            }
        }

        System.out.println("\n--- String Analysis ---");
        System.out.println("Word Count: " + wordCount);
        System.out.println("Character Count (excluding spaces): " + charCount);
        System.out.println("Reversed Sentence: " + reversed);
        System.out.println("Longest Word: " + longestWord);
        
        sc.close();
    }
}



/*
*/