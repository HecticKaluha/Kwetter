package service;

import dao.PostingDao;
import dao.PostingDaoImp;
import java.util.List;
import model.Posting;

public class BlogService {

    private final PostingDao postingDao;

    public BlogService() {
        postingDao = PostingDaoImp.getPostingDao();
    }

    public Posting addPosting(Posting p) {
        return postingDao.create(p);
    }
    
    public Posting editPosting(Long id, String author, String title, String content) {
        return postingDao.update(id, author, title, content);
    }
    
    public void removePosting(Long id) {
        postingDao.delete(id);
    }

    public List<Posting> getPostings() {
        return postingDao.findAll();
    }
    
    public Posting getPosting(Long id) {
        return postingDao.find(id);
    }
}
