package result;
/**
 * The CreateGameResult class represents the result of a request to create a new game.
 */
public class CreateGameResult extends BaseResult {
    /**
     * Creating an int to be the gameID of the created game.
     */
    private Integer gameID = null;
    private String gameName = null;

    /**
     * Constructs a new CreateGameResult.
     */
    public CreateGameResult(){}

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
