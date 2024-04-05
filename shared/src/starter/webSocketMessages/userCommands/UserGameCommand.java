package webSocketMessages.userCommands;


/**
 * Represents a command a user can send the server over a websocket
 */
public class UserGameCommand {


    public UserGameCommand(CommandType commandType) {
        this.commandType = commandType;
    }
    public enum CommandType {
        JOIN_PLAYER,
        JOIN_OBSERVER,
        MAKE_MOVE,
        LEAVE,
        RESIGN
    }

    protected CommandType commandType;

    private String authToken;
    private Integer gameID;

    public Integer getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public CommandType getCommandType() {
        return commandType;
    }
}
