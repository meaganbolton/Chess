import Server.ServerFacade;
import models.Game;
import org.junit.jupiter.api.*;
import result.BaseResult;
import result.CreateGameResult;
import result.ListGameResult;
import result.LoginResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testitest2 {
    private void clear() {
        ServerFacade server = new ServerFacade("http://localhost:8080");
        String username = UUID.randomUUID().toString();
        String password = "pass";
        String email = "email";
        LoginResult logres = server.sfRegister(username, password, email);
        server.clear(logres.getAuthToken());
    }
    ServerFacade server = new ServerFacade("http://localhost:8080");

    private LoginResult login() {
        String username = "meagan";
        String password = "pass";
        String email = "email";
        server.sfRegister(username, password, email);;
        return server.sfLogin(username, password);
    }
    @Test
    @DisplayName("Valid test register")
    @Order(1)
    public void successRegister() {
        clear();
        ServerFacade server = new ServerFacade("http://localhost:8080");
        String username = UUID.randomUUID().toString();
        String password = "pass";
        String email = "email";
        LoginResult logres = server.sfRegister(username, password, email);
        Assertions.assertEquals(username, logres.getUsername(), "Username did not match, login unsuccessfull");
    }
    @Test
    @DisplayName("Invalid test register")
    @Order(2)
    public void failureRegister() {
        clear();
        ServerFacade server = new ServerFacade("http://localhost:8080");

        String username = "meagan5";
        String email = "email";
        LoginResult res = server.sfRegister(username, null, email);

        Assertions.assertEquals("failure: 400", res.getMessage(), "Wrong error message");
    }
    @Test
    @DisplayName("Valid test Login")
    @Order(3)
    public void successLogin() {
        clear();
        ServerFacade server = new ServerFacade("http://localhost:8080");
        String username = "meagan";
        String password = "pass";
        String email = "email";
        server.sfRegister(username, password, email);;
        LoginResult logres = server.sfLogin(username, password);
        Assertions.assertEquals("meagan", logres.getUsername(), "Username did not match, login unsuccessfull");

    }
    @Test
    @DisplayName("Invalid test Login")
    @Order(4)
    public void failureLogin() {
        clear();
        ServerFacade server = new ServerFacade("http://localhost:8080");
        String username = "meagan";
        String password = "pass";
        String email = "email";
        server.sfRegister(username, password, email);
        LoginResult logres = server.sfLogin(username, "kuhwekf");
        Assertions.assertEquals("failure: 401", logres.getMessage(), "Wrong error message");
    }
    @Test
    @DisplayName("Valid test Logout")
    @Order(5)
    public void successLogout() {
        clear();
        LoginResult logres = login();
            BaseResult res = server.sfLogout(logres.getAuthToken());
            Assertions.assertEquals(null, res.getMessage(), "Wrong error message");
    }
    @Test
    @DisplayName("Invalid test Logout")
    @Order(6)
    public void failureLogout() {
        clear();
        login();

            BaseResult res = server.sfLogout("ksjflijf");
            Assertions.assertEquals("failure: 401", res.getMessage(), "Wrong error message");
    }
    @Test
    @DisplayName("Valid test Create Game")
    @Order(7)
    public void successCreateGame() {
        clear();
        LoginResult logres = login();

        CreateGameResult creares = server.sfCreateGame("gamie2", logres.getAuthToken());
            Assertions.assertNotNull(creares.getGameID(), "Game ID is null");
    }
    @Test
    @DisplayName("Invalid test Create Game")
    @Order(8)
    public void failureCreateGame() throws IOException {
        clear();
        LoginResult logres = login();
        CreateGameResult res = server.sfCreateGame(null, logres.getAuthToken());
        Assertions.assertEquals("failure: 400", res.getMessage(), "The error is not the right error");
    }
    @Test
    @DisplayName("Valid test List Game")
    @Order(9)
    public void successListGame() {
        clear();
        LoginResult logres = login();
        server.sfCreateGame("gamie3", logres.getAuthToken());
        server.sfCreateGame("gamie4", logres.getAuthToken());
        server.sfCreateGame("gamie5", logres.getAuthToken());
        ListGameResult list = server.sfListGame(logres.getAuthToken());
        Assertions.assertNotNull(list, "The list is null and shouldn't be");
        Assertions.assertEquals(null, list.getMessage(), "Has Error message and shouldn't");

    }
    @Test
    @DisplayName("Invalid test List Game")
    @Order(10)
    public void failureListGame() {
        clear();
        LoginResult logres = login();
        server.sfCreateGame("gamie3", logres.getAuthToken());
        server.sfCreateGame("gamie4", logres.getAuthToken());
        server.sfCreateGame("gamie5", logres.getAuthToken());
            ListGameResult res = server.sfListGame("slifjoeeih");
            Assertions.assertEquals("failure: 401", res.getMessage(), "Has Error message and shouldn't");

    }
    @Test
    @DisplayName("Valid test Join Game")
    @Order(11)
    public void successJoinGame() {
        clear();
        LoginResult logres = login();
        CreateGameResult gameres = server.sfCreateGame("gamie3", logres.getAuthToken());
        server.sfJoinGame(gameres.getGameID(), "WHITE", logres.getAuthToken());
        ListGameResult list = server.sfListGame(logres.getAuthToken());
        ArrayList<Game> games = list.getGames();
        for(Game game : games){
            if(game.getGameID() == gameres.getGameID()){
                Assertions.assertEquals("meagan", game.getWhiteUsername(), "White username is wrong");

            }
        }
    }
    @Test
    @DisplayName("Invalid test Join Game")
    @Order(12)
    public void failureJoinGame() {
        clear();
        LoginResult logres = login();
        server.sfCreateGame("gamie3", logres.getAuthToken());
            BaseResult res = server.sfJoinGame(6796536, "WHITE", logres.getAuthToken());
            Assertions.assertEquals("failure: 400", res.getMessage(), "Error messages are not the same");
    }
}

