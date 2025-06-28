package main.java.Restaurent_Ordering_System.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    // Full constructor
    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // ✅ Additional constructor for direct login (e.g., client)
    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = "";  // default/empty password
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
