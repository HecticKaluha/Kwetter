package controller;

import controller.JsonBodyClasses.UpdateKweetBody;
import exceptions.*;
import model.Kweet;
import controller.JsonBodyClasses.KweetBody;
import org.springframework.web.bind.annotation.*;
import service.KweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RequestScoped
@Path("/kweet")
@RestController
@RequestMapping("kwetter/api/kweet")
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
            return Response.ok(kweets).build();
        }
        catch(CouldNotGetListException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
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
            return Response.ok(kweet).build();
        }
        catch(CouldNotFindProfileException | CouldNotCreateKweetException | CouldNotRoleBackException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
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
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{id}")
    @RequestMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKweet(@PathVariable("id") @PathParam("id") Long id)
    {
        try
        {
            kweetService.delete(id);
            return Response.ok("Kweet deleted").build();
        }
        catch(KweetNotFoundException | CouldNotDeleteKweetException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @RequestMapping("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findKweet(@PathVariable("id") @PathParam("id") Long id)
    {
        try{
            Kweet kweet = kweetService.find(id);
            kweet.add(linkTo(methodOn(KweetController.class).findKweet(id)).withSelfRel().withType("POST"));
            kweet.add(linkTo(methodOn(KweetController.class).deleteKweet(id)).withRel("Delete kweet").withType("DELETE"));
            return Response.ok(kweet).build();
        }
        catch(KweetNotFoundException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
