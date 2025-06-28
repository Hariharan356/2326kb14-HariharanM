package Day3;

class Person {
 private String name;
 private int age;

 public Person(String name, int age) {
     this.name = name;
     this.age = age;
 }

 public String getName() {
     return name;
 }

 public int getAge() {
     return age;
 }
}

class Employee extends Person {
 private double salary;

 public Employee(String name, int age, double salary) {
     super(name, age);
     this.salary = salary;
 }

 public void setSalary(double salary) {
     if (salary >= 0) {
         this.salary = salary;
     } else {
         System.out.println("Salary can't be negative.");
     }
 }

 public double getSalary() {
     return salary;
 }

 public double calculatePF() {
     return salary * 0.12;  // 12% PF deduction
 }

 public double calculateNetSalary() {
     return salary - calculatePF();
 }
}
