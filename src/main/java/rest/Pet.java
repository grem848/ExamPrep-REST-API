package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facade.Facade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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

    @Path("livingpetslist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLivingPetsJson()
    {
        String json = gson.toJson(f.getAllLivingPets());

        return Response.ok(json).build();
    }

    @Path("peteventday/{year}-{month}-{date}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPetsWithEventsOnGivenDayJson(@PathParam("year") String year, @PathParam("month") String month, @PathParam("date") String date) throws ParseException
    {
        String string_date = date + "-" + month + "-" + year;

        SimpleDateFormat fo = new SimpleDateFormat("dd-MM-yyyy");

        Date d = fo.parse(string_date);
        long ldate = d.getTime();

        String json2 = gson.toJson(f.getAllPetsWithEventsOnGivenDay(new Date(ldate)));

        Date test = new Date(ldate);
        System.out.println(test.toString());
        
        return Response.ok(json2).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content)
    {
    }
}
