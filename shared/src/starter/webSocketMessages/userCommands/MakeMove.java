package webSocketMessages.userCommands;
import chess.ChessMove;
public class MakeMove extends UserGameCommand {
    private ChessMove move;
    public MakeMove(ChessMove move){
        super(CommandType.MAKE_MOVE);
        this.move = move;
    }

    public ChessMove getMove() {
        return move;
    }

    public void setMove(ChessMove move) {
        this.move = move;
    }
}