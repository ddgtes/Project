import java.util.ArrayList;
import java.util.List;

public class User {

    private int userId;
    private String username;
    private String password;
    private String email;

    public User(int userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void register() {
        System.out.println("User '" + username + "' registered successfully.");
    }

    public void login() {
        System.out.println("Welcome, " + username + "! You are now logged in.");
    }

    public void updateProfile(String newUsername, String newEmail) {
        this.username = newUsername;
        this.email = newEmail;
        System.out.println("Profile updated — username: " + username + ", email: " + email);
    }

    public List<Recipe> viewFavorites() {
        // Returns empty list until favorites feature is fully implemented
        return new ArrayList<>();
    }

    public int getUserId()           { return userId; }
    public void setUserId(int id)    { this.userId = id; }

    public String getUsername()              { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword()              { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail()           { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{id=" + userId + ", username='" + username + "', email='" + email + "'}";
    }
}