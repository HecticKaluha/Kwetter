package model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Trend
{
    private String trend;
    @XmlTransient
    private List<Kweet> containingKweets;

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
}
