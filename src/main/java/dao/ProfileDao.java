package dao;

import exceptions.*;
import model.Kweet;
import model.Profile;
import model.Role;

import java.util.List;

public interface ProfileDao
{
    boolean followUser(Profile followThisProfile,Profile initialProfile);

    boolean unfollowUser(Profile unfollowThisProfile, Profile initialProfile);

    Profile findProfile(String username) throws CouldNotFindProfileException;

    void createProfile(String username, Role role) throws ParametersWereEmptyException, AddingToCollectionFailedException;

    boolean deleteProfile(String username) throws CouldNotFindProfileException, ParametersWereEmptyException, AddingToCollectionFailedException;

    boolean updateProfile(String username, String newUsername, String bio, String location, String web) throws CouldNotFindProfileException, ParametersWereEmptyException;

    List<Kweet> getLatest(String username) throws CouldNotGetLatestKweets;

    boolean addRole(String rolename, boolean candelete, boolean canpost, boolean canblacklist, boolean canlike);

    Role getRole(String rolename) throws RoleNotFoundException;

    void updateRole(String username, String roleName) throws RoleNotFoundException, CouldNotFindProfileException;

    List<Profile> getFollowing(String username) throws CouldNotFindProfileException, CouldNotGetListException;
    List<Profile> getFollower(String username) throws CouldNotFindProfileException, CouldNotGetListException;

    boolean addKweetToProfile (
            String username,
            Kweet kweet) throws CouldNotFindProfileException;

    boolean removeKweetFromProfile(
            String username,
            Kweet kweet) throws CouldNotFindProfileException;
}

