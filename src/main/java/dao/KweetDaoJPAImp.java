package dao;

import exceptions.*;
import model.Kweet;
import model.Profile;
import qualifier.JPA;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@JPA
@Stateless
public class KweetDaoJPAImp implements KweetDao
{
    @PersistenceContext(unitName = "KwetterPersistence")
    private EntityManager em;

    /*@Resource
    private UserTransaction utx;*/


    private AtomicLong nextId = new AtomicLong(0L);
    private ConcurrentHashMap<Long, Kweet> kweets = new ConcurrentHashMap<>();

    @Override
    public Kweet post(String kweetMessage, Profile profile)
            throws CouldNotCreateKweetException, CouldNotRoleBackException {
        try{
            Kweet kweet = new Kweet(profile, kweetMessage, new Date());
            kweet.setId(nextId.getAndIncrement());
            kweets.put(kweet.getId(), kweet);

            //utx.begin();
            em.persist(kweet);
            //utx.commit();

            return new Kweet(profile, kweetMessage, new Date());
        }
        catch(Exception e)
        {
            /*try
            {
                utx.rollback();
            }
            catch(SystemException ex)
            {
                throw new CouldNotRoleBackException(ex.getMessage());
            }*/
            throw new CouldNotCreateKweetException(e.getMessage());
        }
        //return new Kweet(profile, kweetMessage, new Date());
    }

    @Override
    public Kweet update(Long id, String content) throws NoContentToUpdateException, KweetNotFoundException {
        if (content == null || content.isEmpty())
        {
            throw new NoContentToUpdateException("No Content to update for kweet with id " + id);
        }
        Kweet kweetToUpdate;
        try {
            kweetToUpdate = em.createQuery("SELECT kweet FROM Kweet kweet WHERE kweet.id = :id", Kweet.class)
                    .setParameter("id", id)
                    .getSingleResult();
            System.out.print("Aangekoemn - " + id);
            kweetToUpdate.setMessage(content);
            kweetToUpdate.setPostDate(new Date());
            return kweetToUpdate;
        }
        catch(Exception e)
        {
            System.out.print("Error: " + e.getMessage());
            throw new KweetNotFoundException("Kweet with id " + id + " was not found in the database.");
        }
    }

    @Override
    public boolean delete(Long id) throws KweetNotFoundException {
        return false;
    }

    @Override
    public List<Kweet> findAll() throws CouldNotGetListException {
        return null;
    }

    @Override
    public Kweet find(Long id) throws KweetNotFoundException {
        return null;
    }

    /*@Override
    public Kweet update(Long id, String content) throws NoContentToUpdateException, KweetNotFoundException{
        if (content == null || content.isEmpty())
        {
            throw new NoContentToUpdateException("No Content to update for kweet with id " + id);
        }
        //Check database
        if(kweets.get(id) == null)
        {
            throw new KweetNotFoundException("Kweet with id " + " not found");
        }
        Kweet kweetToUpdate = kweets.get(id);
        kweetToUpdate.setMessage(content);
        kweetToUpdate.setPostDate(new Date());
        //merge
        //kweets.put(id, kweetToUpdate);
        return kweetToUpdate;
    }

    @Override
    public boolean delete(Long id) throws KweetNotFoundException{
        if (!kweets.containsKey(id)) {
            throw new KweetNotFoundException("Kweet with id " + id + " not found");
        }
        //remove from db
        //kweets.remove(id);
        return true;
    }*/

    /*@Override
    public List<Kweet> findAll() throws CouldNotGetListException {
        //recursief! Return alleen de waarden per kweet die je nodig hebt.
        List<Kweet> kweetList = new ArrayList<Kweet>();
        for(Kweet kweet:kweets.values())
        {
            //kweetList.add(new Kweet(new Profile("Hans"), "gelukt", new Date()));
            kweetList.add(new Kweet(kweet.getOwner(),  kweet.getMessage(), kweet.getPostDate()));
        }
        return kweetList;
        if(kweets == null || kweets.isEmpty())
        {
          //throw new CouldNotGetListException("List kweets was null or empty" );
        }
        return from db

        return new ArrayList<>(kweets.values());
    }

    @Override
    public Kweet find(Long id) throws KweetNotFoundException{
        if (!kweets.containsKey(id)) {
            throw new KweetNotFoundException("Kweet with id " + id + " not found");
        }
        //return kweet from db

        //return kweets.get(id);
    }*/
}
