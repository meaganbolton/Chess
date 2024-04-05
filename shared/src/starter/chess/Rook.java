package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Rook extends ChessPieces {

    public Rook(ChessGame.TeamColor teamColor, PieceType typePiece) {
        super(teamColor, typePiece);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        int row = myPosition.getRow();
        int col = myPosition.getColumn();
        int rowAdd = myPosition.getRow() + 1;
        int rowSub = myPosition.getRow() - 1;
        int colAdd = myPosition.getColumn() + 1;
        int colSub = myPosition.getColumn() - 1;
        Set<ChessMove> validMoves = new HashSet<>();
        for(int i = 1; i < 9; i++) {
            if (rowAdd < 9) {
                ChessPosition newPosition = new ChessPositions(rowAdd, col);
                if (board.getPiece(newPosition) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition, null);
                        validMoves.add(move);
                        break;
                    }else{
                        break;
                    }
                }
                rowAdd += 1;
            }
        }

        for(int k = 1; k < 9; k++) {
            if (rowSub > 0) {
                ChessPosition newPosition2 = new ChessPositions(rowSub, col);
                if (board.getPiece(newPosition2) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition2, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition2).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition2, null);
                        validMoves.add(move);
                        break;
                    }else {
                        break;
                    }
                }
                rowSub -= 1;
            }
        }
        for(int i = 1; i < 9; i++) {
            if (colAdd < 9) {
                ChessPosition newPosition3 = new ChessPositions(row, colAdd);
                if (board.getPiece(newPosition3) == null) {
                    ChessMove move = new ChessMoves(myPosition, newPosition3, null);
                    validMoves.add(move);
                } else {
                    if (board.getPiece(newPosition3).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                        ChessMove move = new ChessMoves(myPosition, newPosition3, null);
                        validMoves.add(move);
                        break;
                    }else{
                         break;
                    }
                }
                colAdd += 1;
            }
        }
        for(int i = 1; i < 9; i++){
                        if (colSub > 0) {
                            ChessPosition newPosition4 = new ChessPositions(row, colSub);
                            if (board.getPiece(newPosition4) == null) {
                                ChessMove move = new ChessMoves(myPosition, newPosition4, null);
                                validMoves.add(move);
                            } else {
                                if (board.getPiece(newPosition4).getTeamColor() != board.getPiece(myPosition).getTeamColor()) {
                                    ChessMove move = new ChessMoves(myPosition, newPosition4, null);
                                    validMoves.add(move);
                                    break;
                                }else{
                                    System.out.println();
                                    break;
                                }
                            }
                            colSub -= 1;
                        }
                    }
        return validMoves;
    }
}
