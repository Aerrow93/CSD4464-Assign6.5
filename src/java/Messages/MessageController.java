/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import static Messages.MessageService.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 *
 * @author c0600299
 */
@ApplicationScoped
public class MessageController {

    private List<Message> messages = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy:HH-mm-ssSS");
    private int count = 1;

    public MessageController() {
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public JsonArray getAllJson() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : messages) {
            json.add(m.toJson());
        }
        return json.build();
    }

    public JsonObject addJson(JsonObject json) {
        Message newMessage = new Message(json);
        newMessage.setId(count++);
        String result = "";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM messageblog WHERE id = ?");

        } catch (SQLException ex) {

        }
        messages.add(newMessage);
        return newMessage.toJson();
    }

    public Message getById(int id) {
        String result = "";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM messageblog WHERE id = ?");
            for (Message m : messages) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                while (rs.next()) {
                    Message mess = new Message();
                    mess.setTitle(rs.getString("title"));
                    mess.setAuthor(rs.getString("contents"));
                    mess.setContents(rs.getString("author"));
                    String senttime = rs.getString("senttime");
                    try {
                        mess.setSenttime(sdf.parse(senttime));
                    } catch (ParseException ex) {
                        mess.setSenttime(new Date());
                    }
                    return mess;
                }
            }

        } catch (SQLException ex) {

        }
//
//        for (Message m : messages) {
//            if (m.getId() == id) {
//                return m;
//            }
//        }
        return null;
    }

    public JsonObject getByIdJson(int id) {
        Message m = getById(id);
        if (m != null) {
            return getById(id).toJson();
        } else {
            return null;
        }
    }

    public JsonArray getByDate(Date startDate, Date endDate) {
        String result = "";
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM messageblog WHERE senttime BETWEEN startDate && endDate");
            for (Message m : messages) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                while (rs.next()) {
                    Message mess = new Message();
                    mess.setTitle(rs.getString("title"));
                    mess.setAuthor(rs.getString("contents"));
                    mess.setContents(rs.getString("author"));
                    String senttime = rs.getString("senttime");
                    try {
                        mess.setSenttime(sdf.parse(senttime));
                    } catch (ParseException ex) {
                        mess.setSenttime(new Date());
                    }
                }
            }
        } catch (SQLException ex) {

        }
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (Message m : messages) {
            if (m.getSenttime().after(startDate) && m.getSenttime().before(endDate) || m.getSenttime().equals(startDate)
                    || m.getSenttime().equals(endDate)) {
                json.add(m.toJson());
            }
        }
        return json.build();
    }

    public JsonObject editJson(int id, JsonObject json) {
        Message m = getById(id);
        m.setTitle(json.getString("title", ""));
        m.setAuthor(json.getString("author", ""));
        m.setContents(json.getString("contents", ""));
        String senttime = json.getString("senttime", "");
        try {
            m.setSenttime(sdf.parse(senttime));
        } catch (ParseException ex) {
            m.setSenttime(new Date());
        }
        return m.toJson();
    }

    public Boolean deleteById(int id) {
        Message m = getById(id);
        if (m != null) {
            messages.remove(m);
            return true;
        } else {
            return false;
        }
    }
}
