package request;
/**
 * The RegisterRequest class represents a request to register a new user with the specified username, password, and email.
 */
public class RegisterRequest {
    /**
     * Creating a string to be the password for the register request.
     */
    private String password = null;
    /**
     * Creating a string to be the username for the register request.
     */
    private String username = null;
    /**
     * Creating a string to be the email for the register request.
     */
    private String email = null;
    /**
     * Constructs a new RegisterRequest.
     */
    public RegisterRequest(String username, String password, String email) {
        this.password = password;
        this.username = username;
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

