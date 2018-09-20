package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.PetDTO;
import facade.Facade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("pet")
public class Pet
{

    @Context
    private UriInfo context;

    Gson gson;
    Facade f = new Facade(Persistence.createEntityManagerFactory("pu"));

    public Pet()
    {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsJson()
    {
        String json = gson.toJson(f.getAllPets());

        return Response.ok(json).build();
    }

    @Path("petcount")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsAmountJson()
    {
        String json = gson.toJson(f.getTotalAmountOfPets());

        return Response.status(Response.Status.ACCEPTED).entity("{\"petCount\":" + "\"" + json + "\"}").build();

    }

    @Path("petlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsInfoJson()
    {
        String json = gson.toJson(f.getAllPetsInfo());
        
        
        return Response.ok(json).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
