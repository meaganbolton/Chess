package webSocketMessages.websocket;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<Integer, List<Connection>> connections = new ConcurrentHashMap<>();

    public void add(Integer gameID, String authtoken, String username, Session session) {
        var connection = new Connection(authtoken, username, session);
        if(connections.containsKey(gameID)) {
            List<Connection> connectionList = connections.get(gameID);
            connectionList.add(connection);
            connections.put(gameID, connectionList);
        } else{
            List<Connection> connectionList = new ArrayList<>();
            connectionList.add(connection);
            connections.put(gameID, connectionList);
        }
    }

    public void remove(String authtoken, Integer gameID) {
        List<Connection> gameClients = connections.get(gameID);
        List<Connection> gameClients2 = new ArrayList<>();
        for(Connection client : gameClients) {
            if (!client.getAuthtoken().equals(authtoken)) {
                gameClients2.add(client);
            }
        }
        connections.put(gameID, gameClients2);
    }
    public Connection find(String username, Integer gameID){
        List<Connection> gameClients = connections.get(gameID);
        for(Connection client : gameClients) {
            if (client.username.equals(username)) {
                return client;
            }
        }
        return null;
    }

    public void broadcast(String authToken, Integer gameID, ServerMessage notification) throws IOException {
        List<Connection> gameClients = connections.get(gameID);
        for(Connection client : gameClients) {
                if (!client.getAuthtoken().equals(authToken)) {
                    client.send(new Gson().toJson(notification));
                }
            }
    }
}