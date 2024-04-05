package chess.Adapters;

import chess.*;
import com.google.gson.*;

import java.lang.reflect.Type;

public class ChessPieceAdapter implements JsonDeserializer<ChessPiece> {
    @Override
    public ChessPiece deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement pieceType = jsonObject.get("type");
        if (pieceType == null){
            pieceType = jsonObject.get("piece");
        }
        JsonElement colorElement = jsonObject.get("color");
        String teamColor = ((JsonPrimitive) colorElement).getAsString();
        ChessGame.TeamColor color = teamColor.equals("WHITE") ? ChessGame.TeamColor.WHITE : ChessGame.TeamColor.BLACK;
        String pieceString = ((JsonPrimitive) pieceType).getAsString();
        switch (pieceString.toUpperCase()){
            case "ROOK" -> {
                return new Rook(color, ChessPiece.PieceType.ROOK);
            }case "PAWN" -> {
                return new Pawn(color, ChessPiece.PieceType.PAWN);
            }case "QUEEN" -> {
                return new Queen(color, ChessPiece.PieceType.QUEEN);
            }case "KING" -> {
                return new King(color, ChessPiece.PieceType.KING);
            }case "KNIGHT" -> {
                return new Knight(color, ChessPiece.PieceType.KNIGHT);
            }case "BISHOP" -> {
                return new Bishop(color, ChessPiece.PieceType.BISHOP);
            }default -> {
                throw new JsonParseException("Unknown piece type: " + pieceType);
            }
        }
    }
}
