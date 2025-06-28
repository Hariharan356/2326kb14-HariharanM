package Day2;

import java.util.Scanner;


public class Task3_Sentence_Split_Into_Words {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = sc.nextLine();

        String[] words = sentence.split("\\s+");

        System.out.println("Words in the sentence:");
        for (String word : words) {	
            System.out.println(word);
        }
        sc.close();
    }
}



/*
*/