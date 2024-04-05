package webSocketMessages.userCommands;

import chess.ChessGame;

public class JoinPlayer extends UserGameCommand{
    private ChessGame.TeamColor playerColor;
    public JoinPlayer(ChessGame.TeamColor playerColor){
        super(CommandType.JOIN_PLAYER);
        this.playerColor = playerColor;
    }

    public ChessGame.TeamColor getPlayerColor() {
        return playerColor;
    }

}
