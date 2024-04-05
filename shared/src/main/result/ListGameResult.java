package result;

import models.Game;
import java.util.ArrayList;

/**
 * The ListGameResult class represents the result of a request to list games, including game details such as game ID, white and black usernames, and game name.
 */
public class ListGameResult extends BaseResult{
    /**
     * Creating a list of games.
     */
    private ArrayList<Game> games;
    /**
     * Constructs a new ListGameResult.
     */
    public ListGameResult(){}
    public ArrayList<Game> getGames(){
        return games;
    }
    public void setGames(ArrayList<Game> games){
        this.games = games;
    }
    public void printListGames(){
        System.out.println("games: [");
        for(Game game : games){
            System.out.println("{gameID: " + game.getGameID() + ", whiteUsername: " + game.getWhiteUsername() + ", blackUsername: " + game.getBlackUsername() + ", gameName: " + game.getGameName() + "}");
        }
        System.out.println("]");
    }
    public Game find(Integer gameID){
        for(Game game : games){
            if(game.getGameID() == gameID){
                return game;
            }
        }
        return null;
    }
}
