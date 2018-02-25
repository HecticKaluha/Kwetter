package dao;

import java.util.List;
import model.Kweet;

public interface KwetterManagerDao {

    Kweet post(Kweet p);

    Kweet update(Long id, String author, String content);

    void delete(Long id);

    List<Kweet> findAll();

    Kweet find(Long id);
}
