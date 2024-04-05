package models;
/**
 * The Authtoken class represents user authentication tokens for the chess application.
 */
public class Authtoken {
    /**
     * Creating a string to be the authentication of the authotoken
     */
    private String authoToken = null;
    /**
     * Creating a string to be the username of the authotoken
     */
    private String username = null;
    /**
     * Constructs a new Authtoken object with the specified authentication token and username.
     *
     * @param authoToken The authentication token.
     * @param username The associated username.
     */
    public Authtoken(String authoToken, String username){
        this.authoToken = authoToken;
        this.username = username;
    }
    public String getAuthoToken(){
        return authoToken;
    }
    public String getUsername(){
        return username;
    }

}
