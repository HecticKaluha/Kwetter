package model;

public class Mention
{
    private Kweet containingKweet;
    private Profile mention;

    public Mention(Kweet containingKweet, Profile mention)
    {
        this.containingKweet = containingKweet;
        this.mention = mention;
    }

    public Kweet getContainingKweet()
    {
        return containingKweet;
    }

    public void setContainingKweet(Kweet containingKweet)
    {
        this.containingKweet = containingKweet;
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
