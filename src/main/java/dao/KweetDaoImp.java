package dao;

import model.*;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class KweetDaoImp implements KweetDao
{
    private static KweetDao instance = null;
    private ConcurrentHashMap<Long, Kweet> kweets;
    private AtomicLong nextId = new AtomicLong(0L);

    public static synchronized KweetDao getKweetDao() {
        if (instance == null) {
            instance = new KweetDaoImp();
        }
        return instance;
    }

    private KweetDaoImp() {
        this.initKweetDaoImp();
    }

    public void initKweetDaoImp() {
        kweets = new ConcurrentHashMap<>();
        post("Bericht 1", new Profile("Hans"));
        post("Bericht 2", new Profile("Piet"));
        post("Bericht 3", new Profile("Klaartje"));
    }

    @Override
    public void post(String kweetMessage, Profile profile)
    {
        if (!kweetMessage.isEmpty() && profile != null) {

            Kweet kweet = new Kweet(profile.getUsername(), kweetMessage, new Date());
            kweet.setId(nextId.getAndIncrement());
            kweets.put(kweet.getId(), kweet);
            //add kweet to profile
            //possibly return kweet to kweetservice and pass it to profiledao to add it there
            profile.addKweet(kweet.getId());
        }
        else
        {
            throw new IllegalArgumentException("Kweetmessage is empty");
        }
        //return k;
    }

    @Override
    public void update(Long id, String content) {

        if (content == null && !content.isEmpty()) {
            throw new IllegalArgumentException("Author, Title or Content is null");
        }
        for (Long kweetId : kweets.keySet())
        {
            if(kweetId.equals(id))
            {
                Kweet kweetToUpdate = kweets.get(id);
                kweetToUpdate.setMessage(content);
                kweetToUpdate.setPostDate(new Date());
                kweets.put(id, kweetToUpdate);
            }
        }
        //return k;
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
