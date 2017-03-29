/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Messages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.sql.*;

/**
 *
 * @author c0600299
 */
@Path("/messages")
@ApplicationScoped
public class MessageService {

    List<Message> messages = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy:HH-mm-ssSS");

    @Inject
    private MessageController msgCtrl;

    /**
     * Utility method used to create a Database Connection
     *
     * @return the Connection object
     * @throws SQLException
     */
    private final static String studentNumber = "c0600299";
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {

        }
        String server = "ipro.lambton.on.ca";
        String username = studentNumber + "-java";
        String password = studentNumber;
        String database = username;
        String jdbc = String.format("jdbc:mysql://%s/%s", server, database);
        return DriverManager.getConnection(jdbc, username, password);
    }

    @GET
    @Produces("application/json")
    public JsonArray getAll() {
        return msgCtrl.getAllJson();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public JsonObject getId(@PathParam("id") int id) { 
        JsonObject json = msgCtrl.getByIdJson(id);
        if (json != null) {
            return json;
        }
        return null;
    }

    @GET
    @Path("{startDate}/{endDate}")
    @Produces("application/json")
    public JsonArray getDateRange(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate) {
        try {
            return msgCtrl.getByDate(sdf.parse(startDate), sdf.parse(endDate));
        } catch (ParseException ex) {
            return null;
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public JsonObject add(JsonObject json) {
        return msgCtrl.addJson(json);
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public JsonObject edit(@PathParam("id") int id, JsonObject json) {
        JsonObject jsonEdit = msgCtrl.editJson(id, json);
        if (jsonEdit != null) {
            return jsonEdit;
        } else {
            return (JsonObject) Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(@PathParam("id") int id) {
        if (msgCtrl.deleteById(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
