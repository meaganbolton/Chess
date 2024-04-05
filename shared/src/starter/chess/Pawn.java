package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Pawn extends ChessPieces{
    public Set<ChessMove> validMoves = new HashSet<>();

    public Pawn(ChessGame.TeamColor teamColor, PieceType typePiece){
        super(teamColor, typePiece);
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        validMoves.clear();
        if(board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK && myPosition.getRow() == 7){
            ChessPosition newPosition = new ChessPositions(myPosition.getRow()-2, myPosition.getColumn());
            ChessPosition newPosition2 = new ChessPositions(myPosition.getRow()-1, myPosition.getColumn());
            if (board.getPiece(newPosition) == null && board.getPiece(newPosition2) == null) {
                ChessMove move = new ChessMoves(myPosition, newPosition, null);
                validMoves.add(move);
            }
        }
        if(board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE && myPosition.getRow() == 2){
            ChessPosition newPosition = new ChessPositions(myPosition.getRow()+2, myPosition.getColumn());
            ChessPosition newPosition2 = new ChessPositions(myPosition.getRow()+1, myPosition.getColumn());
            if (board.getPiece(newPosition) == null && board.getPiece(newPosition2) == null) {
                ChessMove move = new ChessMoves(myPosition, newPosition, null);
                validMoves.add(move);
            }
        }
        if(board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.BLACK) {
            ChessPosition newPosition = new ChessPositions(myPosition.getRow() - 1, myPosition.getColumn());
            if (board.getPiece(newPosition) == null) {
                if (myPosition.getRow() == 2) {
                    promotion(myPosition, newPosition);
                } else {
                    ChessMove move = new ChessMoves(myPosition, newPosition, null);
                    validMoves.add(move);
                }
            }
            if (myPosition.getRow() - 1 > 0 && myPosition.getColumn() - 1 > 0) {
                ChessPosition upRight = new ChessPositions(myPosition.getRow() - 1, myPosition.getColumn() - 1);
                if (board.getPiece(upRight) != null && board.getPiece(upRight).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    ChessMove move = new ChessMoves(myPosition, upRight, null);
                    if (myPosition.getRow() == 2) {
                        promotion(myPosition, upRight);
                    } else {
                        validMoves.add(move);
                    }
                }
            }
            if (myPosition.getRow() - 1 > 0 && myPosition.getColumn() + 1 < 9) {
                ChessPosition upLeft = new ChessPositions(myPosition.getRow() - 1, myPosition.getColumn() + 1);
                if (board.getPiece(upLeft) != null && board.getPiece(upLeft).getTeamColor() == ChessGame.TeamColor.WHITE) {
                    ChessMove move = new ChessMoves(myPosition, upLeft, null);
                    if (myPosition.getRow() == 2) {
                        promotion(myPosition, upLeft);
                    } else {
                        validMoves.add(move);
                    }
                }
            }
        }
        if(board.getPiece(myPosition).getTeamColor() == ChessGame.TeamColor.WHITE) {
            if (myPosition.getRow() + 1 < 9) {
                ChessPosition newPosition = new ChessPositions(myPosition.getRow() + 1, myPosition.getColumn());
                if (board.getPiece(newPosition) == null) {
                    if (myPosition.getRow() == 7) {
                        promotion(myPosition, newPosition);
                    } else {
                        ChessMove move = new ChessMoves(myPosition, newPosition, null);
                        validMoves.add(move);
                    }
                }
            }
            if (myPosition.getRow() + 1 < 9 && myPosition.getColumn() - 1 > 0) {
                ChessPosition upLeft = new ChessPositions(myPosition.getRow() + 1, myPosition.getColumn() - 1);
                if (board.getPiece(upLeft) != null && board.getPiece(upLeft).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    ChessMove move = new ChessMoves(myPosition, upLeft, null);
                    if (myPosition.getRow() == 7) {
                        promotion(myPosition, upLeft);
                    } else {
                        validMoves.add(move);
                    }
                }
            }
            if (myPosition.getRow() + 1 < 9 && myPosition.getColumn() + 1 < 9) {
                ChessPosition upRight = new ChessPositions(myPosition.getRow() + 1, myPosition.getColumn() + 1);
                if (board.getPiece(upRight) != null && board.getPiece(upRight).getTeamColor() == ChessGame.TeamColor.BLACK) {
                    ChessMove move = new ChessMoves(myPosition, upRight, null);
                    if (myPosition.getRow() == 7) {
                        promotion(myPosition, upRight);
                    } else {
                        validMoves.add(move);
                    }
                }
            }
        }
        return validMoves;
    }
    private void promotion(ChessPosition myPosition, ChessPosition newPosition){
        ChessMove move = new ChessMoves(myPosition, newPosition, PieceType.QUEEN);
        validMoves.add(move);
        ChessMove move1 = new ChessMoves(myPosition, newPosition, PieceType.ROOK);
        validMoves.add(move1);
        ChessMove move2 = new ChessMoves(myPosition, newPosition, PieceType.BISHOP);
        validMoves.add(move2);
        ChessMove move3 = new ChessMoves(myPosition, newPosition, PieceType.KNIGHT);
        validMoves.add(move3);
    }
}
