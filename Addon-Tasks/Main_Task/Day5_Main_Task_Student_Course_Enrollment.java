package Main_Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Day5_Main_Task_Student_Course_Enrollment {

    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        HashMap<String, ArrayList<String>> studentCourses = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Course Enrollment System ---");
            System.out.println("1. Add Student with Courses");
            System.out.println("2. Remove Student");
            System.out.println("3. Display All Students and Courses");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add student and courses
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    if (students.contains(name)) {
                        System.out.println("Student already exists!");
                        break;
                    }

                    students.add(name);
                    ArrayList<String> courses = new ArrayList<>();
                    System.out.print("Enter number of courses: ");
                    int courseCount = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    for (int i = 0; i < courseCount; i++) {
                        System.out.print("Enter course " + (i + 1) + ": ");
                        String course = scanner.nextLine();
                        courses.add(course);
                    }

                    studentCourses.put(name, courses);
                    System.out.println("Student added successfully!");
                    break;

                case 2: // Remove student
                    System.out.print("Enter student name to remove: ");
                    String removeName = scanner.nextLine();

                    if (students.remove(removeName)) {
                        studentCourses.remove(removeName);
                        System.out.println("Student removed successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3: // Display all students and their courses
                    if (students.isEmpty()) {
                        System.out.println("No students enrolled.");
                    } else {
                        System.out.println("\n--- Enrolled Students and Their Courses ---");
                        Iterator<String> studentIterator = students.iterator();

                        while (studentIterator.hasNext()) {
                            String student = studentIterator.next();
                            ArrayList<String> enrolledCourses = studentCourses.get(student);

                            System.out.println("Student: " + student);
                            System.out.print("Courses: ");

                            Iterator<String> courseIterator = enrolledCourses.iterator();
                            while (courseIterator.hasNext()) {
                                System.out.print(courseIterator.next());
                                if (courseIterator.hasNext()) System.out.print(", ");
                            }
                            System.out.println();
                        }
                    }
                    break;

                case 4: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
