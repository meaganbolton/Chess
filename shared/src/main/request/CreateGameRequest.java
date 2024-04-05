package request;

/**
 * The CreateGameRequest class represents a request to create a new game.
 */
public class CreateGameRequest {
    /**
     * Creating a string to be the game name.
     */
    private String gameName = null;
    /**
     * Constructs a new CreateGameRequest.
     */
    public CreateGameRequest(String gameName){
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

}
