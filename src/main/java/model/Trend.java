package model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Trend
{
    private String trend;
    private Kweet containingKweet;

    public Trend(String trend, Kweet containingKweet)
    {
        this.trend = trend;
        this.containingKweet = containingKweet;
    }

    public String getTrend()
    {
        return trend;
    }

    public void setTrend(String trend)
    {
        this.trend = trend;
    }

    public Kweet getContainingKweet()
    {
        return containingKweet;
    }

    public void setContainingKweet(Kweet containingKweet)
    {
        this.containingKweet = containingKweet;
    }
}
