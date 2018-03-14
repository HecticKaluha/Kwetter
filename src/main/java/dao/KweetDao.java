package dao;

import exceptions.CouldNotCreateKweetException;
import exceptions.KweetNotFoundException;
import exceptions.NoContentToUpdateException;
import model.Kweet;
import model.Profile;

import java.util.List;

public interface KweetDao
{
    Kweet post(String kweetMessage, Profile profile) throws CouldNotCreateKweetException;
    Kweet update(Long id, String content) throws NoContentToUpdateException, KweetNotFoundException;
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);
}
