package service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dao.KweetDao;
import dao.KweetDaoImp;
import dao.ProfileDao;
import dao.ProfileDaoImp;
import exceptions.AddingToCollectionFailedException;
import exceptions.CouldNotFindProfileException;
import exceptions.ParametersWereEmptyException;
import exceptions.RoleNotFoundException;
import model.Kweet;
import model.Profile;
import model.Role;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class ProfileService
{
    @Inject
    private ProfileDao profileDao;

    @Inject
    private KweetDao kweetDao;

    public void followUser(Profile followThisProfile, Profile initialProfile)
    {
        profileDao.followUser(followThisProfile, initialProfile);
    }
    public void unfollowUser(Profile unfollowThisProfile, Profile initialProfile)
    {
        profileDao.unfollowUser(unfollowThisProfile, initialProfile);
    }
    public Profile findProfile(String username) throws CouldNotFindProfileException
    {
        return profileDao.findProfile(username);
    }
    public void createProfile(String username, Role role)throws ParametersWereEmptyException, AddingToCollectionFailedException
    {
        profileDao.createProfile(username, role);
    }
    public void deleteProfile(String username) throws CouldNotFindProfileException, ParametersWereEmptyException, AddingToCollectionFailedException {
        profileDao.deleteProfile(username);
    }
    public void updateProfile(String username, String newUsername,  String bio, String location, String web) throws CouldNotFindProfileException, ParametersWereEmptyException {
        profileDao.updateProfile(username, newUsername, bio, location, web);
    }
    public void updateRole(String username, String rolename) throws RoleNotFoundException, CouldNotFindProfileException {
        profileDao.updateRole("Klaartje", "admin");
    }
    public boolean addRole(String rolename, boolean canDelete, boolean canPost, boolean  canBlacklist, boolean canLike)
    {
        return profileDao.addRole(rolename, canDelete, canPost, canBlacklist, canLike);
    }
    public Role getRole(String rolename) throws RoleNotFoundException
    {
        return profileDao.getRole(rolename);
    }

    public List<Kweet> getLatest(Long profileId) throws CouldNotFindProfileException {
        return profileDao.getLatest(profileId);
    }
}
