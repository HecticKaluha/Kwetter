package dao;

import model.Profile;

public interface ProfileDao
{
    void followUser(Profile followThisProfile,Profile initialProfile);

    void unfollowUser(Profile unfollowThisProfile, Profile initialProfile);

    Profile findProfile(String username);

    void createProfile();

    void deleteProfile();

    void updateProfile();

}

