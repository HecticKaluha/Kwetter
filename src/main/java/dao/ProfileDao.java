package dao;

import model.Kweet;
import model.Profile;
import model.Role;

import java.util.HashMap;
import java.util.List;

public interface ProfileDao
{
    void followUser(Profile followThisProfile,Profile initialProfile);

    void unfollowUser(Profile unfollowThisProfile, Profile initialProfile);

    Profile findProfile(String username);

    void createProfile(String username, Role role);

    void deleteProfile(String username);

    void updateProfile(String username, String bio, String location, String web);
    List<Long> getLatest(Long profileId);

}

