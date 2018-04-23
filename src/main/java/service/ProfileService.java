package service;

import dao.KweetDao;
import dao.ProfileDao;
import exceptions.*;
import model.Kweet;
import model.Profile;
import model.UserGroup;
import qualifier.JPA;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.sql.SQLException;
import java.util.List;

@Stateless
public class ProfileService {
    @Inject
    @JPA
    private ProfileDao profileDao;

    @Inject
    @JPA
    private KweetDao kweetDao;

    public boolean followUser(Profile followThisProfile, Profile initialProfile) throws CouldNotFindProfileException, UnableToFollowException {
        return profileDao.followUser(followThisProfile, initialProfile);
    }

    public boolean unfollowUser(Profile unfollowThisProfile, Profile initialProfile) throws UnableToUnFollowException {
        return profileDao.unfollowUser(unfollowThisProfile, initialProfile);
    }

    public Profile findProfile(String username) throws CouldNotFindProfileException {
        return profileDao.findProfile(username);
    }

    public void createProfile(String username, String role, String password) throws ParametersWereEmptyException, AddingToCollectionFailedException, RoleNotFoundException, CouldNotCreateProfileException {

        if (profileDao.roleExists(role)) {
            UserGroup usergroup = getRole(role);
            profileDao.createProfile(username, usergroup, password);
        } else {
            throw new CouldNotCreateProfileException("Profile " + username + " could not be created");
        }
    }

    public void createProfile(Profile profile) throws CouldNotCreateProfileException, AddingToCollectionFailedException {
        if (profileDao.roleExists(profile.getRole().get(0).getName())) {
            profileDao.createProfile(profile);
        } else {
            throw new CouldNotCreateProfileException("Profile " + profile.getUsername() + " could not be created");
        }
    }

    public void deleteProfile(String username) throws CouldNotFindProfileException, ParametersWereEmptyException, AddingToCollectionFailedException {
        profileDao.deleteProfile(username);
    }

    public void updateProfile(String username, String newUsername, String bio, String location, String web) throws CouldNotFindProfileException, ParametersWereEmptyException, CouldNotUpdateProfileException {
        profileDao.updateProfile(username, newUsername, bio, location, web);
    }

    public void updateRole(String username, String newRoleName, String oldRoleName) throws RoleNotFoundException, CouldNotFindProfileException, CouldNotUpdateProfileException {
        profileDao.updateRole(username, newRoleName, oldRoleName);
    }

    public boolean addRole(String rolename) {
        return profileDao.addRole(rolename);
    }


    public UserGroup getRole(String rolename) throws RoleNotFoundException {
        if (profileDao.roleExists(rolename)) {
            UserGroup usergroup = profileDao.getRole(rolename);
            return usergroup;
        } else {
            throw new RoleNotFoundException("role with name " + rolename + " was not found");
        }
    }

    public List<Profile> getFollowing(String username) throws CouldNotFindProfileException, CouldNotGetListException {
        return profileDao.getFollowing(username);
    }

    public List<Profile> getFollower(String username) throws CouldNotFindProfileException, CouldNotGetListException {
        return profileDao.getFollower(username);
    }

    public List<Kweet> getLatest(String username) throws CouldNotGetLatestKweets, CouldNotFetchLatestKweetFromDatabaseException {
        return profileDao.getLatest(username);
    }

    public Kweet getLatestKweet(String username) throws CouldNotFetchLatestKweetFromDatabaseException {
        Kweet kweet = profileDao.getLatestKweet(username);
        return kweet;
    }

    public List<Profile> getAllProfiles() throws CouldNotGetListException {
        return profileDao.getAllProfiles();
    }

    public List<UserGroup> getAllRoles() throws RoleNotFoundException, SQLException {
        return profileDao.getAllRoles();
    }

    public List<Kweet> getTimeline(String username) throws CouldNotFetchLatestKweetFromDatabaseException, CouldNotFindProfileException {
        Profile profile = findProfile(username);
        return profileDao.getTimeline(profile);
    }

    public void authenticate(String login, String password) throws SecurityException {

        try{
            profileDao.authenticate(login, password);
        }
        catch(Exception e){
            throw new SecurityException("Invalid user/password : " + e.getMessage());
        }
    }

    public String issueToken(String login) {
        return profileDao.issueToken(login);
    }

}
