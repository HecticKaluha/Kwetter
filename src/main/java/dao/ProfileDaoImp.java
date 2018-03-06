package dao;

import model.Kweet;
import model.Profile;

import java.util.concurrent.ConcurrentHashMap;

public class ProfileDaoImp implements ProfileDao
{
    private ConcurrentHashMap<Long, Profile> profiles;

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

        return null;
    }

    @Override
    public void createProfile()
    {

    }

    @Override
    public void deleteProfile()
    {

    }

    @Override
    public void updateProfile()
    {

    }
}































