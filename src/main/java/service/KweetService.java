package service;

import java.util.List;

import dao.KweetDao;
import dao.KweetDaoImp;
import dao.ProfileDao;
import model.Kweet;
import model.Profile;

import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

@ApplicationScoped
public class KweetService {
    @Inject
    private KweetDao kweetDao;
    @Inject
    private ProfileDao profileDao;

    public Kweet post(String kweetmessage, String profile){
        Kweet kweet = kweetDao.post(kweetmessage, profileDao.findProfile(profile));
        profileDao.addKweetToProfile(profile, kweet);
        return kweet;
    }

    public void update(Long id, String updatedContent){
        kweetDao.update(id, updatedContent);
        //return null;
    }
    public void delete(Long id){
        kweetDao.delete(id);
        //return null;
    }
    public List<Kweet> findAll(){
        return kweetDao.findAll();
    }
    public Kweet find(Long id){
        return kweetDao.find(id);
    }

}
