package Post_Assesment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Day5_Mini_Student_Management_App {

    public static void main(String[] args) {
        ArrayList<String> studentNames = new ArrayList<>();
        HashMap<Integer, ArrayList<String>> studentCourses = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Mini Student Management App ---");
            System.out.println("1. Add Student with Courses");
            System.out.println("2. Search Student by ID");
            System.out.println("3. Delete Student by ID");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Course List to File");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add student
                    System.out.print("Enter student ID (integer): ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    if (studentCourses.containsKey(id)) {
                        System.out.println("Student ID already exists!");
                        break;
                    }

                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    studentNames.add(name);

                    System.out.print("Enter number of courses: ");
                    int numCourses = scanner.nextInt();
                    scanner.nextLine();

                    ArrayList<String> courses = new ArrayList<>();
                    for (int i = 0; i < numCourses; i++) {
                        System.out.print("Enter course " + (i + 1) + ": ");
                        courses.add(scanner.nextLine());
                    }

                    studentCourses.put(id, courses);
                    System.out.println("Student added successfully!");
                    break;

                case 2: // Search student
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();

                    if (studentCourses.containsKey(searchId)) {
                        int index = new ArrayList<>(studentCourses.keySet()).indexOf(searchId);
                        String studentName = studentNames.get(index);
                        System.out.println("Student Name: " + studentName);
                        System.out.println("Courses: " + studentCourses.get(searchId));
                    } else {
                        System.out.println("Student ID not found.");
                    }
                    break;

                case 3: // Delete student
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();

                    if (studentCourses.containsKey(deleteId)) {
                        int index = new ArrayList<>(studentCourses.keySet()).indexOf(deleteId);
                        studentNames.remove(index);
                        studentCourses.remove(deleteId);
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student ID not found.");
                    }
                    break;

                case 4: // Display all students
                    if (studentCourses.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("\n--- Student List ---");
                        Iterator<Map.Entry<Integer, ArrayList<String>>> it = studentCourses.entrySet().iterator();
                        int nameIndex = 0;
                        while (it.hasNext()) {
                            Map.Entry<Integer, ArrayList<String>> entry = it.next();
                            System.out.println("ID: " + entry.getKey() + ", Name: " + studentNames.get(nameIndex));
                            System.out.println("Courses: " + entry.getValue());
                            nameIndex++;
                        }
                    }
                    break;

                case 5: // Save course list to a file
                    try (FileWriter writer = new FileWriter("C:\\Users\\Danish K S\\eclipse-workspace\\JAVA\\src\\Post_Assesment\\Student_Details.txt")) {
                        int i = 0;
                        for (Map.Entry<Integer, ArrayList<String>> entry : studentCourses.entrySet()) {
                            writer.write("ID: " + entry.getKey() + ", Name: " + studentNames.get(i++) + "\n");
                            writer.write("Courses: " + entry.getValue() + "\n\n");
                        }
                        System.out.println("Student course list saved to student_courses.txt");
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    break;

                case 6: // Exit
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
