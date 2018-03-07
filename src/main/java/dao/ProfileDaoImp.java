package dao;

import model.Kweet;
import model.Profile;
import model.Role;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileDaoImp implements ProfileDao
{
    private static ProfileDaoImp instance;
    private ConcurrentHashMap<String, Profile> profiles;

    public static synchronized ProfileDao getProfileDao() {
        if (instance == null) {
            instance = new ProfileDaoImp();
        }
        return instance;
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
        Profile profile = profiles.get(username);
        profile.setBio(bio);
        profile.setLocation(location);
        profile.setWeb(web);
    }
    @Override
    public List<Long> getLatest(Long profileId)
    {
        return profiles.get(profileId).getLatest();
    }
}































