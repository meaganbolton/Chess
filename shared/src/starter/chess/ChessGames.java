package chess;

import chess.Adapters.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
public class ChessGames implements ChessGame{
    private boolean kingMoved = false;
    private boolean lwMoved = false;
    private boolean rwMoved = false;
    private boolean lbMoved = false;
    private boolean rbMoved = false;
    private TeamColor teamColor;
    private ChessBoard board;
    public ChessGames(){ // took board out as a parameter
        teamColor = TeamColor.WHITE;
        setBoard(new ChessBoards());
    }
    public TeamColor getTeamTurn() {

        return teamColor;
    }

    public void setTeamTurn(TeamColor team) {
        teamColor = team;
        kingMoved = false;
        rwMoved = false;
        rbMoved = false;
        lbMoved = false;
        lwMoved = false;
    }
    Set<ChessMove> moves3 = new HashSet<>();

    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        moves3.clear();
        if(!kingMoved) {
            castle(startPosition);
        }
        ChessPiece tempPiece = null;
        if(board.getPiece(startPosition) != null) { // if the piece they want does exsist
            ChessPiece piece = board.getPiece(startPosition); // get the piece
            Set<ChessMove> moves = (Set<ChessMove>) piece.pieceMoves(board, startPosition);
            for(ChessMove move: moves){
                if(board.getPiece(move.getEndPosition()) != null){
                    tempPiece = board.getPiece(move.getEndPosition());
                }
                board.addPiece(move.getEndPosition(), piece);
                board.addPiece(move.getStartPosition(), null);
                if(!isInCheck(piece.getTeamColor())){
                    moves3.add(move);
                }
                board.addPiece(move.getEndPosition(), tempPiece);
                board.addPiece(move.getStartPosition(), piece);
                tempPiece = null;
            }
            return moves3;
        }else {
            return null;
        }
    }
    public void castle(ChessPosition startPosition){
        ChessPosition whiteCL = new ChessPositions(1,1);
        ChessPosition whiteCR = new ChessPositions(1,8);
        ChessPosition blackCL = new ChessPositions(8,1);
        ChessPosition blackCR = new ChessPositions(8,8);
        if(board.getPiece(startPosition) == null){
            return;
        }
        ChessPiece piece = board.getPiece(startPosition);
        if(piece.getPieceType() != ChessPiece.PieceType.KING){
            return;
        }
        if(piece.getTeamColor() == TeamColor.WHITE){
          if((startPosition.getRow() != 1 || startPosition.getColumn() != 5) ||
                  ((board.getPiece(whiteCL).getPieceType() != ChessPiece.PieceType.ROOK)
                  || (board.getPiece(whiteCR).getPieceType() != ChessPiece.PieceType.ROOK))){
              return;
          }
            if(board.getPiece(whiteCL).getPieceType() == ChessPiece.PieceType.ROOK){
                for(int i = 2; i < 5; i++){
                    ChessPosition betweenrk = new ChessPositions(1,i);
                    if(board.getPiece(betweenrk) != null){
                        return;
                    }
                }
                if(!lwMoved) {
                    ChessPosition endPosition = new ChessPositions(1, 3);
                    ChessMove move = new ChessMoves(startPosition, endPosition, null);
                    moves3.add(move);
                }
            }
            if(board.getPiece(whiteCR).getPieceType() == ChessPiece.PieceType.ROOK){
                for(int k = 6; k < 8; k++){
                    ChessPosition betweenrk = new ChessPositions(1,k);
                    if(board.getPiece(betweenrk) != null){
                        return;
                    }
                }
                if(!rwMoved){
                ChessPosition endPosition = new ChessPositions(1,7);
                ChessMove move = new ChessMoves(startPosition, endPosition, null);
                moves3.add(move);
                }
            }
        }
        ///////////////////////// THIS IS THE BLACK CASTLE //////////////////
        if(piece.getTeamColor() == TeamColor.BLACK){
            if((startPosition.getRow() != 8 || startPosition.getColumn() != 5) ||
                    ((board.getPiece(blackCL).getPieceType() != ChessPiece.PieceType.ROOK)
                            || (board.getPiece(blackCR).getPieceType() != ChessPiece.PieceType.ROOK))){
                return;
            }
            if(board.getPiece(blackCL).getPieceType() == ChessPiece.PieceType.ROOK){
                for(int i = 2; i < 5; i++){
                    ChessPosition betweenrk = new ChessPositions(8,i);
                    if(board.getPiece(betweenrk) != null){
                        return;
                    }
                }
                if(!lbMoved) {
                    ChessPosition endPosition = new ChessPositions(8, 3);
                    ChessMove move = new ChessMoves(startPosition, endPosition, null);
                    moves3.add(move);
                }
            }
            if(board.getPiece(blackCR).getPieceType() == ChessPiece.PieceType.ROOK){
                for(int k = 6; k < 8; k++){
                    ChessPosition betweenrk = new ChessPositions(8,k);
                    if(board.getPiece(betweenrk) != null){
                        return;
                    }
                }
                if(!rbMoved) {
                    ChessPosition endPosition = new ChessPositions(8, 7);
                    ChessMove move = new ChessMoves(startPosition, endPosition, null);
                    moves3.add(move);
                }
            }
        }
    }

    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece tempPiece = null;
        Collection<ChessMove> moves = validMoves(move.getStartPosition()); // if it does have moves, get them
        if(board.getPiece(move.getStartPosition()).getTeamColor() != getTeamTurn()){
            throw new InvalidMoveException("Invalid move: " + move);
        }
        if(moves.isEmpty() || moves == null) { // used to be moves == null
                   throw new InvalidMoveException("Invalid move: " + move);
               }
        ChessPiece piece = board.getPiece(move.getStartPosition()); // if it does exsist get the piece
        if (!moves.contains(move)) { // if the moves it can make doesn't contain the move theyre trying to make
                    throw new InvalidMoveException("Invalid move: " + move);
                }
                else {
                        if(move.getPromotionPiece() == ChessPiece.PieceType.ROOK) {
                            System.out.println("promotion to rook");
                            board.addPiece(move.getEndPosition(), new Rook(teamColor, ChessPiece.PieceType.ROOK));
                        }
                        else if(move.getPromotionPiece() == ChessPiece.PieceType.QUEEN) {
                            System.out.println("promotion to queen");
                            board.addPiece(move.getEndPosition(), new Queen(teamColor, ChessPiece.PieceType.QUEEN));
                        }
                        else if(move.getPromotionPiece() == ChessPiece.PieceType.BISHOP) {
                            System.out.println("promotion to bishop");
                            board.addPiece(move.getEndPosition(), new Bishop(teamColor, ChessPiece.PieceType.BISHOP));
                        }
                        else if(move.getPromotionPiece() == ChessPiece.PieceType.KNIGHT) {
                            System.out.println("promotion to knight");
                            board.addPiece(move.getEndPosition(), new Knight(teamColor, ChessPiece.PieceType.KNIGHT));
                        } else{
                            if(board.getPiece(move.getEndPosition()) != null){
                                tempPiece = board.getPiece(move.getEndPosition());
                            }
                            board.addPiece(move.getEndPosition(), piece);
                        }
                        if(piece.getPieceType() == ChessPiece.PieceType.KING){
                            move4Castle(move);
                            kingMoved = true;
                        }
            if(piece.getPieceType() == ChessPiece.PieceType.ROOK){
                rookMoves(move);
            }
                    board.addPiece(move.getStartPosition(), null);
                    if(isInCheck(teamColor)){
                        board.addPiece(move.getEndPosition(), tempPiece);
                        board.addPiece(move.getStartPosition(), piece);
                        throw new InvalidMoveException("Invalid move: " + move);
                    }
                    if(getTeamTurn() == TeamColor.WHITE){
                        teamColor = TeamColor.BLACK;
                    }else{
                        teamColor = TeamColor.WHITE;
                    }
        }
    }
    public void rookMoves(ChessMove move){
        if(move.getStartPosition().getRow() == 1 && move.getStartPosition().getColumn() == 1){
            lwMoved = true;
        }
        if(move.getStartPosition().getRow() == 1 && move.getStartPosition().getColumn() == 8){
            rwMoved = true;
        }
        if(move.getStartPosition().getRow() == 8 && move.getStartPosition().getColumn() == 1){
            lbMoved = true;
        }
        if(move.getStartPosition().getRow() == 8 && move.getStartPosition().getColumn() == 8){
            rbMoved = true;
        }
    }
    public void move4Castle(ChessMove move){
        if(move.getStartPosition().getRow() == 1 && move.getStartPosition().getColumn() == 5){
            if(move.getEndPosition().getRow() == 1 && move.getEndPosition().getColumn() == 3){
                ChessPosition whiteCL = new ChessPositions(1,1);
                ChessPosition endPosition2 = new ChessPositions(1,4);
                ChessMove move2 = new ChessMoves(whiteCL, endPosition2, null);
                board.addPiece(move2.getEndPosition(), board.getPiece(whiteCL));
                board.addPiece(move2.getStartPosition(), null);

            }
        }
        if(move.getStartPosition().getRow() == 1 && move.getStartPosition().getColumn() == 5){
            if(move.getEndPosition().getRow() == 1 && move.getEndPosition().getColumn() == 7){
                ChessPosition whiteCR = new ChessPositions(1,8);
                ChessPosition endPosition2 = new ChessPositions(1,6);
                ChessMove move2 = new ChessMoves(whiteCR, endPosition2, null);
                board.addPiece(move2.getEndPosition(), board.getPiece(whiteCR));
                board.addPiece(move2.getStartPosition(), null);

            }
        }
        if(move.getStartPosition().getRow() == 8 && move.getStartPosition().getColumn() == 5){
            if(move.getEndPosition().getRow() == 8 && move.getEndPosition().getColumn() == 3){
                ChessPosition whiteCL = new ChessPositions(8,1);
                ChessPosition endPosition2 = new ChessPositions(8,4);
                ChessMove move2 = new ChessMoves(whiteCL, endPosition2, null);
                board.addPiece(move2.getEndPosition(), board.getPiece(whiteCL));
                board.addPiece(move2.getStartPosition(), null);

            }
        }
        if(move.getStartPosition().getRow() == 8 && move.getStartPosition().getColumn() == 5){
            if(move.getEndPosition().getRow() == 8 && move.getEndPosition().getColumn() == 7){
                ChessPosition whiteCR = new ChessPositions(8,8);
                ChessPosition endPosition2 = new ChessPositions(8,6);
                ChessMove move2 = new ChessMoves(whiteCR, endPosition2, null);
                board.addPiece(move2.getEndPosition(), board.getPiece(whiteCR));
                board.addPiece(move2.getStartPosition(), null);

            }
        }
    }

    public boolean isInCheck(TeamColor teamColor) {
        Set<ChessPosition> opponents = (Set<ChessPosition>) opposingPieces(teamColor); // looking at all the oppnenets position
        if(kingsPosition(teamColor) == null){
            return false;
        }
        for(ChessPosition position : opponents){
            ChessPiece piece = board.getPiece(position); // getting each of the opponents pieces
            Set<ChessMove> moves = (Set<ChessMove>) piece.pieceMoves(board, position); // gettitng moves for each of the opponnets
            if(moves != null) { // if the piece does have moves
                for(ChessMove move : moves) { // go through each of the moves
                    if (move.getEndPosition().getRow() == kingsPosition(teamColor).getRow() &&
                            move.getEndPosition().getColumn() == kingsPosition(teamColor).getColumn()) {
                        return true; // if they can move where the king is you're in check
                    }
                }
            }
        }
        // if none of the piece can move where the king is you're not in check
        return false;
    }

    public boolean isInCheckmate(TeamColor teamColor) {
        ChessPiece tempPiece = null;
        if(!isInCheck(teamColor)){ // if hes not in check can't be checkmate
            return false;
        }
        Set<ChessPosition> turnTeam = (Set<ChessPosition>) turnPieces(teamColor); //getting every piece on turn team
        for(ChessPosition position : turnTeam){
            ChessPiece piece = board.getPiece(position);
            if(piece != null) {
                Set<ChessMove> moves = (Set<ChessMove>) piece.pieceMoves(board, position);

                if (moves != null) {
                    for (ChessMove move : moves) {
                        if (move != null) {
                            if(board.getPiece(move.getEndPosition()) != null){
                                tempPiece = board.getPiece(move.getEndPosition());
                            }
                            board.addPiece(move.getEndPosition(), piece);
                            board.addPiece(move.getStartPosition(), null);
                            if (!isInCheck(teamColor)) {
                                 return false;
                            }
                            board.addPiece(move.getStartPosition(), piece);
                            board.addPiece(move.getEndPosition(), tempPiece);
                            tempPiece = null;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isInStalemate(TeamColor teamColor) {
        if(isInCheck(teamColor) || kingsPosition(teamColor) == null){
            return false;
        }
        Set<ChessPosition> turnTeam = (Set<ChessPosition>) turnPieces(teamColor);
        for(ChessPosition position : turnTeam) {
            ChessPiece piece = board.getPiece(position);
            Set<ChessMove> moves = (Set<ChessMove>) piece.pieceMoves(board, position);
            if (piece != null) {
                for (ChessMove move : moves) {
                    board.addPiece(move.getEndPosition(), piece);
                    board.addPiece(move.getStartPosition(), null);
                    if (!isInCheck(teamColor)) {
                        return false;
                    }
                    board.addPiece(move.getEndPosition(), null);
                    board.addPiece(move.getStartPosition(), piece);
                }
            }
        }
        return true;
    }

    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    public ChessBoard getBoard() {
        return board;
    }
    public ChessPosition kingsPosition(TeamColor teamColor){
        for(int row = 1; row < 9; row++){
            for(int col = 1; col < 9; col++){
                ChessPosition position = new ChessPositions(row, col);
                if(board.getPiece(position) != null){
                    if(board.getPiece(position).getPieceType() == ChessPiece.PieceType.KING && board.getPiece(position).getTeamColor() == teamColor){
                        return position;
                    }
                }
            }
        }
        return null;
    }
    public  Collection<ChessPosition> opposingPieces(TeamColor teamColor){
        Set<ChessPosition> opponentsLocation = new HashSet<>();
        for(int row = 1; row < 9; row++){
            for(int col = 1; col < 9; col++){
                ChessPosition position = new ChessPositions(row, col);
                if(board.getPiece(position) != null){
                    if(board.getPiece(position).getTeamColor() != teamColor){
                        opponentsLocation.add(position);
                    }
                }
            }
        }
        return opponentsLocation;
    }
    public  Collection<ChessPosition> turnPieces(TeamColor teamColor){
        Set<ChessPosition> turnTeamLocation = new HashSet<>();
        for(int row = 1; row < 9; row++){
            for(int col = 1; col < 9; col++){
                ChessPosition position = new ChessPositions(row, col);
                if(board.getPiece(position) != null){
                    if(board.getPiece(position).getTeamColor() == teamColor){
                        turnTeamLocation.add(position);
                    }
                }
            }
        }
        return turnTeamLocation;
    }
    public static Gson serialization(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ChessGame.class, new ChessGameAdapter());
        gsonBuilder.registerTypeAdapter(ChessBoard.class, new ChessBoardAdapter());
        gsonBuilder.registerTypeAdapter(ChessPiece.class, new ChessPieceAdapter());
        gsonBuilder.registerTypeAdapter(ChessMove.class, new ChessMoveAdapter());
        gsonBuilder.registerTypeAdapter(ChessPosition.class, new ChessPositionAdapter());
        return gsonBuilder.create();
    }
}
