package Day5;

import java.util.HashMap;
import java.util.Map;

public class Task3_Store_Student_Name_Marks_Using_HashMap {

    public static void main(String[] args) {
        // Create a HashMap to store student name → marks
        HashMap<String, Integer> studentMarks = new HashMap<>();

        // Add student name and marks
        studentMarks.put("Hari", 85);
        studentMarks.put("Divya", 78);
        studentMarks.put("Nithish", 92);
        studentMarks.put("Ajay", 88);
        studentMarks.put("Krish", 74);

        // Display student marks
        System.out.println("Student Marks:");
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // Calculate average marks
        int total = 0;
        for (int marks : studentMarks.values()) {
            total += marks;
        }

        double average = (double) total / studentMarks.size();
        System.out.println("\nAverage Marks: " + average);
    }
}
