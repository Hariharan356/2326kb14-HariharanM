package Day6;

// Custom thread class extending Thread
class NumberPrinterThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(500); // optional: pause for 0.5 seconds
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class Task1_Thread_Creation_Print_1_To_10 {

    public static void main(String[] args) {
        // Create and start the thread
        NumberPrinterThread thread = new NumberPrinterThread();
        thread.start();
    }
}
