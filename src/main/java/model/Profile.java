package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    private List<Kweet> ownKweets;

    public Profile(String username)
    {
        this.username = username;
    }

    public Profile(String username, Role role)
    {
        this.username = username;
        this.role = role;
        this.ownKweets = new ArrayList<>();
        this.following = new ArrayList<>();
        this.web = "";
        this.bio = "";
        this.location = "";
        this.profilePictureUrl = "";
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
        this.ownKweets = new ArrayList<>();
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
    public void addFollower(String profileName)
    {
        if(!this.following.contains(profileName))
        {
            this.followers.add(profileName);
        }
    }

    public void addFollowing(String profileName)
    {
        if(!this.following.contains(profileName))
        {
            this.following.add(profileName);
        }
    }

    public boolean addKweet(Kweet kweet)
    {
        return ownKweets.add(kweet);
    }

    public boolean removeKweet(Kweet kweet)
    {
        return ownKweets.remove(kweet);
    }

    public void removeFollowing(String profileName)
    {
        this.following.remove(profileName);
    }
    public void removeFollower(String profileName)
    {
        this.following.remove(profileName);
    }

    public List<Kweet> getLatest(){
        return ownKweets.subList(Math.max(ownKweets.size() - 10, 0), ownKweets.size());
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

    public List<Kweet> getOwnKweetsById()
    {
        return ownKweets;
    }

    public void setOwnKweetsById(List<Kweet> ownKweets)
    {
        this.ownKweets = ownKweets;
    }


}
