package controller;


import controller.JsonBodyClasses.*;
import exceptions.*;
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
            //return Response.ok(username).build();
            return Response.ok(profileService.findProfile(username)).build();
        }
        catch(/*CouldNotFindProfileException*/ Exception e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProfile(CreateProfileBody profileBody)
    {
        try{
            profileService.createProfile(profileBody.getUsername(), profileBody.getRole(), profileBody.getPassword());
            return Response.ok("Profiel met username "+ profileBody.getUsername()+" aangemaakt.").build();
        }
        catch(ParametersWereEmptyException | AddingToCollectionFailedException | RoleNotFoundException | CouldNotFindProfileException e){
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
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
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/follow/{usernameToFollow}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response followProfile(@PathParam("usernameToFollow") String usernameToFollow, FollowProfileBody profileBody)
    {
        try{
            if(profileService.followUser(profileService.findProfile(usernameToFollow), profileService.findProfile(profileBody.getUsername())))
            {
                return Response.ok(profileBody.getUsername() + " volgt nu "+ usernameToFollow).build();
            }
            else{
                throw new UnableToFollowException(profileBody.getUsername() + " was unable to follow " + usernameToFollow);
            }
        }
        catch(CouldNotFindProfileException | UnableToFollowException e){
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/unfollow/{usernameToUnFollow}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response unfollowProfile(@PathParam("usernameToUnFollow") String usernameToFollow, UnFollowProfileBody profileBody)
    {
        try{
            if(profileService.unfollowUser(profileService.findProfile(usernameToFollow), profileService.findProfile(profileBody.getUsername())))
            {
                return Response.ok(profileBody.getUsername() + " volgt nu "+ usernameToFollow + " niet meer").build();
            }
            else{
                throw new UnableToUnFollowException(profileBody.getUsername() + " was unable to unfollow " + usernameToFollow);
            }
        }
        catch(CouldNotFindProfileException | UnableToUnFollowException e){
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
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
        catch(CouldNotFindProfileException | ParametersWereEmptyException | CouldNotUpdateProfileException e){
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/ownkweets/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileLatestKweets(@PathParam("username") String username)
    {
        try{
            return Response.ok(profileService.getLatest(username)).build();
        }
        catch(CouldNotGetLatestKweets | CouldNotFetchLatestKweetFromDatabaseException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/followers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileFollowers(@PathParam("username") String username)
    {
        try{
            return Response.ok(profileService.getFollower(username)).build();
        }
        catch(CouldNotFindProfileException | CouldNotGetListException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/following/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileFollowing(@PathParam("username") String username)
    {
        try{
            return Response.ok(profileService.getFollowing(username)).build();
        }
        catch(CouldNotFindProfileException | CouldNotGetListException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }


}