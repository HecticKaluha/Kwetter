package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import exceptions.CouldNotGetLatestKweets;
import exceptions.CouldNotGetListException;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Profile")
@Table(name = "profile")
public class Profile implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String bio;

    private String location;

    private String web;

    @JsonIgnore
    @OneToMany(mappedBy = "following", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    @ElementCollection
    private List<Profile> following;

    @JsonIgnore
    @ElementCollection
    @OneToMany(mappedBy = "follower", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
    private List<Profile> followers;

    private String profilePictureUrl;

    //look into
    @ManyToOne(/*mappedBy = "role",*/ cascade = CascadeType.PERSIST)
    private Role role;

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "ownkweets", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
    //@JsonManagedReference
    private transient List<Kweet> ownKweets;

    public Profile(){}

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
        this.followers = new ArrayList<>();
        this.web = "";
        this.bio = "";
        this.location = "";
        this.profilePictureUrl = "";
    }

    public Profile(String username, String bio, String location, String web, List<Profile> following, List<Profile> followers, String profilePictureUrl, Role role)
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
    public boolean addFollower(Profile profile)
    {
        if(!this.followers.contains(profile))
        {
            this.followers.add(profile);
            return true;
        }
        return false;
    }

    public boolean addFollowing(Profile profile)
    {
        if(!this.following.contains(profile))
        {
            this.following.add(profile);
            return true;
        }
        return false;
    }

    public boolean addKweet(Kweet kweet)
    {
        return ownKweets.add(kweet);
    }

    public boolean removeKweet(Kweet kweet)
    {
        return ownKweets.remove(kweet);
    }

    public boolean removeFollowing(Profile profile)
    {
        if(this.following.contains(profile))
        {
            this.following.remove(profile);
            return true;
        }
        return false;
    }
    public boolean removeFollower(Profile profile)
    {
        if(this.followers.contains(profile))
        {
            this.followers.remove(profile);
            return true;
        }
        return false;
    }

    public List<Profile> getFollowing() throws CouldNotGetListException {
        if(this.following == null )
        {
            throw new CouldNotGetListException("List following was empty or null");
        }
        return this.following;
    }

    public List<Profile> getFollower() throws CouldNotGetListException {
        if(this.followers == null )
        {
            throw new CouldNotGetListException("List followers was empty or null");
        }
        return this.followers;
    }

    @XmlTransient
    @Transient
    @JsonIgnore
    public List<Kweet> getLatest() throws CouldNotGetLatestKweets {
        List<Kweet> kweetsToReturn =  ownKweets.subList(Math.max(ownKweets.size() - 10, 0), ownKweets.size());
        if(kweetsToReturn.isEmpty())
        {
            throw new CouldNotGetLatestKweets("unable to return the latest kweets of " + this.username);
        }
        return kweetsToReturn;
    }

    public void setFollowing(List<Profile> following)
    {
        this.following = following;
    }

    public List<Profile> getFollowers()
    {
        return followers;
    }

    public void setFollowers(List<Profile> followers)
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

    @XmlTransient
    @Transient
    @JsonIgnore
    public void setOwnKweetsById(List<Kweet> ownKweets)
    {
        this.ownKweets = ownKweets;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
