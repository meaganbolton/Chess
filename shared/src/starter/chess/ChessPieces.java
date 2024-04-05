package chess;

import java.util.Collection;

public class ChessPieces implements ChessPiece {
    private ChessGame.TeamColor color;
    private PieceType type;
    public ChessPieces(ChessGame.TeamColor color, ChessPiece.PieceType type){
        this.color = color;
        this.type = type;
    }
    @Override
    public ChessGame.TeamColor getTeamColor() {
        return color;
    }

    @Override
    public PieceType getPieceType() {
        return type;
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return null;
    }
}
