package controller;


import controller.JsonBodyClasses.CreateProfileBody;
import exceptions.AddingToCollectionFailedException;
import exceptions.CouldNotFindProfileException;
import exceptions.ParametersWereEmptyException;
import exceptions.RoleNotFoundException;
import service.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/profile")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileController
{
    @Inject
    private ProfileService profileService;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@PathParam("username") String username)
    {
        try{
            return Response.ok(profileService.findProfile(username)).build();
        }
        catch(CouldNotFindProfileException e)
        {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProfile(CreateProfileBody profileBody)
    {
        try{
            profileService.createProfile(profileBody.getUsername(), profileService.getRole(profileBody.getRole()));
            return Response.ok("Profiel met username "+ profileBody.getUsername()+" aangemaakt.").build();
        }
        catch(ParametersWereEmptyException | AddingToCollectionFailedException | RoleNotFoundException e){
            return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
        }
    }
    /*@POST
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
    }*/
}
