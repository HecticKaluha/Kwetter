package model;

import java.util.List;

public class Profile
{
    private String username;
    private String bio;
    private String location;
    private String web;
    private List<String> following;
    private List<String> followers;
    private String profilePictureUrl;
    private Role role;

    public Profile(String username)
    {
        this.username = username;
    }

    public Profile()
    {
        this.username = username;
        this.role = role;
    }

    public Profile(String username, Role role)
    {
        this.username = username;
        this.role = role;
    }

    public Profile(String username, String bio, String location, String web, List<String> following, List<String> followers, String profilePictureUrl, Role role)
    {
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.web = web;
        this.following = following;
        this.followers = followers;
        this.profilePictureUrl = profilePictureUrl;
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getBio()
    {
        return bio;
    }

    public void setBio(String bio)
    {
        this.bio = bio;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getWeb()
    {
        return web;
    }

    public void setWeb(String web)
    {
        this.web = web;
    }

    public List<String> getFollowing()
    {
        return following;
    }

    public void setFollowing(List<String> following)
    {
        this.following = following;
    }

    public List<String> getFollowers()
    {
        return followers;
    }

    public void setFollowers(List<String> followers)
    {
        this.followers = followers;
    }

    public String getProfilePictureUrl()
    {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl)
    {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }
}
