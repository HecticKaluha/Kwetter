package dao;

import exceptions.*;
import model.Kweet;
import model.Profile;
import model.UserGroup;

import java.util.List;

public interface ProfileDao
{
    boolean followUser(Profile followThisProfile,Profile initialProfile) throws CouldNotFindProfileException, UnableToFollowException;

    boolean unfollowUser(Profile unfollowThisProfile, Profile initialProfile) throws UnableToUnFollowException;

    Profile findProfile(String username) throws CouldNotFindProfileException;

    //void createProfile(String username, Role role) throws ParametersWereEmptyException, AddingToCollectionFailedException;

    void createProfile(String username, UserGroup role) throws ParametersWereEmptyException, AddingToCollectionFailedException;

    boolean deleteProfile(String username) throws CouldNotFindProfileException, ParametersWereEmptyException, AddingToCollectionFailedException;

    boolean updateProfile(String username, String newUsername, String bio, String location, String web) throws CouldNotFindProfileException, ParametersWereEmptyException, CouldNotUpdateProfileException;

    List<Kweet> getLatest(String username) throws CouldNotGetLatestKweets, CouldNotFetchLatestKweetFromDatabaseException;

    //boolean addRole(String rolename, boolean candelete, boolean canpost, boolean canblacklist, boolean canlike);

    boolean addRole(String rolename);

    boolean roleExists(String rolename);

    UserGroup getRole(String rolename) throws RoleNotFoundException;

    void updateRole(String username, String roleName) throws RoleNotFoundException, CouldNotFindProfileException, CouldNotUpdateProfileException;

    List<Profile> getFollowing(String username) throws CouldNotFindProfileException, CouldNotGetListException;
    List<Profile> getFollower(String username) throws CouldNotFindProfileException, CouldNotGetListException;

    boolean addKweetToProfile (
            String username,
            Kweet kweet) throws CouldNotFindProfileException;

    boolean removeKweetFromProfile(
            String username,
            Kweet kweet) throws CouldNotFindProfileException;

    List<Profile> getAllProfiles() throws CouldNotGetListException;
}

