package dao;

import model.Kweet;
import java.util.List;

public interface KweetDao
{
    void addComment(String message, String profile);
    Kweet post(Kweet k);
    Kweet update(Long id, String author, String content);
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);
}
