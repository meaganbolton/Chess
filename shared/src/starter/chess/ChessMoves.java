package chess;

import java.util.Objects;

public class ChessMoves implements ChessMove{
    private ChessPosition startPosition;
    private ChessPosition endPosition;
    private ChessPiece.PieceType promotionPiece;
    public ChessMoves(ChessPosition startPosition, ChessPosition endPosition, ChessPiece.PieceType promotionPiece){
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.promotionPiece = promotionPiece;
    }
    public ChessPosition getStartPosition() {
        return startPosition;
    }

    public ChessPosition getEndPosition() {
        return endPosition;
    }

    public ChessPiece.PieceType getPromotionPiece() {
        return promotionPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessMoves that = (ChessMoves) o;
        return Objects.equals(startPosition, that.startPosition) && Objects.equals(endPosition, that.endPosition) && promotionPiece == that.promotionPiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPosition, endPosition, promotionPiece);
    }
}
