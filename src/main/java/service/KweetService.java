package service;

import java.util.List;

import dao.KweetDao;
import dao.KweetDaoImp;
import model.Kweet;
import model.Profile;

public class KweetService {


    private final KweetDao kweetDao;
    public KweetService() {
        kweetDao = KweetDaoImp.getKweetDao();
    }
    
    public void post(String kweetmessage, Profile profile){
        //get profile from id

        kweetDao.post(kweetmessage, profile);
        //eventueel add kweet to profile it's kweets
        //return null;
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
