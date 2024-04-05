package webSocketMessages.serverMessages;

import models.Game;

public class LoadGame extends ServerMessage{
    Game game;
    public LoadGame(Game game) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
    }

    @Override
    public Game getGame() {
        return game;
    }
}
