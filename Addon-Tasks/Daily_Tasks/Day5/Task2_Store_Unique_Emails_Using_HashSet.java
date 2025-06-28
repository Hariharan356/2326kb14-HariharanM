package Day5;

import java.util.HashSet;
import java.util.Set;

public class Task2_Store_Unique_Emails_Using_HashSet {

    public static void main(String[] args) {
        // Create a HashSet to store unique email addresses
        Set<String> emailSet = new HashSet<>();

        // Add email addresses
        emailSet.add("alice@example.com");
        emailSet.add("bob@example.com");
        emailSet.add("charlie@example.com");
        emailSet.add("alice@example.com");  // Duplicate
        emailSet.add("bob@example.com");    // Duplicate
        emailSet.add("david@example.com");

        // Display the unique email addresses
        System.out.println("Unique Email Addresses:");
        for (String email : emailSet) {
            System.out.println(email);
        }
    }
}
