package service;

import java.util.List;

import dao.KwetterManager;
import dao.KwetterManagerDao;
import model.Kweet;

public class BlogService {

    private final KwetterManagerDao kwetterManagerDao;

    public BlogService() {
        kwetterManagerDao = KwetterManager.getPostingDao();
    }

    public Kweet addPosting(Kweet p) {
        return kwetterManagerDao.post(p);
    }
    
    public Kweet editPosting(Long id, String author, String title, String content) {
        return kwetterManagerDao.update(id, author, content);
    }
    
    public void removePosting(Long id) {
        kwetterManagerDao.delete(id);
    }

    public List<Kweet> getPostings() {
        return kwetterManagerDao.findAll();
    }
    
    public Kweet getPosting(Long id) {
        return kwetterManagerDao.find(id);
    }
}
