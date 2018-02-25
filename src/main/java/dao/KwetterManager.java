package dao;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class KwetterManager implements KwetterManagerDao
{
    private List<Profile> profiles;
    private List<Trend> trends;
    private List<Mention> mentions;
    private ConcurrentHashMap<Long, Kweet> kweets;
    private static KwetterManagerDao instance = null;

    public List<Profile> getProfiles()
    {
        return profiles;
    }

    public void setProfiles(List<Profile> profiles)
    {
        this.profiles = profiles;
    }

    public List<Trend> getTrends()
    {
        return trends;
    }

    public void setTrends(List<Trend> trends)
    {
        this.trends = trends;
    }

    public List<Mention> getMentions()
    {
        return mentions;
    }

    public void setMentions(List<Mention> mentions)
    {
        this.mentions = mentions;
    }


    private AtomicLong nextId = new AtomicLong(0L);

    public static synchronized KwetterManagerDao getPostingDao() {
        if (instance == null) {
            instance = new KwetterManager();
        }

        return instance;
    }
    private KwetterManager() {
        this.initWebBlog();
    }

    public void initWebBlog() {
        this.kweets = new ConcurrentHashMap<>();
        this.trends = new ArrayList<>();
        this.mentions = new ArrayList<>();
        this.profiles = new ArrayList<>();
        this.profiles.add(new Profile("Admin", new Role(true,true,true,true)));
        post(new Kweet("Admin", "Bericht 1", new Date()));
        post(new Kweet("Admin", "Bericht 2", new Date()));
        post(new Kweet("Admin", "Bericht 3", new Date()));
    }

    @Override
    public Kweet post(Kweet k)
    {
        if (k == null) {
            throw new IllegalArgumentException("Kweet is null");
        }
        k.setId(nextId.getAndIncrement());
        kweets.put(k.getId(), k);
        return k;
    }

    @Override
    public Kweet update(Long id, String author, String content) {
    if (author == null || content == null) {
        throw new IllegalArgumentException("Author, Title or Content is null");
    }
    Kweet k = kweets.get(id);
    k.setOwner(author);
    k.setMessage(content);

    return k;
}

    @Override
    public void delete(Long id) {
        if (!kweets.containsKey(id)) {
            throw new IllegalArgumentException("Id not found: " + id);
        }

        kweets.remove(id);
    }

    @Override
    public List<Kweet> findAll() {
        return new ArrayList(kweets.values());
    }

    @Override
    public Kweet find(Long id) {
        if (!kweets.containsKey(id)) {
            throw new IllegalArgumentException("Id not found: " + id);
        }
        return kweets.get(id);
    }
}
