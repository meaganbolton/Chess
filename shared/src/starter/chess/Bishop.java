package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Bishop extends ChessPieces{
    public Bishop(ChessGame.TeamColor teamColor, PieceType typePiece){
        super(teamColor,typePiece);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int rowAdd = myPosition.getRow() + 1;
        int rowSub = myPosition.getRow() - 1;
        int colAdd = myPosition.getColumn() + 1;
        int colSub = myPosition.getColumn() - 1;
        Set<ChessMove> validMoves = new HashSet<>();
        for (int i = 1; i < 9; i++) {
            if (rowAdd < 9 && colAdd < 9) {
                ChessPosition newPosition = new ChessPositions(rowAdd, colAdd);
                if (board.getPiece(newPosition) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition, null);
                        validMoves.add(move);
                        break;
                    } else {
                        break;
                    }
                }
                rowAdd += 1;
                colAdd += 1;
            }
        }
        for (int k = 1; k < 9; k++) {
            if (rowSub > 0 && colSub > 0) {
                ChessPosition newPosition2 = new ChessPositions(rowSub, colSub);
                if (board.getPiece(newPosition2) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition2, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition2).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition2, null);
                        validMoves.add(move);
                        break;
                    } else {
                        break;
                    }
                }
                rowSub -= 1;
                colSub -= 1;
            }
        }
        rowAdd = myPosition.getRow() + 1;
        rowSub = myPosition.getRow() - 1;
        colAdd = myPosition.getColumn() + 1;
        colSub = myPosition.getColumn() - 1;
        for (int j = 1; j < 9; j++){
            if (colAdd < 9 && rowSub > 0) {
                ChessPosition newPosition3 = new ChessPositions(rowSub, colAdd);
                if (board.getPiece(newPosition3) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition3, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition3).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition3, null);
                        validMoves.add(move);
                        break;
                    } else {
                        break;
                    }
                }
                colAdd += 1;
                rowSub -= 1;
            }
        }
        for (int l = 1; l < 9; l++) {
            if (colSub > 0 && rowAdd < 9) {
                ChessPosition newPosition4 = new ChessPositions(rowAdd, colSub);
                if (board.getPiece(newPosition4) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition4, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition4).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition4, null);
                        validMoves.add(move);
                        break;
                    } else {
                        break;
                    }
                }
                colSub -= 1;
                rowAdd += 1;
            }
        }
            return validMoves;
    }
}
