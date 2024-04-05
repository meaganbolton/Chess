package Server;

import chess.*;
import com.google.gson.Gson;
import result.*;
import request.*;
import webSocketMessages.userCommands.MakeMove;
import java.io.*;
import java.net.*;

public class ServerFacade {

    private final String serverUrl;

    public ServerFacade(String url) {
        serverUrl = url;
    }

    public void clear(String auth) {
        var path = "/db";
        try {
            this.makeRequest("DELETE", path, null, auth, BaseResult.class);

        } catch (IOException e) {
            BaseResult base = new BaseResult();
            base.setMessage(e.getMessage());
        }
    }

    public LoginResult sfRegister(String username, String password, String email) {
        RegisterRequest req = new RegisterRequest(username, password, email);
        var path = "/user";
        try {
            return this.makeRequest("POST", path, req, LoginResult.class);
        } catch (IOException e) {
            LoginResult res = new LoginResult();
            res.setMessage(e.getMessage());
            return res;
        }
    }

    public LoginResult sfLogin(String username, String password) {
        LoginRequest req = new LoginRequest(username, password);
        var path = "/session";
        try {
            return this.makeRequest("POST", path, req, LoginResult.class);
        } catch (IOException e) {
            LoginResult res = new LoginResult();
            res.setMessage(e.getMessage());
            return res;
        }
    }

    public BaseResult sfLogout(String athu) {
        var path = "/session";
        try {
            return this.makeRequest("DELETE", path, null, athu, BaseResult.class);
        } catch (IOException e) {
            BaseResult base = new BaseResult();
            base.setMessage(e.getMessage());
            return base;
        }
    }

    public ListGameResult sfListGame(String auth) {
        var path = "/game";
        try {
            return this.makeRequest("GET", path, null, auth, ListGameResult.class);
        } catch (Exception e) {
            ListGameResult res = new ListGameResult();
            res.setMessage(e.getMessage());
            return res;
        }
    }

    public BaseResult sfJoinGame(int gameID, String playerColor, String auth) {
        JoinGameRequest req = new JoinGameRequest(gameID, playerColor);
        var path = "/game";
        try {
            return this.makeRequest("PUT", path, req, auth, BaseResult.class);
        } catch (IOException e) {
            BaseResult res = new BaseResult();
            res.setMessage(e.getMessage());
            return res;
        }
    }

    public CreateGameResult sfCreateGame(String gameName, String auth) {
        CreateGameRequest req = new CreateGameRequest(gameName);
        var path = "/game";
        try {
            return this.makeRequest("POST", path, req, auth, CreateGameResult.class);
        } catch (IOException e) {
            CreateGameResult res = new CreateGameResult();
            res.setMessage(e.getMessage());
            return res;
        }
    }

    private <T> T makeRequest(String method, String path, Object request, Class<T> responseClass) throws IOException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setReadTimeout(10000);
            http.setRequestMethod(method);
            http.setDoOutput(true);
            http.connect();
            writeBody(request, http);
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }

    private <T> T makeRequest(String method, String path, Object request, String auth, Class<T> responseClass) throws IOException {
        try {
            URL url = (new URI(serverUrl + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setReadTimeout(10000);
            http.setRequestMethod(method);
            http.setDoOutput(true);
            http.addRequestProperty("Authorization", auth);
            http.connect();
            writeBody(request, http);
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }

    private static void writeBody(Object request, HttpURLConnection http) throws IOException {
        if (request != null) {
            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws IOException {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            throw new IOException("failure: " + status);
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass == ListGameResult.class || responseClass == MakeMove.class) {
                    response = ChessGames.serialization().fromJson(reader, responseClass);
                    return response;
                }
                response = new Gson().fromJson(reader, responseClass);
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }
}