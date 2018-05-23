package websocket;

import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import exceptions.CouldNotRoleBackException;
import model.Kweet;
import service.KweetService;
import service.ProfileService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.StringReader;

@ApplicationScoped
@ServerEndpoint("/kweetsws")
public class WebSocket {


    @Inject
    private SessionHandler sessionHandler;

    @Inject
    private KweetService kweetService;

    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        System.out.println("Error: " + error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            System.out.println("aangekomen op de server");
            JsonReader reader = Json.createReader(new StringReader(message));
            JsonObject jsonMessage = reader.readObject();
            System.out.println("Message: " + jsonMessage.getString("message") + " Sender: " + jsonMessage.getString("profilename"));
            Kweet kweet = kweetService.post(jsonMessage.getString("message"), jsonMessage.getString("profilename"));
            sessionHandler.sendToAllConnectedSessions(sessionHandler.createAddMessage(kweet));
        } catch (CouldNotCreateKweetException e) {
            e.printStackTrace();
        } catch (CouldNotFindProfileException e) {
            e.printStackTrace();
        } catch (CouldNotRoleBackException e) {
            e.printStackTrace();
        }
    }
}
