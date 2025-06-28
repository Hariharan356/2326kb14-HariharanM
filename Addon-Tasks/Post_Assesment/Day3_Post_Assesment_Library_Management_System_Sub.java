package Post_Assesment;

import java.util.*;

class Book {
    private String title;
    private boolean isIssued;

    public Book(String title) {
        this.title = title;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }
}

class Library {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(String title) {
        if (!books.containsKey(title)) {
            books.put(title, new Book(title));
            System.out.println("Book added: " + title);
        } else {
            System.out.println("Book already exists.");
        }
    }

    public void removeBook(String title) {
        if (books.containsKey(title)) {
            books.remove(title);
            System.out.println("Book removed: " + title);
        } else {
            System.out.println("Book not found.");
        }
    }

    public void issueBook(String title) {
        Book book = books.get(title);
        if (book == null) {
            System.out.println("Book not found.");
        } else if (book.isIssued()) {
            System.out.println("Book is already issued.");
        } else {
            book.issue();
            System.out.println("Book issued: " + title);
        }
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Library is empty.");
        } else {
            System.out.println("\nBooks in Library:");
            for (Book book : books.values()) {
                String status = book.isIssued() ? "Issued" : "Available";
                System.out.println("- " + book.getTitle() + " (" + status + ")");
            }
        }
    }
}
