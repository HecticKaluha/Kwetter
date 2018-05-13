package model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Kweet")
@Table(name = "kweet")
public class Kweet extends ResourceSupport implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    //look into this
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="PROFILE_OWNER", nullable = false)
    //@JsonBackReference
    private Profile owner;

    private String message;
    private Date postDate;


    //@JsonIgnore
    //private List<Profile> likes;

    //@ManyToMany
    //private List<Mention> mentions;

    //@ManyToMany
    //private List<Trend> trends;

    @JsonCreator
    public Kweet()
    {

    }

    @JsonCreator
    public Kweet(@JsonProperty("owner") Profile owner,@JsonProperty("message") String message,@JsonProperty("postDate") Date postDate,@JsonProperty("likes") List<Profile> likes,@JsonProperty("mentions") List<Mention> mentions,@JsonProperty("trends") List<Trend> trends)
    {
        this (owner,message,postDate);
        /*this.likes = likes;
        this.mentions = mentions;
        this.trends = trends;*/
    }

    @JsonCreator
    public Kweet(@JsonProperty("id") Long id,@JsonProperty("owner") Profile owner,@JsonProperty("message") String message,@JsonProperty("postDate") Date postDate,@JsonProperty("likes") List<Profile> likes,@JsonProperty("mentions") List<Mention> mentions,@JsonProperty("trends") List<Trend> trends)
    {
        this (owner, message, postDate,likes,mentions,trends);
        this.id = id;
    }

    @JsonCreator
    public Kweet(@JsonProperty("owner") Profile owner, @JsonProperty("message") String message, @JsonProperty("postDate") Date postDate)
    {
        if(owner == null)
        {
            throw new IllegalArgumentException("Owner is not defined");
        }
        if(message.isEmpty() || message.length() > 140)
        {
            throw new IllegalArgumentException("Message is not defined/not a value or longer than 140 characters");
        }
        this.owner = owner;
        this.message = message;
        this.postDate = postDate;
    }

    public List<Kweet> getComments()
    {
        //return comments;
        return new ArrayList<>();
    }

    public Profile getOwner()
    {
        return owner;
    }

    public void setOwner(Profile owner)
    {
        this.owner = owner;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Date getPostDate()
    {
        return postDate;
    }

    public void setPostDate(Date postDate)
    {
        this.postDate = postDate;
    }

    public List<Profile> getLikes()
    {
        //return likes;
        return null;
    }

    public void setLikes(List<Profile> likes)
    {
        //this.likes = likes;
    }

    public List<Mention> getMentions()
    {
        //return mentions;
        return null;
    }

    public void setMentions(List<Mention> mentions)
    {
        //this.mentions = mentions;
    }

    public List<Trend> getTrends()
    {
        //return trends;
        return null;
    }

    public void setTrends(List<Trend> trends)
    {
        //this.trends = trends;
    }

    public Long getKweetId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void addComment(String message, String profile)
    {
        //get singleton kwettermanager
        //lookup profile in kwettermanager by username
        //use found profile to create new comment
        //Kweet comment = new Kweet(profile.getUsername(), message, new Date());
        //this.comments.add(comment);
    }
}
