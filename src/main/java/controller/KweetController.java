package controller;

import controller.JsonBodyClasses.UpdateKweetBody;
import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import exceptions.KweetNotFoundException;
import exceptions.NoContentToUpdateException;
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
        List<Kweet> kweets = kweetService.findAll();
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
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateKweet(UpdateKweetBody updateKweetBody)
    {
        try{
            Kweet updatedKweet = kweetService.update(updateKweetBody.getId(), updateKweetBody.getMessage());
            return Response.ok("gelukt").build();
        }
        catch(KweetNotFoundException | NoContentToUpdateException e)
        {
            return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
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
            return Response.ok("Kweet deleted").build();
        }
        catch(KweetNotFoundException e)
        {
            return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
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
            return Response.ok(kweet).build();
        }
        catch(KweetNotFoundException e)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
