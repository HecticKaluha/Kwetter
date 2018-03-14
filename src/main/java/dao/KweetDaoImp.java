package dao;

import exceptions.CouldNotCreateKweetException;
import model.*;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class KweetDaoImp implements KweetDao
{
    private ConcurrentHashMap<Long, Kweet> kweets = new ConcurrentHashMap<>();

    private AtomicLong nextId = new AtomicLong(0L);


    @Override
    public Kweet post(String kweetMessage, Profile profile)
    throws CouldNotCreateKweetException
    {
        try{
            Kweet kweet;
            kweet = new Kweet(profile, kweetMessage, new Date());
            kweet.setId(nextId.getAndIncrement());
            kweets.put(kweet.getId(), kweet);
            return kweet;
        }
        catch(IllegalArgumentException e)
        {
            throw new CouldNotCreateKweetException("Could not create Kweet");
        }
    }

    @Override
    public void update(Long id, String content) {

        if (content != null && !content.isEmpty())
        {
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
        //recursief! Return alleen de waarden per kweet die je nodig hebt.
        /*List<Kweet> kweetList = new ArrayList<Kweet>();
        for(Kweet kweet:kweets.values())
        {
            //kweetList.add(new Kweet(new Profile("Hans"), "gelukt", new Date()));
            kweetList.add(new Kweet(kweet.getOwner(),  kweet.getMessage(), kweet.getPostDate()));
        }
        return kweetList;*/
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
