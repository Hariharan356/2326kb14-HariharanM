package Post_Assesment;

import java.util.Scanner;

public class Day3_Post_Assesment_Library_Management_System_Main {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Issue Book");
            System.out.println("4. Show Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title to add: ");
                    String addTitle = scanner.nextLine();
                    lib.addBook(addTitle);
                    break;
                case 2:
                    System.out.print("Enter book title to remove: ");
                    String removeTitle = scanner.nextLine();
                    lib.removeBook(removeTitle);
                    break;
                case 3:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = scanner.nextLine();
                    lib.issueBook(issueTitle);
                    break;
                case 4:
                    lib.showBooks();
                    break;
                case 5:
                    System.out.println("Exiting system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
