package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
//@Entity(name = "Mention")
//@Table(name = "mention")
public class Mention implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @XmlTransient
    @JsonIgnore
    private List<Kweet> containingKweet;
    private Profile mention;

    public Mention()
    {

    }
    public Mention(List<Kweet> containingKweet, Profile mention)
    {
        this.containingKweet = containingKweet;
        this.mention = mention;
    }

    public List<Kweet> getContainingKweet()
    {
        return containingKweet;
    }

    public void setContainingKweet(Kweet containingKweet)
    {
        this.containingKweet.add(containingKweet);
    }

    public Profile getMention()
    {
        return mention;
    }

    public void setMention(Profile mention)
    {
        this.mention = mention;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
