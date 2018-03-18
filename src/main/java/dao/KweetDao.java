package dao;

import exceptions.*;
import model.Kweet;
import model.Profile;

import java.util.List;

public interface KweetDao
{
    Kweet post(String kweetMessage, Profile profile) throws CouldNotCreateKweetException, CouldNotRoleBackException;
    Kweet update(Long id, String content) throws NoContentToUpdateException, KweetNotFoundException;
    boolean delete(Long id) throws KweetNotFoundException;
    List<Kweet> findAll() throws CouldNotGetListException;
    Kweet find(Long id) throws KweetNotFoundException;
}
