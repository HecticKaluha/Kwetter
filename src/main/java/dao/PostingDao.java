package dao;

import java.util.List;
import model.Posting;

public interface PostingDao {

    Posting create(Posting p);
    
    Posting update(Long id, String author, String title, String content);
    
    void delete(Long id);

    List<Posting> findAll();

    Posting find(Long id);
}
