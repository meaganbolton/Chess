package result;


/**
 * The LoginResult class represents the result of a login request, including message, authentication token, and username properties.
 */
public class LoginResult extends BaseResult{
    /**
     * Creating a string to be the authentication.
     */
    private String username = null;
    /**
     * Constructs a new LoginResult.
     */
    private String authToken = null;
    /**
     * Creating a string to be the username.
     */
    public LoginResult() { }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
