package service;

import java.util.List;

import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import dao.KweetDao;
import dao.ProfileDao;
import exceptions.KweetNotFoundException;
import exceptions.NoContentToUpdateException;
import model.Kweet;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;

@Stateless
public class KweetService {

    @Inject
    private KweetDao kweetDao;
    @Inject
    private ProfileDao profileDao;

    public Kweet post(String kweetmessage, String profile) throws CouldNotCreateKweetException, CouldNotFindProfileException
    {
        if (profileDao.findProfile(profile) == null) {
            throw new CouldNotFindProfileException("profile not found");
        }
        Kweet kweet = kweetDao.post(kweetmessage, profileDao.findProfile(profile));


        profileDao.addKweetToProfile(profile, kweet);
        return kweet;
    }

    public Kweet update(Long id, String updatedContent) throws NoContentToUpdateException, KweetNotFoundException{
        return kweetDao.update(id, updatedContent);
    }
    public boolean delete(Long id) throws KweetNotFoundException{
        return kweetDao.delete(id);
    }
    public List<Kweet> findAll(){
        return kweetDao.findAll();
    }
    public Kweet find(Long id) throws KweetNotFoundException{
        return kweetDao.find(id);
    }

}
