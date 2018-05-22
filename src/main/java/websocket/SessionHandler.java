package websocket;

import com.google.gson.Gson;
import controller.JsonBodyClasses.KweetBody;
import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import exceptions.CouldNotGetListException;
import exceptions.CouldNotRoleBackException;
import model.Kweet;
import service.KweetService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class SessionHandler {

    @Inject
    KweetService kweetService;

    private final Set<Session> sessions = new HashSet<>();

    public void addSession(Session session) {
        sessions.add(session);
        try {
            for (Kweet kweet : kweetService.findAll()) {
                String addMessage = createAddMessage(kweet);
                sendToSession(session, addMessage);
            }
        } catch (CouldNotGetListException e) {
            e.printStackTrace();
        }
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }

    public String createAddMessage(Kweet kweet) {
        Gson gson = new Gson();
        String json = gson.toJson(kweet);
        return json;
    }

    public void addKweet(KweetBody kweet) {
        try {
            Kweet newKweet = kweetService.post(kweet.getMessage(), kweet.getUsername());
            String addMessage = createAddMessage(newKweet);
            sendToAllConnectedSessions(addMessage);
        } catch (CouldNotCreateKweetException e) {
            e.printStackTrace();
        } catch (CouldNotFindProfileException e) {
            e.printStackTrace();
        } catch (CouldNotRoleBackException e) {
            e.printStackTrace();
        }
    }


    public void sendToAllConnectedSessions(String message) {
        for (Session session : sessions) {
            sendToSession(session, message);
        }
    }

    private void sendToSession(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            sessions.remove(session);
            System.out.println("error" + ex);
        }
    }

}
