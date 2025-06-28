package Day5;

import java.util.ArrayList;
import java.util.Iterator;

// Student class (can be inner class or separate file)
class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // toString to print student details
    @Override
    public String toString() {
        return "Student ID: " + id + ", Name: " + name;
    }
}

public class Task1_Student_ArrayList {

    public static void main(String[] args) {
        // Creating an ArrayList of Student
        ArrayList<Student> studentList = new ArrayList<>();

        // Adding students to the list
        studentList.add(new Student(1, "Alice"));
        studentList.add(new Student(2, "Bob"));
        studentList.add(new Student(3, "Charlie"));

        // Display using Iterator
        Iterator<Student> iterator = studentList.iterator();

        System.out.println("Student List:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
