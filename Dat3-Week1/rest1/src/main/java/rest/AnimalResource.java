/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author asgerhs
 */
@Path("animal")
public class AnimalResource {

    static List<Animal> al = new ArrayList();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
         al.add(new Animal("Dog", 2010, "Moo"));
        al.add(new Animal("Cat", 2011, "wuf"));
        al.add(new Animal("Turtle", 2018, "ooooooa"));
        al.add(new Animal("Penguin", 2019, "?????"));
       
    }
    

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first webservice";
    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String randomMethod() {
//        return "hello fuck dig";
        Random ran = new Random();
        Animal ra = al.get(ran.nextInt(al.size()));
        return new Gson().toJson(ra);
    }
    
    
}
