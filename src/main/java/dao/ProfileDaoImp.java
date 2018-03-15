package dao;

import exceptions.AddingToCollectionFailedException;
import exceptions.CouldNotFindProfileException;
import exceptions.ParametersWereEmptyException;
import exceptions.RoleNotFoundException;
import model.Kweet;
import model.Profile;
import model.Role;

import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Stateless
public class ProfileDaoImp implements ProfileDao
{
    private ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Role> roles = new ConcurrentHashMap<>();


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
    public void followUser(Profile followThisProfile, Profile initialProfile)
    {
        initialProfile.addFollowing(followThisProfile.getUsername());
        followThisProfile.addFollower(initialProfile.getUsername());
    }

    @Override
    public void unfollowUser(Profile unfollowThisProfile, Profile initialProfile)
    {
        initialProfile.removeFollowing(unfollowThisProfile.getUsername());
        unfollowThisProfile.removeFollower(initialProfile.getUsername());
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
    public void createProfile(String username, Role role) throws ParametersWereEmptyException, AddingToCollectionFailedException {
        if(username == null || username.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(role == null)
        {
            throw new ParametersWereEmptyException("parameter " + role +" was null or empty");
        }
        try{
            profiles.put(username, new Profile(username, role));
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException(e.getMessage());
        }

    }

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
    public List<Kweet> getLatest(Long profileId) throws CouldNotFindProfileException
    {
        return profiles.get(profileId).getLatest();
    }

    @Override
    public boolean addRole(String rolename, boolean candelete, boolean canpost, boolean canblacklist, boolean canlike)
    {
        if(!roles.containsKey(rolename))
        {
            Role role = new Role(candelete, canpost, canblacklist, canlike);
            roles.put(rolename, role);
        }
        else{
            return false;
        }
        return true;
    }

    @Override
    public Role getRole(String rolename) throws RoleNotFoundException {
        if(!roles.containsKey(rolename))
        {
            throw new RoleNotFoundException("Role "+ rolename +" was not found");
        }
        return roles.get(rolename);
    }

    @Override
    public void updateRole (String username,String roleName) throws RoleNotFoundException, CouldNotFindProfileException
    {
        Profile profile = findProfile(username);
        if(roles.get(username)!= null)
        {
            Role role = roles.get(username);
            profile.setRole(role);
        }
        else
        {
            throw new RoleNotFoundException("No role with the name " + roleName + " found!");
        }
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
}































