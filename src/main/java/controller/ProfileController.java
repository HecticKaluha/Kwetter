package controller;


import controller.JsonBodyClasses.*;
import exceptions.*;
import filter.JWTTokenNeeded;
import model.Kweet;
import model.Profile;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import java.util.List;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RequestScoped
@Path("/profile")
@RestController
@RequestMapping("kwetter/api/profile")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileController
{
    @Inject
    private ProfileService profileService;


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LoginProfileBody loginProfileBody) {
        try {

            // Authenticate the user using the credentials provided
            profileService.authenticate(loginProfileBody.getLogin(), loginProfileBody.getPassword());

            // Issue a token for the user
            String token = profileService.issueToken(loginProfileBody.getLogin());

            // Return the token on the response
            return Response.ok("\"" + token + "\"")
                    .header(AUTHORIZATION, token)
                    .build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProfiles()
    {
        try{
            //return Response.ok(username).build();
            List<Profile> profileList = profileService.getAllProfiles();
            return Response.ok(profileList).build();
        }
        catch(/*CouldNotFindProfileException*/ Exception e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{username}")
    @RequestMapping("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@PathVariable("username") @PathParam("username") String username)
    {
        try{
            Profile profile = profileService.findProfile(username);
            profile.removeLinks();
            profile.add(linkTo(methodOn(ProfileController.class).getProfile(username)).withSelfRel().withType("GET"));

            profile.add(linkTo(methodOn(ProfileController.class).deleteProfile(username)).withRel("Delete profile").withType("DELETE"));

            //hoe updates en posts af te handelen?
            FollowProfileBody fpb = new FollowProfileBody();
            fpb.setUsername("andere gebruiker");
            profile.add(linkTo(methodOn(ProfileController.class).followProfile(username, fpb)).withRel("Follow this profile").withType("PUT"));

            UnFollowProfileBody ufpb = new UnFollowProfileBody();
            ufpb.setUsername("andere gebruiker");
            profile.add(linkTo(methodOn(ProfileController.class).unfollowProfile(username, ufpb)).withRel("Unfollow this profile").withType("PUT"));

            profile.add(linkTo(methodOn(ProfileController.class).getProfileLatestKweets(username)).withRel("Get the latest kweets of this profile").withType("GET"));

            profile.add(linkTo(methodOn(ProfileController.class).getProfileLatestKweet(username)).withRel("Get the latest kweet of this profile").withType("GET"));

            profile.add(linkTo(methodOn(ProfileController.class).getProfileFollowers(username)).withRel("Get all he followers of this profile").withType("GET"));

            profile.add(linkTo(methodOn(ProfileController.class).getProfileFollowing(username)).withRel("Get all the profiles this profile is following").withType("GET"));

            profile.add(linkTo(methodOn(ProfileController.class).getTimeLine(username)).withRel("Get the timeline of this profile").withType("GET"));



            return Response.ok(profile).build();
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
        catch(ParametersWereEmptyException | AddingToCollectionFailedException | RoleNotFoundException | CouldNotCreateProfileException e){
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{username}")
    @RequestMapping("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProfile(@PathVariable("username") @PathParam("username") String username)
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
    @RequestMapping("/follow/{usernameToFollow}")
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response followProfile(@PathVariable("usernameToFollow") @PathParam("usernameToFollow") String usernameToFollow, FollowProfileBody profileBody)
    {
        try{
            if(profileService.followUser(profileService.findProfile(usernameToFollow), profileService.findProfile(profileBody.getUsername())))
            {
                return Response.ok().build();
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
    @RequestMapping("/unfollow/{usernameToUnFollow}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response unfollowProfile(@PathVariable("usernameToUnFollow") @PathParam("usernameToUnFollow") String usernameToFollow, UnFollowProfileBody profileBody)
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
    @RequestMapping("/ownkweets/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileLatestKweets(@PathVariable("username") @PathParam("username") String username)
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
    @Path("/mostrecent/{username}")
    @RequestMapping("/mostrecent/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileLatestKweet(@PathVariable("username") @PathParam("username") String username)
    {
        try{
            Kweet kweet = profileService.getLatestKweet(username);
            return Response.ok(kweet).build();
        }
        catch(CouldNotFetchLatestKweetFromDatabaseException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/followers/{username}")
    @RequestMapping("/followers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileFollowers(@PathVariable("username") @PathParam("username") String username)
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
    @RequestMapping("/following/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfileFollowing(@PathVariable("username") @PathParam("username") String username)
    {
        try{
            return Response.ok(profileService.getFollowing(username)).build();
        }
        catch(CouldNotFindProfileException | CouldNotGetListException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/timeline/{username}")
    @RequestMapping("/timeline/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimeLine(@PathVariable("username") @PathParam("username") String username)
    {
        try{
            return Response.ok(profileService.getTimeline(username)).build();
        }
        catch(CouldNotFetchLatestKweetFromDatabaseException | CouldNotFindProfileException e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
