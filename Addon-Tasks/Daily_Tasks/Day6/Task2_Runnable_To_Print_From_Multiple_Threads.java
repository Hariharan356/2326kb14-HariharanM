package Day6;

class MessagePrinter implements Runnable {
    private String message;

    public MessagePrinter(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        // Print the message 5 times
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + message + " (" + i + ")");
            try {
                Thread.sleep(500); // Optional: slow down output for clarity
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " was interrupted!");
            }
        }
    }
}

public class Task2_Runnable_To_Print_From_Multiple_Threads {

    public static void main(String[] args) {
        // Create multiple Runnable objects
        Runnable task1 = new MessagePrinter("Hello from Thread 1");
        Runnable task2 = new MessagePrinter("Greetings from Thread 2");
        Runnable task3 = new MessagePrinter("Hi from Thread 3");

        // Create and start multiple threads
        Thread thread1 = new Thread(task1, "Thread-1");
        Thread thread2 = new Thread(task2, "Thread-2");
        Thread thread3 = new Thread(task3, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
