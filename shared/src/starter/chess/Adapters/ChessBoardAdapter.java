package chess.Adapters;

import chess.ChessBoard;
import chess.ChessBoards;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ChessBoardAdapter implements JsonDeserializer<ChessBoard> {
    @Override
    public ChessBoard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return jsonDeserializationContext.deserialize(jsonElement, ChessBoards.class);
    }
}
