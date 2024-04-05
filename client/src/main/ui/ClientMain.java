package ui;

import java.util.Objects;
import java.util.Scanner;
import Server.ServerFacade;
import chess.*;
import result.*;

public class ClientMain {
    private static Scanner scanner;
    private static ServerFacade server = null;
    private static String serverUrl = "";
    private static boolean loggedIn = false;
    private static String authToken = null;
    private static boolean loop = true;
    private static boolean player = false;
    private static boolean observer = false;
    private static Integer GameID = 0;
    private static WSClient wsClient;
    private ChessGame.TeamColor color;

    private NotificationHandler notificationHandler;


    public ClientMain(NotificationHandler notificationHandler) {
        scanner = new Scanner(System.in);
        serverUrl = "http://localhost:8080";
        server = new ServerFacade(serverUrl);
        this.notificationHandler = notificationHandler;
    }

    public void eval() {
        System.out.println("Welcome to Chess");
        while (loop) {
            System.out.println("Please enter one of the commands \"Help\" \"Quit\" \"Login\" \"Register\"");
            prelogin();
        }
        if (loggedIn) {
            System.out.println("You are now logged in");
            System.out.println("'Tis Chess time");
            while (loggedIn) {
                System.out.println("Please enter one of the commands \"Help\" \"Logout\" \"Create Game\" \"List Games\" \"Join Game\" \"Join Observer\"");
                postlogin();
                while (player) {
                    System.out.println("Please enter one of the commands \"Help\" \"Redraw Chess Board\" \"Leave\" \"Make Move\" \"Resign\" \"Highlight Legal Moves\"");
                    playerOptions();
                }
                while (observer) {
                    System.out.println("Please enter one of the commands \"Help\" \"Redraw Chess Board\" \"Leave\" \"Highlight Legal Moves\"");
                    observerOptions();
                }
            }
        }
    }

    public void prelogin() {
            String command = scanner.nextLine();
                switch (command) {
                    case "Help" -> help();
                    case "Quit" -> quit();
                    case "Login" -> clientLogin();
                    case "Register" -> clientRegister();
                    default -> System.out.println("You didn't use a valid command, try again");
                }
    }
    public void postlogin() {
        String command = scanner.nextLine();
             switch (command) {
                case "Help" -> help();
                case "Logout" -> logoutClient();
                case "Create Game" -> clientCreateGame();
                case "List Games" -> listGame();
                case "Join Game" -> joinGame();
                case "Join Observer" -> joinObserver();
                default -> System.out.println("You didn't put in a valid command, try again");
            }
    }
    public void playerOptions() {
        String command = scanner.nextLine();
        switch (command) {
            case "Help" -> help();
            case "Redraw Chess Board" -> clientRedrawBoard();
            case "Leave" -> clientLeave();
            case "Make Move" -> clientMakeMove();
            case "Resign" -> clientResign();
            case "Highlight Legal Moves" -> clientHighlight();
            default -> System.out.println("You didn't put in a valid command, try again");
        }
    }
    public void observerOptions() {
        String command = scanner.nextLine();
        switch (command) {
            case "Help" -> help();
            case "Redraw Chess Board" -> clientRedrawBoard();
            case "Leave" -> clientLeave();
            case "Highlight Legal Moves" -> clientHighlight();
            default -> System.out.println("You didn't put in a valid command, try again");
        }
    }
    public void clientHighlight(){
        System.out.println("To highlight valid moves please enter the starting row number of the piece you want to move");
        System.out.println("Row number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for row number:");
            scanner.nextLine();
        }
        int sRowNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the starting column letter of the piece you want to move");
        System.out.println("Column letter: ");
        char singleChar = 'a';
        while (scanner.hasNext()) {
            String token = scanner.next();
            // Check if the token is a single character
            if (token.length() == 1) {
                singleChar = token.charAt(0);
                System.out.println("Found character: " + singleChar);
                break;
            } else {
                System.out.println("Not a single character: " + token);
            }
        }
        scanner.nextLine();
        ChessPositions pos = new ChessPositions(sRowNum, lettersToNums(singleChar));
        ListGameResult gameList = server.sfListGame(authToken);
        if(gameList.find(GameID).getGame().getBoard().getPiece(pos).getTeamColor() == gameList.find(GameID).getGame().getTeamTurn()) {
            new Drawboard(gameList.find(GameID).getGame().getBoard(), gameList.find(GameID).getGame().validMoves(pos), gameList.find(GameID).getGame().getBoard().getPiece(pos).getTeamColor());
        }else{
            System.out.println("It is not that teams turn so there are no valid moves for that piece");
        }
    }
    public void clientMakeMove(){
        System.out.println("To make a move please enter the starting row number of the piece you want to move");
        System.out.println("Row number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for row number:");
            scanner.nextLine();
        }
        int sRowNum = scanner.nextInt();
        scanner.nextLine();
        char singleChar = 'a';
        System.out.println("Now please enter the column letter for the starting location of the piece you want to move");
        System.out.println("Column letter: ");
        while (scanner.hasNext()) {
            String token = scanner.next();
            // Check if the token is a single character
            if (token.length() == 1) {
                singleChar = token.charAt(0);
                break;
            } else {
                System.out.println("Not a single character: " + token);
            }
        }
        int sColumnNum = lettersToNums(singleChar);
        scanner.nextLine();
        System.out.println("Now please enter the row number of location you want to move");
        System.out.println("Row number: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer for row number:");
            scanner.nextLine();
        }
        int eRowNum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Now please enter the column letter of location you want to move");
        System.out.println("Column letter: ");
        while (scanner.hasNext()) {
            String token = scanner.next();
            // Check if the token is a single character
            if (token.length() == 1) {
                singleChar = token.charAt(0);
                break;
            } else {
                System.out.println("Not a single character: " + token);
            }
        }
        int eColumnNum = lettersToNums(singleChar);
        scanner.nextLine();
        ChessPositions s_position = new ChessPositions(sRowNum, sColumnNum);
        ChessPositions e_position = new ChessPositions(eRowNum, eColumnNum);
        ChessMoves move = new ChessMoves(s_position, e_position, null); // fix this to add the promotion piece

        wsClient.makeMoveWS(move);
    }
    public void clientRedrawBoard(){
        ListGameResult gameList = server.sfListGame(authToken);
        new Drawboard(gameList.find(GameID).getGame().getBoard());
    }
    public void clientLeave(){
        wsClient.leaveWS();
        observer = false;
        player = false;
    }
    public void clientResign(){
        System.out.println("Are you sure you want to resign, you will lose");
        System.out.println("Enter yes or no");
        System.out.println("Your Answer:");
        String answer = scanner.nextLine();
        if(Objects.equals(answer, "yes")){
            wsClient.resignWS();
            player = false;
            observer = true;
        }else{
            System.out.println("Cool, you'll be staying in the game");
        }
    }

    private void quit() {
        loop = false;
    }

    public void clientLogin() {
        System.out.println("To login please enter you username");
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Now please enter your password");
        System.out.println("Password: ");
        String password = scanner.nextLine();
        LoginResult logres = server.sfLogin(username, password);
        authToken = logres.getAuthToken();
        if(logres.getMessage() == null) {
            loggedIn = true;
            loop = false;
        }
        else{
            System.out.println("Login was unsuccessful " + logres.getMessage());
            System.out.println("Please enter the correct username and password.");
        }
    }
    public void clientRegister() {
        System.out.println("To register please pick a username");
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("To register please pick a password");
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("To register please pick an email");
        System.out.println("Email: ");
        String email = scanner.nextLine();
        LoginResult log = server.sfRegister(username, password, email);
        if(log.getMessage() == null) {
            authToken = log.getAuthToken();
            loggedIn = true;
            loop = false;
        }else{
            System.out.println("Register was unsuccessful " + log.getMessage());
            System.out.println("Please enter the correct username and password.");
        }
    }

    public void clientCreateGame() {
        if(assertSignedIn()) {
            System.out.println("To create a game you'll need to pick a game name");
            System.out.println("Please enter a game name: ");
            String gameName = scanner.nextLine();
            CreateGameResult res = server.sfCreateGame(gameName, authToken);
            if(res.getMessage() == null){
                System.out.println("You've successfully created a new game");
            } else{
                System.out.println("Create game attempt invalid-- " + res.getMessage());
            }
        }
    }

    public void listGame() {
        if(assertSignedIn()) {
            ListGameResult res = server.sfListGame(authToken);
            res.printListGames();
        }
    }
    public void joinGame() {
        if(assertSignedIn()){
            System.out.println("To join a game you'll need to enter the gameID");
            System.out.println("gameID: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer for gameID:");
                scanner.nextLine();
            }
            int gameID = scanner.nextInt();
            GameID = gameID;
            scanner.nextLine();
            System.out.println("To join a game you'll need to pick a color");
            System.out.println("player's color: ");
            String playerColor = scanner.nextLine();
            BaseResult res = server.sfJoinGame(gameID, playerColor, authToken);
            if(res.getMessage() != null){
                System.out.println("Joining a game was unsuccessful-- " + res.getMessage());
            } else{
                player = true;
                try {
                    wsClient = new WSClient(notificationHandler);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                wsClient.setAuthToken(authToken);
                wsClient.setGameID(gameID);
                    if(Objects.equals(playerColor, "WHITE")){
                        color = ChessGame.TeamColor.WHITE;
                        wsClient.joinPlayerWS(color);
                    } if(Objects.equals(playerColor, "BLACK")) {
                        color = ChessGame.TeamColor.BLACK;
                    wsClient.joinPlayerWS(color);
                }
            }
        }
    }

    public void joinObserver() {
        if(assertSignedIn()){
            System.out.println("To observe a game you'll need to enter the gameID");
            System.out.println("gameID: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid integer for gameID:");
                scanner.nextLine(); // Consume the invalid input
            }
            int gameID = scanner.nextInt();
            GameID = gameID;
            scanner.nextLine();
            BaseResult res = server.sfJoinGame(gameID,null, authToken);
            if(res.getMessage() != null){
                System.out.println("Observing a game was unsuccessful-- " + res.getMessage());
            } else{
                observer = true;
                try {
                    wsClient = new WSClient(notificationHandler);
                    wsClient.setAuthToken(authToken);
                    wsClient.setGameID(gameID);
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
                color = ChessGame.TeamColor.WHITE;
                wsClient.joinObserverWS();
            }
        }
    }

    public void logoutClient() {
            if(assertSignedIn()) {
                server.sfLogout(authToken);
                loggedIn = false;
                System.out.println("You've been successfully logged out");
            }
    }


    public static void help() {
        System.out.println("Alright you clown I'm not sure what you're confused by, but the commands you can enter are");
        if (player) {
            System.out.println("\"Help\" \"Redraw Chess Board\" \"Leave\" \"Make Move\" \"Resign\" \"Highlight Legal Moves\"");
            System.out.println("Help-- Gives you a list of all the valid commands you can make");
            System.out.println("Redraw Chess Board-- Will redraw the board for you to see");
            System.out.println("Leave-- Will let you leave the game, you will no longer be able to play or watch");
            System.out.println("Make Move-- Will let you make a move by entering the current position and the destination position");
            System.out.println("Resign-- You give up and the other player wins");
            System.out.println("Highlight Legal Moves-- By putting in the position of the piece you want to move you will see the valid moves for the piece lit up in green");
        }else if (observer) {
            System.out.println("\"Help\" \"Redraw Chess Board\" \"Leave\" \"Highlight Legal Moves\"");
            System.out.println("Help-- Gives you a list of all the valid commands you can make");
            System.out.println("Redraw Chess Board-- Will redraw the board for you to see");
            System.out.println("Leave-- Will let you leave the game, you will no longer be able to play or watch");
            System.out.println("Highlight Legal Moves-- By putting in the position of the piece you want to move you will see the valid moves for the piece lit up in green");
        } else if(loggedIn) {
            System.out.println("\"Help\" \"Logout\" \"Create Game\" \"List Games\" \"Join Game\" \"Join Observer\"");
            System.out.println("Help-- Gives you a list of all the valid commands you can make");
            System.out.println("Logout-- Will log you out");
            System.out.println("Create Game-- Allows you to create a new game of chess");
            System.out.println("List Games-- Will give you a list of all the games in our database");
            System.out.println("Join Game-- Allows you to join a game to play");
            System.out.println("Join Observer-- allows you to watch a game others are playing, you will not be a player");
            System.out.println(" ");
        }else {
            System.out.println("\"Help\" \"Quit\" \"Login\" \"Register\"");
            System.out.println("To play you'll need to register and login");
            System.out.println("Help-- Gives you a list of all the valid commands you can make");
            System.out.println("Quit-- Ends the session");
            System.out.println("Login-- Login in to play or watch a game");
            System.out.println("Register-- You'll need to register in order to login");
            System.out.println(" ");

        }
    }
    public ChessGame.TeamColor getColor(){
        return color;
    }

    private boolean assertSignedIn() {
        if(loggedIn != true) {
            System.out.println("You must sign in");
            return false;
        }
        else return true;
    }
    private int lettersToNums(char letter){
        switch (letter){
            case 'a' -> {
                return 1;
            }case 'b' -> {
                return 2;
            }case 'c' -> {
                return 3;
            }case 'd' -> {
                return 4;
            }case 'e' -> {
                return 5;
            }case 'f' -> {
                return 6;
            }case 'g' -> {
                return 7;
            }case 'h' -> {
                return 8;
            }
        }
        return 0;
    }
}