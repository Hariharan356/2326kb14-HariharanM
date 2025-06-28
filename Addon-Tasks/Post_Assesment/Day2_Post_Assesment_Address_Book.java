package Post_Assesment;

import java.util.Scanner;

public class Day2_Post_Assesment_Address_Book {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        final int SIZE = 5;
        String[] contacts = new String[SIZE];
        int count = 0;

        while (true) {
            System.out.println("\n--- Mini Address Book ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Show All Contacts");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (count < SIZE) {
                        System.out.print("Enter contact name: ");
                        contacts[count] = sc.nextLine();
                        count++;
                        System.out.println("Contact added successfully!");
                    } else {
                        System.out.println("Address book is full!");
                    }
                    break;
                case 2:
                    System.out.println("\n--- Contact List ---");
                    if (count == 0) {
                        System.out.println("No contacts to display.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            System.out.println((i + 1) + ". " + contacts[i]);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting address book. Goodbye!");
                   sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
