package dao;

import model.Profile;
import model.Role;

public interface ProfileDao
{
    void followUser(Profile followThisProfile,Profile initialProfile);

    void unfollowUser(Profile unfollowThisProfile, Profile initialProfile);

    Profile findProfile(String username);

    void createProfile(String username, Role role);

    void deleteProfile(String username);

    void updateProfile(String username, String bio, String location, String web);

}

