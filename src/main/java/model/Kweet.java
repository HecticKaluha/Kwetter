package model;

import dao.KweetDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kweet
{
    private Long id;
    private String owner;
    private String message;
    private Date postDate;
    private List<String> likes;
    private List<Mention> mentions;
    private List<Trend> trends;


    public Kweet(String owner, String message, Date postDate, List<String> likes, List<Mention> mentions, List<Trend> trends)
    {
        this.owner = owner;
        this.message = message;
        this.postDate = postDate;
        this.likes = likes;
        this.mentions = mentions;
        this.trends = trends;
    }

    public Kweet(Long id, String owner, String message, Date postDate, List<String> likes, List<Mention> mentions, List<Trend> trends)
    {
        this.id = id;
        this.owner = owner;
        this.message = message;
        this.postDate = postDate;
        this.likes = likes;
        this.mentions = mentions;
        this.trends = trends;
    }

    public Kweet(String owner, String message, Date postDate)
    {
        this.owner = owner;
        this.message = message;
        this.postDate = postDate;
    }

    public List<Kweet> getComments()
    {
        //return comments;
        return new ArrayList<>();
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
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
