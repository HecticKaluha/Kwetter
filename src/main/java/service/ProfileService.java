package service;

import dao.ProfileDao;
import dao.ProfileDaoImp;
import model.Profile;
import model.Role;

import java.util.List;

public class ProfileService
{
    private final ProfileDao profileDao;

    public ProfileService() {
        profileDao = ProfileDaoImp.getProfileDao();
    }

    public void followUser(Profile followThisProfile, Profile initialProfile)
    {
        profileDao.followUser(followThisProfile, initialProfile);
    }
    public void unfollowUser(Profile unfollowThisProfile, Profile initialProfile)
    {
        profileDao.unfollowUser(unfollowThisProfile, initialProfile);
    }
    public Profile findProfile(String username)
    {
        return profileDao.findProfile(username);
    }
    public void createProfile(String username, Role role)
    {
        profileDao.createProfile(username, role);
    }
    public void deleteProfile(String username)
    {
        profileDao.deleteProfile(username);
    }
    public void updateProfile(String username, String bio, String location, String web)
    {
        profileDao.updateProfile(username, bio, location, web);
    }
}
