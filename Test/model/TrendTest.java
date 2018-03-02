package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TrendTest
{

    private Trend trend;
    private Kweet kweet;
    private List<String> likes;
    private List<Mention> mentions;
    private List<Trend> trends;
    @Before
    public void setUp() throws Exception
    {
        kweet = null;
        trend = null;
        likes = new ArrayList<>();
        likes.add("Hans");
        likes.add("Piet");
        mentions = new ArrayList<>();
        trends = new ArrayList<>();
        likes.add("Hans");
        mentions.add(new Mention(kweet, new Profile("Hans")));
        trends.add(new Trend("trend", kweet));
    }

    @After
    public void tearDown() throws Exception
    {
        trend = null;
    }

    @Test
    public void getTrend()
    {
        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
        trend = new Trend("OldSchoolRunescape" , kweet);
        String expected = "OldSchoolRunescape";
        String actual = trend.getTrend();
        assertThat(expected, is(actual));
    }

    @Test
    public void setTrend()
    {
    }

    @Test
    public void getContainingKweet()
    {
        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
        trend = new Trend("OldSchoolRunescape" , kweet);
        Kweet expected = kweet;
        Kweet actual = trend.getContainingKweet();
        assertThat(expected, is(actual));
    }

    @Test
    public void setContainingKweet()
    {
    }
}