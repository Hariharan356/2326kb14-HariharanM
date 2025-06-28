package Main_Task;

class TicketCounter {
    private int availableSeats = 5;

    // Synchronized method to book tickets
    public synchronized void bookTicket(String user, int seatsRequested) {
        System.out.println(user + " is trying to book " + seatsRequested + " seat(s)...");

        // Simulate processing delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(user + " was interrupted.");
        }

        if (seatsRequested <= availableSeats) {
            availableSeats -= seatsRequested;
            System.out.println("✅ " + user + " successfully booked " + seatsRequested + " seat(s).");
        } else {
            System.out.println("❌ Sorry " + user + ", not enough seats available. Remaining: " + availableSeats);
        }

        System.out.println("🎫 Remaining seats: " + availableSeats);
        System.out.println("---------------------------------");
    }
}

class User extends Thread {
    private String userName;
    private int seatsToBook;
    private TicketCounter counter;

    public User(String userName, int seatsToBook, TicketCounter counter) {
        this.userName = userName;
        this.seatsToBook = seatsToBook;
        this.counter = counter;
    }

    @Override
    public void run() {
        counter.bookTicket(userName, seatsToBook);
    }
}

public class Day6_Ticket_Booking_Simulation_With_Thread_Sync_Sleep {

    public static void main(String[] args) {
        TicketCounter counter = new TicketCounter();

        // Multiple users trying to book tickets
        User user1 = new User("Alice", 2, counter);
        User user2 = new User("Bob", 1, counter);
        User user3 = new User("Charlie", 3, counter);
        User user4 = new User("Diana", 1, counter);

        // Start all threads
        user1.start();
        user2.start();
        user3.start();
        user4.start();
    }
}
