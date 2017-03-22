/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.websocket.Session;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 *
 * @author c0600299
 */
public class MessageController {
    
    private List<Session> people = new ArrayList<>();
    private List<JsonObject> messages = new ArrayList<>();

    public MessageController() {
    }
    
    public List<Session> getPeople() {
        return people;
    }

    public void setPeople(List<Session> people) {
        this.people = people;
    }

    public List<JsonObject> getMessages() {
        return messages;
    }

    public void setMessages(List<JsonObject> messages) {
        this.messages = messages;
    }

    Iterable<Session> getSessions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void addMessage(JsonObject json) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void addSession(Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean containsSession(Session session) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
