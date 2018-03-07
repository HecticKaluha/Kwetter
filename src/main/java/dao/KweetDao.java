package dao;

import model.Kweet;
import model.Profile;

import java.util.List;

public interface KweetDao
{
    //Kweet post(Kweet k);
    void post(String kweetMessage, Profile profile);
    void update(Long id, String content);
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);
}
