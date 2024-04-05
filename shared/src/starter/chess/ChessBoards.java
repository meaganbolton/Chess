package chess;
public class ChessBoards implements ChessBoard{
    private final ChessPiece[][] board = new ChessPiece[8][8];

    public ChessBoards(){
        resetBoard();
    }
    public void addPiece(ChessPosition position, ChessPiece piece) {
        board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    public ChessPiece getPiece(ChessPosition position) {
        return this.board[position.getRow()-1][position.getColumn()-1];
    }


public void resetBoard() {
        for(int i = 0; i < 8; i++){
            for(int k = 0; k < 8; k++){
                board[i][k] = null;
            }
        }
        board[0][0] = new Rook(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        board[0][1] = new Knight(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        board[0][2] = new Bishop(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        board[0][3] = new Queen(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);
        board[0][4] = new King(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);
        board[0][5] = new Bishop(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);
        board[0][6] = new Knight(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);
        board[0][7] = new Rook(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);
        for(int i = 0; i < 8; i++){
            board[1][i] = new Pawn(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
            board[6][i] = new Pawn(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);
        }
        board[7][0] = new Rook(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        board[7][1] = new Knight(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        board[7][2] = new Bishop(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        board[7][3] = new Queen(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);
        board[7][4] = new King(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);
        board[7][5] = new Bishop(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);
        board[7][6] = new Knight(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);
        board[7][7] = new Rook(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);
        }
}
