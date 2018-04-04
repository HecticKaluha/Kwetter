//package model;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
//public class KweetTest
//{
//    private Kweet kweet;
//    private List<String> likes;
//    private List<Mention> mentions;
//    private List<Trend> trends;
//    @Before
//    public void setUp() throws Exception
//    {
//        kweet = null;
//        likes = new ArrayList<>();
//        likes.add("Hans");
//        likes.add("Piet");
//        mentions = new ArrayList<>();
//        trends = new ArrayList<>();
//        likes.add("Hans");
//        mentions.add(new Mention(kweet, new Profile("Hans")));
//        trends.add(new Trend("trend", kweet));
//    }
//
//    @After
//    public void tearDown() throws Exception
//    {
//    }
//
//    @Test
//    public void getId()
//    {
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
//        Long expected = 1L;
//        Long actual = kweet.getId();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setId()
//    {
//    }
//
//    @Test
//    public void getOwner()
//    {
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
//        String expected = "Hans";
//        String actual = kweet.getOwner();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setOwner()
//    {
//
//    }
//
//    @Test
//    public void getMessage()
//    {
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
//        String expected = "Dit is een kweet";
//        String actual = kweet.getMessage();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setMessage()
//    {
//    }
//
//    @Test
//    public void getPostDate()
//    {
//        Date postDate = new Date();
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", postDate, likes, mentions, trends);
//        Date expected = postDate;
//        Date actual = kweet.getPostDate();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setPostDate()
//    {
//    }
//
//    @Test
//    public void getLikes()
//    {
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
//        List<String> expected = likes;
//        List<String> actual = kweet.getLikes();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setLikes()
//    {
//    }
//
//    @Test
//    public void getMentions()
//    {
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
//        List<Mention> expected = mentions;
//        List<Mention> actual = kweet.getMentions();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setMentions()
//    {
//    }
//
//    @Test
//    public void getTrends()
//    {
//        kweet = new Kweet( 1L, "Hans", "Dit is een kweet", new Date(), likes, mentions, trends);
//        List<Trend> expected = trends;
//        List<Trend> actual = kweet.getTrends();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setTrends()
//    {
//    }
//
//}