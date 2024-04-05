package models;
/**
 * The User class represents user data for the chess application.
 */
public class User {
    private String username = null;

    private String password = null;

    private String email = null;
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
