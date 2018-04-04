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
//@Entity(name = "Trend")
//@Table(name = "trend")
public class Trend implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String trend;
    @JsonIgnore
    private List<Kweet> containingKweets;

    public Trend(){

    }

    public Trend(String trend, Kweet containingKweet)
    {
        this.trend = trend;
        this.containingKweets.add(containingKweet);
    }

    public String getTrend()
    {
        return trend;
    }

    public void setTrend(String trend)
    {
        this.trend = trend;
    }

    public List<Kweet> getContainingKweet()
    {
        return containingKweets;
    }

    public void setContainingKweet(List<Kweet> containingKweet)
    {
        this.containingKweets = containingKweet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
