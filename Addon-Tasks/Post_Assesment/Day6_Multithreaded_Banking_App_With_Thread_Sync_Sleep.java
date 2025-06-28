package Post_Assesment;

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    // Synchronized method to withdraw money safely
    public synchronized void withdraw(int amount) {
        String threadName = Thread.currentThread().getName();
        System.out.println("🔄 " + threadName + " is trying to withdraw ₹" + amount);

        try {
            Thread.sleep(1000); // Simulate delay
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }

        if (amount <= balance) {
            balance -= amount;
            System.out.println("✅ " + threadName + " withdrew ₹" + amount + " | Remaining Balance: ₹" + balance);
        } else {
            System.out.println("❌ " + threadName + " - Insufficient funds! Available Balance: ₹" + balance);
        }

        System.out.println("--------------------------------------------");
    }

    public int getBalance() {
        return balance;
    }
}

class UserThread extends Thread {
    private BankAccount account;
    private int withdrawAmount;

    public UserThread(BankAccount account, int withdrawAmount, String name) {
        super(name); // set thread name
        this.account = account;
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public void run() {
        account.withdraw(withdrawAmount);
    }
}

public class Day6_Multithreaded_Banking_App_With_Thread_Sync_Sleep {

    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount(5000); // Starting balance ₹5000

        // Multiple users trying to withdraw
        Thread user1 = new UserThread(sharedAccount, 2000, "User-Alice");
        Thread user2 = new UserThread(sharedAccount, 1500, "User-Bob");
        Thread user3 = new UserThread(sharedAccount, 2000, "User-Charlie");
        Thread user4 = new UserThread(sharedAccount, 1000, "User-Diana");

        // Start all threads
        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}
