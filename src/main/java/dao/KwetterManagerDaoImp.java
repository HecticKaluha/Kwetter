package dao;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class KwetterManagerDaoImp implements KwetterManagerDao
{
    private List<Profile> profiles;
    private ConcurrentHashMap<Long, Kweet> kweets;
    private List<Trend> trends;
    private List<Mention> mentions;

    @Override
    public List<Profile> getProfiles()
    {
        return profiles;
    }

    @Override
    public void setProfiles(List<Profile> profiles)
    {
        this.profiles = profiles;
    }

    public void initWebBlog()
    {
        this.kweets = new ConcurrentHashMap<>();
        this.trends = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.profiles = new ArrayList<>();
        this.profiles.add(new Profile("Admin", new Role(true, true, true, true)));
    }
}
