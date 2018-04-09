package dao;

import exceptions.*;
import model.Kweet;
import model.Profile;
import model.UserGroup;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Stateless
public class ProfileDaoImp implements ProfileDao
{
    private ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
    //private ConcurrentHashMap<String, Role> roles = new ConcurrentHashMap<>();


    private ProfileDaoImp() {
        this.initProfileDaoImp();
    }

    public void initProfileDaoImp() {
//        profiles = new ConcurrentHashMap<>();
//        roles = new ConcurrentHashMap<>();
//        addRole("Admin", true, true, true, true);
//        addRole("User", false, true, false, true);
//        createProfile("Hans", roles.get("Admin") );
//        createProfile("Peter", roles.get("Admin"));
//        createProfile("Klaartje", roles.get("User"));
    }

    @Override
    public boolean followUser(Profile followThisProfile, Profile initialProfile)
    {
        if(initialProfile.addFollowing(followThisProfile) && followThisProfile.addFollower(initialProfile))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean unfollowUser(Profile unfollowThisProfile, Profile initialProfile)
    {
        if(initialProfile.removeFollowing(unfollowThisProfile) && unfollowThisProfile.removeFollower(initialProfile))
        {
            return true;
        }
        return false;
    }

    @Override
    public Profile findProfile(String username) throws CouldNotFindProfileException
    {
        if(!profiles.containsKey(username))
        {
            throw new CouldNotFindProfileException("Profile with username " + username + " was not found");
        }
        return profiles.get(username);
    }

    @Override
    public void createProfile(String username, UserGroup role, String password) throws ParametersWereEmptyException, AddingToCollectionFailedException {

    }

    @Override
    public void createProfile(Profile profile) {

    }

    /*@Override
    public void createProfile(String username, UserGroup role) throws ParametersWereEmptyException, AddingToCollectionFailedException {
        if(username == null || username.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(role == null)
        {
            throw new ParametersWereEmptyException("parameter " + role +" was null or empty");
        }
        try{
            List<UserGroup> groups = new ArrayList<>();
            groups.add(role);
            profiles.put(username, new Profile(username, groups));
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException(e.getMessage());
        }

    }*/

    @Override
    public boolean deleteProfile(String username) throws CouldNotFindProfileException, ParametersWereEmptyException, AddingToCollectionFailedException {
        if(username == null || username.isEmpty()) {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(!profiles.containsKey(username))
        {
            throw new CouldNotFindProfileException("Profile with username" + username + " was not found");
        }
        try{
            profiles.remove(username);
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateProfile(String username, String newUsername, String bio, String location, String web) throws CouldNotFindProfileException, ParametersWereEmptyException {
        if(username == null || username.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(newUsername == null || newUsername.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + newUsername +" was null or empty");
        }
        if(bio == null || bio.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + bio +" was null or empty");
        }
        if(location == null || location.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + location +" was null or empty");
        }
        if(web == null || web.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + web +" was null or empty");
        }
        Profile profile = findProfile(username);
        profile.setUsername(newUsername);
        profile.setBio(bio);
        profile.setLocation(location);
        profile.setWeb(web);
        profiles.remove(username);
        profiles.put(newUsername, profile);
        return true;
    }
    @Override
    public List<Kweet> getLatest(String username) throws CouldNotGetLatestKweets {
        return profiles.get(username).getLatest();
    }

    @Override
    public boolean addRole(String rolename) {
        return false;
    }

    @Override
    public boolean roleExists(String rolename) {
        return false;
    }


    @Override
    public UserGroup getRole(String rolename) throws RoleNotFoundException {
        /*if(!roles.containsKey(rolename))
        {
            throw new RoleNotFoundException("Role "+ rolename +" was not found");
        }*/
        return null;
    }


    @Override
    public void updateRole(String username, String newRoleName, String oldRoleName) throws RoleNotFoundException, CouldNotFindProfileException, CouldNotUpdateProfileException {

    }

    @Override
    public List<Profile> getFollowing(String username) throws CouldNotFindProfileException, CouldNotGetListException {
        return findProfile(username).getFollowing();
    }

    @Override
    public List<Profile> getFollower(String username) throws CouldNotFindProfileException, CouldNotGetListException {
        return findProfile(username).getFollower();
    }

    @Override
    public boolean addKweetToProfile(
            String username,
            Kweet kweet) throws CouldNotFindProfileException
    {
        if (findProfile(username) == null)
        {
            throw new CouldNotFindProfileException("Profile not found");
        }
        return findProfile(username).addKweet(kweet);
    }
    @Override
    public boolean removeKweetFromProfile(String username, Kweet kweet) throws CouldNotFindProfileException
    {
        return findProfile(username).removeKweet(kweet);
    }

    @Override
    public List<Profile> getAllProfiles() throws CouldNotGetListException {
        return new ArrayList<>();
    }

    @Override
    public List<UserGroup> getAllRoles() {
        return null;
    }
}































