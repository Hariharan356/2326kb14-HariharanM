package Day3;

import java.util.Scanner;

public class Task1_Bank_Account {

    private String owner;
    private double balance;

    public Task1_Bank_Account(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task1_Bank_Account account = new Task1_Bank_Account("User", 1000);

        System.out.println("Welcome " + account.getOwner() + "! Your initial balance is $" + account.getBalance());

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1, 2, or 3): ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter 1, 2, or 3.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    if (scanner.hasNextDouble()) {
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account.deposit(depositAmount);
                    } else {
                        System.out.println("Invalid amount.");
                        scanner.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    if (scanner.hasNextDouble()) {
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine();
                        account.withdraw(withdrawAmount);
                    } else {
                        System.out.println("Invalid amount.");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    System.out.println("Thank you " + account.getOwner() + "! Your final balance is $" + account.getBalance());
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }
}
