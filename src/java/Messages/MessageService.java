/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

/**
 *
 * @author c0600299
 */
public class MessageService {

    @GET
    @Produces("application/json")
    public JsonArray getAll() {
        JsonArrayBuilder json = Json.createArrayBuilder();
        for (String m : MessageController.getMessages()) {
            json.add(m);
        }
        return json.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonArray add(String str) {
        int count = 1;
        JsonObject json = Json.createObjectBuilder()
                .add("id", Json.createObjectBuilder()
                        .add("author", "")
                        .add("title", "")
                        .add("contents", "")
                        .add("senttime", "")
                )
                .build();
        count++;
        return getAll();
    }

//    @PUT
//    @Produces("application/json")
//    public JsonArray edit(String str){
//        
//        
//        
//        
//        return json.build();
//    }
}
