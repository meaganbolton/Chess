package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Knight extends ChessPieces{
    public Knight(ChessGame.TeamColor teamColor, PieceType typePiece) {
        super(teamColor, typePiece);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Set<ChessMove> validMoves = new HashSet<>();
        int[] dr = {2, 2, 1, 1, -2, -2, -1, -1};
        int[] dr2 = {1, -1, 2, -2, 1,-1, 2, -2};
        for(int i = 0; i < 8; i++) {
            if((myPosition.getRow() + dr[i] > 0 && myPosition.getRow() + dr[i] < 9) && (myPosition.getColumn() + dr2[i] > 0 && myPosition.getColumn() + dr2[i] < 9)) {
                ChessPosition newPosition = new ChessPositions(myPosition.getRow() + dr[i], myPosition.getColumn() + dr2[i]);
                if (board.getPiece(newPosition) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition, null);
                        validMoves.add(move);
                    }
                }
            }
        }
        if(validMoves.isEmpty()){
            return null;
        }
        return validMoves;
    }
}
