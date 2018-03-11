package dao;

import exceptions.CouldNotFindProfileException;
import model.Kweet;
import model.Profile;
import model.Role;

import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Stateless
public class ProfileDaoImp implements ProfileDao
{
    private ConcurrentHashMap<String, Profile> profiles;
    private ConcurrentHashMap<String, Role> roles;


    private ProfileDaoImp() {
        this.initProfileDaoImp();
    }

    public void initProfileDaoImp() {
        profiles = new ConcurrentHashMap<>();
        roles = new ConcurrentHashMap<>();
        addRole("Admin", true, true, true, true);
        addRole("User", false, true, false, true);
        createProfile("Hans", roles.get("Admin") );
        createProfile("Peter", roles.get("Admin"));
        createProfile("Klaartje", roles.get("User"));
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
    public Profile findProfile(String username)
    {
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
    public void updateProfile(String username, String bio, String location, String web)
    {
        Profile profile = findProfile(username);
        profile.setBio(bio);
        profile.setLocation(location);
        profile.setWeb(web);
    }
    @Override
    public List<Kweet> getLatest(Long profileId)
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
    public void updateRole (String username,String roleName) throws NullPointerException
    {
        Profile profile = findProfile(username);
        if(roles.get(username)!= null)
        {
            Role role = roles.get(username);
            profile.setRole(role);
        }
        else
        {
            throw new NullPointerException("No role by that name found!");
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
    public boolean removeKweetFromProfile(String username, Kweet kweet)
    {
        return findProfile(username).removeKweet(kweet);
    }
}































