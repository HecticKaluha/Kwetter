package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dao.KweetDao;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Kweet implements Serializable
{
    private Long id;
    @JsonIgnore
    private Profile owner;
    private String message;
    private Date postDate;
    @JsonIgnore
    private List<String> likes;
    @JsonIgnore
    private List<Mention> mentions;
    @JsonIgnore
    private List<Trend> trends;


    public Kweet(Profile owner, String message, Date postDate, List<String> likes, List<Mention> mentions, List<Trend> trends)
    {
        this (owner,message,postDate);
        this.likes = likes;
        this.mentions = mentions;
        this.trends = trends;
    }

    public Kweet(Long id, Profile owner, String message, Date postDate, List<String> likes, List<Mention> mentions, List<Trend> trends)
    {
        this (owner, message, postDate,likes,mentions,trends);
        this.id = id;
    }

    public Kweet(Profile owner, String message, Date postDate)
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

    public List<String> getLikes()
    {
        return likes;
    }

    public void setLikes(List<String> likes)
    {
        this.likes = likes;
    }

    public List<Mention> getMentions()
    {
        return mentions;
    }

    public void setMentions(List<Mention> mentions)
    {
        this.mentions = mentions;
    }

    public List<Trend> getTrends()
    {
        return trends;
    }

    public void setTrends(List<Trend> trends)
    {
        this.trends = trends;
    }

    public Long getId()
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
