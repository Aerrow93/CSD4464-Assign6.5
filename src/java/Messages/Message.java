/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;

/**
 *
 * @author c0600299
 */
public class Message {
    private int id;
    private String title;
    private String contents;
    private String author;
    private Date senttime;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy:HH-mm-ssSS");

    public Message() {
    }

    public Message(JsonObject json){
        id = json.getInt("id");
        title = json.getString("title");
        contents = json.getString("contents");
        author = json.getString("author");
        try {
            senttime = sdf.parse(json.getString("senttime"));
        } catch (ParseException ex) {
            Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getSenttime() {
        return senttime;
    }

    public void setSenttime(Date senttime) {
        this.senttime = senttime;
    }
    
    public JsonObject toJson(){
        JsonObject json = Json.createObjectBuilder()
                .add("id", id)
                        .add("author", author)
                        .add("title", title)
                        .add("contents", contents)
                        .add("senttime", sdf.format(senttime))
                .build();
        return json;
    }
}
