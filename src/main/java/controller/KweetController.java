package controller;

import controller.JsonBodyClasses.UpdateKweetBody;
import exceptions.*;
import model.Kweet;
import controller.JsonBodyClasses.KweetBody;
import service.KweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response getAllKweets()
    {
        try{
            List<Kweet> kweets = kweetService.findAll();
            return Response.ok(kweets).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(CouldNotGetListException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(KweetBody kweetBody)
    {
        try
        {
            Kweet kweet = kweetService.post(kweetBody.getMessage(), kweetBody.getUsername());
            return Response.ok(kweet).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(CouldNotFindProfileException | CouldNotCreateKweetException | CouldNotRoleBackException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKweet(UpdateKweetBody updateKweetBody)
    {
        try{
            Kweet updatedKweet = kweetService.update(updateKweetBody.getId(), updateKweetBody.getMessage());
            return Response.ok("gelukt").header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(KweetNotFoundException | NoContentToUpdateException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }
    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKweet(@PathParam("id") Long id)
    {
        try
        {
            kweetService.delete(id);
            return Response.ok("Kweet deleted").header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(KweetNotFoundException | CouldNotDeleteKweetException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findKWeet(@PathParam("id") Long id)
    {
        try{
            Kweet kweet = kweetService.find(id);
            return Response.ok(kweet).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
        catch(KweetNotFoundException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }
}
