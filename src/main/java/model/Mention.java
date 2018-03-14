package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Mention
{

    @XmlTransient
    private List<Kweet> containingKweet;
    private Profile mention;

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
}
