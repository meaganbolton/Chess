package webSocketMessages.websocket;
import org.eclipse.jetty.websocket.api.Session;
import java.io.IOException;

public class Connection {
    public String authtoken;
    public Session session;
    public String username;

    public Connection(String authtoken, String username, Session session) {
        this.authtoken = authtoken;
        this.session = session;
        this.username = username;
    }

    public void send(String msg) throws IOException {
        session.getRemote().sendString(msg);
    }
    public String getAuthtoken(){
        return authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
