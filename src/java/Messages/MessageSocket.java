///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package Messages;

import java.io.IOException;
import java.io.StringReader;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
//
///**
// *
// * @author c0600299
// */
@ServerEndpoint("/messages")
@ApplicationScoped
public class MessageSocket {
//    
//    @Inject
//    private MessageController msgCtrl;
//    
//    @OnMessage
//    public void receiveMeesage(String contents, Session session) throws IOException{
//        if (!msgCtrl.containsSession(session)){
//            msgCtrl.addSession(session);
//        }
//        
//        JsonObject json = Json.createReader(new StringReader(contents)).readObject();
//        msgCtrl.addMessage(json);
//        
//        for (Session message: msgCtrl.getSessions()){
//            RemoteEndpoint.Basic basic = message.getBasicRemote();
//            String output = Json.createArrayBuilder().add(json).build().toString();
//            basic.sendText(output);
//        }
//    }
//    
//    @OnOpen
//    public void connected(Session session) throws IOException{
//        if (!msgCtrl.containsSession(session)){
//            msgCtrl.addSession(session);
//        }
//        
//        JsonArrayBuilder arr = Json.createArrayBuilder();
//        
//        for(JsonObject m: msgCtrl.getMessages()){
//            arr.add(m);
//        }
//        
//        JsonArray output = arr.build();
//        
//        RemoteEndpoint.Basic basic = session.getBasicRemote();
//        System.out.println("Connected to " + session.getId() + " and sending " + output.toString());
//        basic.sendText(output.toString());
//    }
}
