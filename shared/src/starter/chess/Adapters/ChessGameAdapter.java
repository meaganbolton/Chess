package chess.Adapters;

import chess.ChessGame;
import chess.ChessGames;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import chess.ChessMove;

import java.io.IOException;
import java.lang.reflect.Type;

public class ChessGameAdapter implements JsonDeserializer<ChessGame> {

    @Override
    public ChessGame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         return jsonDeserializationContext.deserialize(jsonElement, ChessGames.class);
    }
}
