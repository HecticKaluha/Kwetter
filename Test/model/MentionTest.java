package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MentionTest
{

    Mention mention;
    Profile profileInMention;
    private Trend trend;
    private Kweet kweet;
    private List<String> likes;
    private List<Mention> mentions;
    private List<Trend> trends;
    @Before
    public void setUp() throws Exception
    {
        mention = null;
        profileInMention = null;
        kweet = null;
        trend = null;
        likes = new ArrayList<>();
        likes.add("Hans");
        likes.add("Piet");
        mentions = new ArrayList<>();
        trends = new ArrayList<>();
        likes.add("Hans");
        mentions.add(new Mention(kweet, profileInMention));
        trends.add(new Trend("trend", kweet));
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void getContainingKweet()
    {
        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
        profileInMention = new Profile("Hans");
        mention = new Mention(kweet, profileInMention);
        Kweet expected = kweet;
        Kweet actual = mention.getContainingKweet();
        assertThat(expected, is(actual));
    }

    @Test
    public void setContainingKweet()
    {
    }

    @Test
    public void getMention()
    {
        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
        profileInMention = new Profile("Hans");
        mention = new Mention(kweet, profileInMention);
        Profile expected = profileInMention;
        Profile actual = mention.getMention();
        assertThat(expected, is(actual));
    }

    @Test
    public void setMention()
    {
    }
}