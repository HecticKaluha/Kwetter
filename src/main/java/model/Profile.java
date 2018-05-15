package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import exceptions.CouldNotGetLatestKweets;
import exceptions.CouldNotGetListException;
import org.eclipse.persistence.annotations.PrivateOwned;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.registry.infomodel.User;
import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Profile")
@Table(name = "profile")
public class Profile extends ResourceSupport implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String username;

    private String password;

    private String bio;

    private String location;

    private String web;

    @JsonIgnore
    @XmlTransient
    @ManyToMany(/*mappedBy = "following", cascade = CascadeType.PERSIST,*/ fetch=FetchType.LAZY)
    private List<Profile> following;

    @JsonIgnore
    @XmlTransient
    @ManyToMany(mappedBy = "following", cascade = CascadeType.PERSIST,/*cascade = CascadeType.PERSIST,*/ fetch=FetchType.LAZY)
    private List<Profile> followers;

    private String profilePictureUrl;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name = "userName", referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name = "groupName", referencedColumnName = "name"))
    @JsonIgnore
    private List<UserGroup> groups = new ArrayList<>();


    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "ownKweets", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @PrivateOwned
    //@JsonManagedReference
    private transient List<Kweet> ownKweets;

    public Profile(){}

    public Profile(String username)
    {
        this.username = username;
    }

    public Profile(String username, String password, List<UserGroup> roles)
    {
        this (username, roles);
        this.password = password;
    }

    public Profile(String username, String password, List<UserGroup> roles, String location, String webUrl, String bio)
    {
        this (username, roles);
        this.password = password;
        this.location = location;
        this.web = webUrl;
        this.bio = bio;
    }

    public Profile(String username, String password, List<UserGroup> roles, String location, String webUrl, String bio, String profilePictureUrl)
    {
        this (username, roles);
        this.password = password;
        this.location = location;
        this.web = webUrl;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
    }


    public Profile(String username, List<UserGroup> roles)
    {
        this.username = username;
        this.groups = roles;
        this.ownKweets = new ArrayList<>();
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.web = "";
        this.bio = "";
        this.location = "";
        this.profilePictureUrl = "";
    }

    public Profile(String username, String bio, String location, String web, List<Profile> following, List<Profile> followers, String profilePictureUrl, List<UserGroup> roles)
    {
        this.username = username;
        this.bio = bio;
        this.location = location;
        this.web = web;
        this.following = following;
        this.followers = followers;
        this.profilePictureUrl = profilePictureUrl;
        this.groups = roles;
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
    @JsonIgnore
    public List<Profile> getFollowing() throws CouldNotGetListException {
        if(this.following == null )
        {
            throw new CouldNotGetListException("List following was empty or null");
        }
        return this.following;
    }
    @JsonIgnore
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

    public List<UserGroup> getRole()
    {
        return groups;
    }

    public void setRole(List<UserGroup> roles)
    {
        this.groups = roles;
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


    public Long getProfileId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<UserGroup> groups) {
        this.groups = groups;
    }

    public void setGroup(UserGroup group)
    {
        for(UserGroup ug: groups)
        {
            ug.getUsers().remove(this);
        }

        groups.clear();

        groups.add(group);

        group.getUsers().add(this);
    }

    public UserGroup getSingleUserGroup()
    {
        if(groups.get(0) != null)
        {
            return groups.get(0);
        }
        return null;
    }
}
