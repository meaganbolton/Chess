package ui;

import chess.ChessGame;
import chess.ChessGames;
import chess.ChessMove;
import com.google.gson.Gson;
import webSocketMessages.serverMessages.Notification;
import webSocketMessages.userCommands.*;
import javax.websocket.*;
import java.net.URI;


public class WSClient extends Endpoint {
    public Session session;
    NotificationHandler notificationHandler;
    private String authToken;
    private Integer gameID;

    public WSClient(NotificationHandler notificationHandler)  {
        try {
            URI uri = new URI("ws://localhost:8080/connect");
            this.notificationHandler = notificationHandler;
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, uri);
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    Notification notification = ChessGames.serialization().fromJson(message, Notification.class);
                    notificationHandler.notify(notification);
                }

            });
        }catch ( Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }
    public void joinObserverWS() {
        UserGameCommand obs = new JoinObserver();
        obs.setGameID(gameID);
        obs.setAuthToken(authToken);
        String command = new Gson().toJson(obs);
        try {
            send(command);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void joinPlayerWS(ChessGame.TeamColor color) {
        JoinPlayer player = new JoinPlayer(color);
        player.setAuthToken(authToken);
        player.setGameID(gameID);
        String command = new Gson().toJson(player);
        try {
            send(command);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void leaveWS() {
        UserGameCommand player = new UserGameCommand(UserGameCommand.CommandType.LEAVE);
        player.setGameID(gameID);
        player.setAuthToken(authToken);
        String command = new Gson().toJson(player);
        try {
            send(command);
            session.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void makeMoveWS(ChessMove move) {
        MakeMove player = new MakeMove(move);
        player.setAuthToken(authToken);
        player.setGameID(gameID);
        String command = new Gson().toJson(player);
        try {
            send(command);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void resignWS() {
        UserGameCommand player = new UserGameCommand(UserGameCommand.CommandType.RESIGN);
        player.setGameID(gameID);
        player.setAuthToken(authToken);
        String command = new Gson().toJson(player);
        try {
            send(command);
            session.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void send(String msg) throws Exception {
        this.session.getBasicRemote().sendText(msg);
    }

    public void onOpen(Session session, EndpointConfig endpointConfig) {
        System.out.println("WebSocket connection opened");
    }
}