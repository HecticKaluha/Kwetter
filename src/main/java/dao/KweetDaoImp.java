package dao;

import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class KweetDaoImp implements KweetDao
{
    private static KweetDao instance = null;
    private ConcurrentHashMap<Long, Kweet> kweets;

    @Override
    public void addComment(
            String message,
            String profile)
    {

    }

    private AtomicLong nextId = new AtomicLong(0L);

    public static synchronized KweetDao getKweetDao() {
        if (instance == null) {
            instance = new KweetDaoImp();
        }
        return instance;
    }

    private KweetDaoImp() {
        this.initWebBlog();
    }

    public void initWebBlog() {
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
        return new ArrayList<>(kweets.values());
    }

    @Override
    public Kweet find(Long id) {
        if (!kweets.containsKey(id)) {
            throw new IllegalArgumentException("Id not found: " + id);
        }
        return kweets.get(id);
    }
}
