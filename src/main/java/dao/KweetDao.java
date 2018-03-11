package dao;

import exceptions.CouldNotCreateKweetException;
import model.Kweet;
import model.Profile;

import java.util.List;

public interface KweetDao
{
    Kweet post(String kweetMessage, Profile profile) throws CouldNotCreateKweetException;
    void update(Long id, String content);
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);
}
