package chess;

import java.util.*;

public class King extends ChessPieces{

    public King(ChessGame.TeamColor teamColor, PieceType typePiece){
        super(teamColor, typePiece);
    }


    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        Set<ChessMove> validMoves = new HashSet<>();
        for(int i = -1; i <2; i++){
            for(int k = -1; k <2; k++){
                if((row + i > 0 && row + i < 8) && (col+k > 0 && col+k < 8)){
                ChessPosition newPosition = new ChessPositions(row+i, col+k);
                if(board.getPiece(newPosition) == null){
                    ChessMove move = new ChessMoves(myPosition, newPosition, null);
                    validMoves.add(move);
                }else {
                    if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition, null);
                        validMoves.add(move);
                    }
                }
                }
            }
        }
        return validMoves;
    }
}
