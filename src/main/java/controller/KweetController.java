package controller;

import com.google.gson.Gson;
import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import model.Kweet;
import model.KweetBody;
import service.KweetService;

import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/kweet")
@Produces(MediaType.APPLICATION_JSON)
public class KweetController
{
    @Inject
    private KweetService kweetService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKweet()
    {
        //GenericEntity<List<Kweet>> kweets = new GenericEntity<List<Kweet>>(kweetService.findAll()) {};
        List<Kweet> kweets = kweetService.findAll();
        //String json = new Gson().toJson(kweets);
        return Response.ok(kweets).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(KweetBody kweetBody)
    {
        try
        {
            Kweet kweet = kweetService.post(kweetBody.getMessage(), kweetBody.getUsername());
            return Response.ok(kweet).build();
        }
        catch(CouldNotFindProfileException | CouldNotCreateKweetException e)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

//        try{
//            if(kweetService == null)
//            {
//                return Response.ok("kweetservice is null").build();
//            }
//            if(kweetBody == null) {
//                return Response.status(Response.Status.NOT_FOUND).entity("poep").build();
//            }
//            if() == null)
//            {

//                return Response.ok("response is null").build();
//            }
//            return Response.ok(kweetService.post(kweetBody.getMessage(), kweetBody.getUsername())).build();
//        }
//        catch(Exception e)
//        {
//            return Response.ok(e.getMessage()).build();
//        }
    }

    /*void post(String kweetMessage, Profile profile);
    void update(Long id, String content);
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);*/
}
