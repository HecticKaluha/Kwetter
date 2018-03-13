package model;

import java.util.List;

public class Mention
{

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
