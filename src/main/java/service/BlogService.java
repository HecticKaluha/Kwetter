package service;

import java.util.List;

import dao.KweetDao;
import dao.KweetDaoImp;
import dao.KwetterManagerDaoImp;
import dao.KwetterManagerDao;
import model.Kweet;

public class BlogService {

    private final KweetDao KweetDao;

    public BlogService() {
        KweetDao = KweetDaoImp.getKweetDao();
    }

    public Kweet addPosting(Kweet p) {
        return KweetDao.post(p);
    }
    
    public Kweet editPosting(Long id, String author, String title, String content) {
        return KweetDao.update(id, author, content);
    }
    
    public void removePosting(Long id) {
        KweetDao.delete(id);
    }

    public List<Kweet> getPostings() {
        return KweetDao.findAll();
    }
    
    public Kweet getPosting(Long id) {
        return KweetDao.find(id);
    }
}
