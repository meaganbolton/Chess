package webSocketMessages.serverMessages;

import com.google.gson.Gson;
import models.Game;

/**
 * Represents a Message the server can send through a WebSocket
 */
public class ServerMessage {
    public enum ServerMessageType{
        LOAD_GAME,
        ERROR,
        NOTIFICATION
    }
    protected ServerMessageType serverMessageType;


    public ServerMessage(ServerMessageType type){
        this.serverMessageType = type;
    }
    private Game game;
    public Game getGame(){
        return game;
    }
    public void setGame(Game game){
        this.game = game;
    }
    public ServerMessageType getServerMessageType() { return this.serverMessageType;}
    public String toString() {
        return new Gson().toJson(this);
    }
}
