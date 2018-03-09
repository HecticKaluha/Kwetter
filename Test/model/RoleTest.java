//package model;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
//public class RoleTest
//{
//    private Role role;
//
//    @Before
//    public void setUp() throws Exception
//    {
//        role = null;
//    }
//
//    @After
//    public void tearDown() throws Exception
//    {
//    }
//
//    @Test
//    public void getCanDelete()
//    {
//        role = new Role( true, true, false, true);
//        boolean expected = true;
//        boolean actual = role.getCanDelete();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setCanDelete()
//    {
//
//    }
//
//    @Test
//    public void getCanPost()
//    {
//        role = new Role( true, true, false, true);
//        boolean expected = true;
//        boolean actual = role.getCanPost();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setCanPost()
//    {
//    }
//
//    @Test
//    public void getCanBlacklist()
//    {
//        role = new Role( true, true, false, true);
//        boolean expected = false;
//        boolean actual = role.getCanBlacklist();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setCanBlacklist()
//    {
//    }
//
//    @Test
//    public void getCanLike()
//    {
//        role = new Role( true, true, false, true);
//        boolean expected = true;
//        boolean actual = role.getCanLike();
//        assertThat(expected, is(actual));
//    }
//
//    @Test
//    public void setCanLike()
//    {
//    }
//}