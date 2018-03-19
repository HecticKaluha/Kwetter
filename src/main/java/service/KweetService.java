package service;

import java.util.List;

import exceptions.*;
import dao.KweetDao;
import dao.ProfileDao;
import model.Kweet;
import qualifier.JPA;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;

@Stateless
public class KweetService {

    @Inject
    @JPA
    private KweetDao kweetDao;
    @Inject
    @JPA
    private ProfileDao profileDao;

    public Kweet post(String kweetmessage, String profile) throws CouldNotCreateKweetException, CouldNotFindProfileException, CouldNotRoleBackException {
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
    public boolean delete(Long id) throws KweetNotFoundException, CouldNotDeleteKweetException {
        return kweetDao.delete(id);
    }
    public List<Kweet> findAll() throws CouldNotGetListException{
        return kweetDao.findAll();
    }
    public Kweet find(Long id) throws KweetNotFoundException{
        return kweetDao.find(id);
    }

}
