package ui;

import webSocketMessages.serverMessages.*;

public interface NotificationHandler {
    void notify(ServerMessage message);
}
