package request;

/**
 * The LoginRequest class represents a request to log in with a specified username and password.
 */
public class LoginRequest {
    /**
     * Creating a string to be the username for the login.
     */
    private String username = null;
    /**
     * Creating a string to be the password for the login.
     */
    private String password = null;
    /**
     * Constructs a new LoginRequest.
     */
    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

}
