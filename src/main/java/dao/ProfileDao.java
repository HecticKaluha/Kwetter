package dao;

import exceptions.CouldNotFindProfileException;
import exceptions.RoleNotFoundException;
import model.Kweet;
import model.Profile;
import model.Role;

import java.util.List;

public interface ProfileDao
{
    void followUser(Profile followThisProfile,Profile initialProfile);

    void unfollowUser(Profile unfollowThisProfile, Profile initialProfile);

    Profile findProfile(String username) throws CouldNotFindProfileException;

    void createProfile(String username, Role role);

    void deleteProfile(String username);

    void updateProfile(String username, String bio, String location, String web) throws CouldNotFindProfileException;

    List<Kweet> getLatest(Long profileId) throws CouldNotFindProfileException;

    boolean addRole(String rolename, boolean candelete, boolean canpost, boolean canblacklist, boolean canlike);

    Role getRole(String rolename) throws RoleNotFoundException;

    void updateRole(String username, String roleName) throws RoleNotFoundException, CouldNotFindProfileException;

    boolean addKweetToProfile (
            String username,
            Kweet kweet) throws CouldNotFindProfileException;

    boolean removeKweetFromProfile(
            String username,
            Kweet kweet) throws CouldNotFindProfileException;
}

