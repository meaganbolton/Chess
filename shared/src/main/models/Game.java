package models;

import chess.ChessGame;
import chess.ChessGames;
import org.jetbrains.annotations.NotNull;

/**
 * The Game class represents chess game data for the chess application.
 */
public class Game implements Comparable<Game> {
    /**
     * Creating an int to be the game ID.
     */
    private int gameID;
    /**
     * Creating a string to be the whiteUsername.
     */
    private String whiteUsername = null;
    /**
     * Creating a string to be the blackUsername.
     */
    private String blackUsername = null;
    /**
     * Creating a string to be the game name.
     */
    private String gameName;
    private ChessGame game;
    // chess game
    /**
     * Constructs a new Game object with specified gameID, white players username, black players username, and game name.
     *
     * @param gameID The ID number for a game.
   //  * @param whiteUsername The username of the white player in a game.
   //  * @param blackUsername The username of the black player in a game.
     * @param gameName The name of the game.
     */
    public Game(int gameID, String gameName){
        this.gameID = gameID;
        this.gameName = gameName;
        this.game = new ChessGames();
    }
    public int getGameID(){
        return gameID;
    }
    public String getWhiteUsername(){
        return whiteUsername;
    }
    public String getBlackUsername(){
        return blackUsername;
    }
    public String getGameName(){
        return gameName;
    }
    public void setWhiteUsername(String whiteUsername){
        this.whiteUsername = whiteUsername;
    }
    public void setBlackUsername(String blackUsername){
        this.blackUsername = blackUsername;
    }

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }

    @Override
    public int compareTo(@NotNull Game o) {
        return o.gameID - this.gameID;
    }
}
