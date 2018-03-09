package service;

import dao.KweetDao;
import dao.KweetDaoImp;
import dao.ProfileDao;
import dao.ProfileDaoImp;
import model.Kweet;
import model.Profile;
import model.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfileService
{
    private final ProfileDao profileDao;
    //mag ik hier opnieuw instantieren?
    private final KweetDao kweetDao;

    public ProfileService() {
        profileDao = ProfileDaoImp.getProfileDao();
        kweetDao = KweetDaoImp.getKweetDao();
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
    public void updateRole(String username, String rolename)
    {
        profileDao.updateRole("Klaartje", "admin");
    }
    public boolean addRole(String rolename, boolean canDelete, boolean canPost, boolean  canBlacklist, boolean canLike)
    {
        return profileDao.addRole(rolename, canDelete, canPost, canBlacklist, canLike);
    }

    //wellicht moet dit in de profiledaoImp??
    public List<Kweet> getLatest(Long profileid)
    {
        List<Kweet> latestsKweets = new ArrayList<>();
        List<Long> latestKweetIds = profileDao.getLatest(profileid);
        for (Long id : latestKweetIds)
        {
            latestsKweets.add(kweetDao.find(id));
        }

        return latestsKweets;
    }
}
