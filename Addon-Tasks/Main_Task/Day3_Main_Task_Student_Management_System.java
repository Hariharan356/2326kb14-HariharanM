package Main_Task;

import java.util.Scanner;

class Student {
    private String name;
    private int id;
    private int[] marks;

    public Student(String name, int id, int numberOfSubjects) {
        this.name = name;
        this.id = id;
        this.marks = new int[numberOfSubjects];
    }

    public void assignMark(int subjectIndex, int mark) {
        if (subjectIndex < 0 || subjectIndex >= marks.length) {
            System.out.println("Invalid subject index.");
            return;
        }
        if (mark < 0 || mark > 100) {
            System.out.println("Invalid mark. Must be between 0 and 100.");
            return;
        }
        marks[subjectIndex] = mark;
    }

    public void displayResult() {
        System.out.println("Student Name: " + name);
        System.out.println("Student ID: " + id);
        int total = 0;
        for (int i = 0; i < marks.length; i++) {
            System.out.println("Mark for subject " + (i + 1) + ": " + marks[i]);
            total += marks[i];
        }
        double average = (double) total / marks.length;
        System.out.println("Total Marks: " + total);
        System.out.printf("Average Marks: %.2f\n", average);
    }
}

public class Day3_Main_Task_Student_Management_System {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        Student student = new Student(name, id, numberOfSubjects);

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter mark for subject " + (i + 1) + ": ");
            int mark = scanner.nextInt();
            student.assignMark(i, mark);
        }

        System.out.println("\n--- Student Result ---");
        student.displayResult();

        scanner.close();
    }
}
