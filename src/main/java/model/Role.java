package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/*
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "Role")
@Table(name = "role")
public class Role implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private boolean canDelete;
    private boolean canPost;
    private boolean canBlacklist;
    private boolean canLike;

    public Role(){

    }

    public Role(boolean canDelete, boolean canPost, boolean canBlacklist, boolean canLike)
    {
        this.canDelete = canDelete;
        this.canPost = canPost;
        this.canBlacklist = canBlacklist;
        this.canLike = canLike;
    }

    public boolean getCanDelete()
    {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete)
    {
        this.canDelete = canDelete;
    }

    public boolean getCanPost()
    {
        return canPost;
    }

    public void setCanPost(boolean canPost)
    {
        this.canPost = canPost;
    }

    public boolean getCanBlacklist()
    {
        return canBlacklist;
    }

    public void setCanBlacklist(boolean canBlacklist)
    {
        this.canBlacklist = canBlacklist;
    }

    public boolean getCanLike()
    {
        return canLike;
    }

    public void setCanLike(boolean canLike)
    {
        this.canLike = canLike;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}*/
