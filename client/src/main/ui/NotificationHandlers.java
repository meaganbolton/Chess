package ui;

import webSocketMessages.serverMessages.ServerMessage;

public class NotificationHandlers implements NotificationHandler {
    private final ClientMain client;
    public NotificationHandlers(){
      client = new ClientMain(this);

    }
    public void run(){
      client.eval();
    }

    @Override
    public void notify(ServerMessage message) {
        ServerMessage.ServerMessageType type = message.getServerMessageType();
        switch (type) {
            case ERROR -> System.out.println(message);
            case NOTIFICATION -> System.out.println(message);
            case LOAD_GAME -> {System.out.println(new Drawboard(message.getGame().getGame().getBoard(), client.getColor()));
               System.out.println("Please enter one of the commands \"Help\" \"Redraw Chess Board\" \"Leave\" \"Make Move\" \"Resign\" \"Highlight Legal Moves\"");
            System.out.println("Observers Command: \"Help\" \"Redraw Chess Board\" \"Leave\" \"Highlight Legal Moves\" ");}
        }
    }
}
