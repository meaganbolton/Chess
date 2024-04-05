package request;
/**
 * The JoinGameRequest class represents a request to join a game with the specified game ID and player color.
 */
public class JoinGameRequest {
    /**
     * Creating an int to be the gameID.
     */
    private int gameID;
    /**
     * Creating a string to be the players color.
     */
    private String playerColor = null;
    /**
     * Constructs a new JoinGameRequest.
     */
    public JoinGameRequest(int gameID, String playerColor){
        this.gameID = gameID;
        this.playerColor = playerColor;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getPlayerColor() {
        return playerColor;
    }

}
