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
            //kweets.put(kweet.getId(), kweet);
            //utx.begin();
            em.persist(kweet);
            //utx.commit();
            return new Kweet(profile, kweetMessage, new Date());
        }
        catch(Exception e)
        {
            throw new CouldNotCreateKweetException(e.getMessage());
        }
    }

    @Override
    public Kweet update(Long id, String content) throws NoContentToUpdateException, KweetNotFoundException {
        if (content == null || content.isEmpty())
        {
            throw new NoContentToUpdateException("No Content to update for kweet with id " + id);
        }
        Kweet kweetToUpdate;
        try {
            kweetToUpdate = find(id);
            kweetToUpdate.setMessage(content);
            kweetToUpdate.setPostDate(new Date());
            em.persist(kweetToUpdate);
            return kweetToUpdate;
        }
        catch(Exception e)
        {
            throw new KweetNotFoundException("Kweet with id " + id + " was not found in the database.");
        }
    }

    @Override
    public boolean delete(Long id) throws CouldNotDeleteKweetException{
        try{
            Kweet kweetToDelete = find(id);
            em.remove(kweetToDelete);
            return true;
        }
        catch(Exception e)
        {
            throw new CouldNotDeleteKweetException("Kweet with id " + id + " was not deleted because: " + e.getMessage());
        }
    }

    @Override
    public List<Kweet> findAll() throws CouldNotGetListException {
        try{
            return em.createQuery("SELECT kweet FROM Kweet kweet").getResultList();
        }
        catch(Exception e)
        {
            throw new CouldNotGetListException("Could not retrieve all from database");
        }
    }

    @Override
    public Kweet find(Long id) throws KweetNotFoundException {
        try {
            Kweet kweetToReturn = em.createQuery("SELECT kweet FROM Kweet kweet WHERE kweet.id = :id", Kweet.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return kweetToReturn;
        }
        catch(Exception e)
        {
            throw new KweetNotFoundException("Kweet with id " + id + " not found");
        }
    }
}
