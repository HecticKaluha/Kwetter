package controller;


import controller.JsonBodyClasses.CreateProfileBody;
import controller.JsonBodyClasses.DeleteProfileBody;
import controller.JsonBodyClasses.UpdateKweetBody;
import controller.JsonBodyClasses.UpdateProfileBody;
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

    @DELETE
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfile(@PathParam("username") String username)
    {
        try{
            profileService.deleteProfile(username);
            return Response.ok("Profiel met username "+ username+" verwijderd.").build();
        }
        catch(CouldNotFindProfileException | ParametersWereEmptyException | AddingToCollectionFailedException e)
        {
            return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(@PathParam("username") String username, UpdateProfileBody profileBody)
    {
        try{
            profileService.updateProfile(username, profileBody.getNewUsername(), profileBody.getBio(), profileBody.getLocation(), profileBody.getWeb());
            return Response.ok("Profiel met originele username "+ username +" geupdate.").build();
        }
        catch(CouldNotFindProfileException | ParametersWereEmptyException e){
            return Response.status(Response.Status.NOT_MODIFIED).entity(e.getMessage()).build();
        }
    }
}
