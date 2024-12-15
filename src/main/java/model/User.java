package src.main.java.model;

public class User extends Person {
    private String username;
    private String password;

    // Constructor with all required fields
    public User(String name, String email, String phoneNumber, String username, String password) {
        super(name, email, phoneNumber); // Call the constructor of Person (if it accepts name, email, phoneNumber)
        this.username = username;
        this.password = password;
    }

    // Getters and Setters for username and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

