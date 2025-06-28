package Day6;

class WorkerThread extends Thread {
    private String taskName;

    public WorkerThread(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " started.");

        try {
            // Simulate work using sleep
            for (int i = 1; i <= 5; i++) {
                System.out.println(taskName + " working... step " + i);
                Thread.sleep(500); // Pause for 0.5 second
            }
        } catch (InterruptedException e) {
            System.out.println(taskName + " was interrupted.");
        }

        System.out.println(taskName + " finished.");
    }
}

public class Task3_Sleep_Join_Using_Thread {

    public static void main(String[] args) {
        System.out.println("Main thread started.");

        // Create two threads
        WorkerThread t1 = new WorkerThread("Thread-1");
        WorkerThread t2 = new WorkerThread("Thread-2");

        // Start first thread
        t1.start();

        try {
            // Main thread waits for t1 to complete
            t1.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting for Thread-1.");
        }

        // Start second thread after t1 is complete
        t2.start();

        try {
            // Wait for t2 to finish
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting for Thread-2.");
        }

        System.out.println("Main thread finished.");
    }
}
