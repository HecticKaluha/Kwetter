package dao;

import exceptions.CouldNotFindProfileException;
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
        if(profiles.contains(username))
        {
            throw new CouldNotFindProfileException("Profile with username " + username + " was not found");
        }
        return profiles.get(username);
    }

    @Override
    public void createProfile(String username, Role role)
    {
        profiles.put(username, new Profile(username, role));
    }

    @Override
    public void deleteProfile(String username)
    {
        profiles.remove(username);
    }

    @Override
    public void updateProfile(String username, String bio, String location, String web) throws CouldNotFindProfileException
    {
        Profile profile = findProfile(username);
        profile.setBio(bio);
        profile.setLocation(location);
        profile.setWeb(web);
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
        if(roles.get(rolename) == null)
        {
            throw new RoleNotFoundException("Role was not found");
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































