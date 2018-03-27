package dao;

import exceptions.*;
import model.Kweet;
import model.Profile;
import model.Role;
import qualifier.JPA;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@JPA
@Stateless
public class ProfileDaoJPAImp implements ProfileDao
{
    private ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Role> roles = new ConcurrentHashMap<>();

    @PersistenceContext(unitName = "KwetterPersistence")
    private EntityManager em;

    private ProfileDaoJPAImp() {
        this.initProfileDaoImp();
    }

    public void initProfileDaoImp() {
//        profiles = new ConcurrentHashMap<>();
//        roles = new ConcurrentHashMap<>();
//        addRole("Admin", true, true, true, true);
//        addRole("User", false, true, false, true);
//        createProfile("Hans", roles.get("Admin") );
//        createProfile("Peter", roles.get("Admin"));
//        createProfile("Klaartje", roles.get("User"));
    }

    @Override
    public boolean followUser(Profile followThisProfile, Profile initialProfile) throws CouldNotFindProfileException, UnableToFollowException {
        try{
            Profile profileToFollow = findProfile(followThisProfile.getUsername());
            Profile ownProfile = findProfile(initialProfile.getUsername());
            ownProfile.addFollowing(profileToFollow);
            profileToFollow.addFollower(ownProfile);
            em.merge(ownProfile);
            em.merge(profileToFollow);
            return true;
        }
        catch(Exception e)
        {
            System.out.print("Error");
            throw new UnableToFollowException(initialProfile.getUsername() + " was unable to follow " + followThisProfile.getUsername() + " because: " + e.getMessage());
        }
    }

    @Override
    public boolean unfollowUser(Profile unfollowThisProfile, Profile initialProfile) throws UnableToUnFollowException {
        try{
            Profile profileToUnFollow = findProfile(unfollowThisProfile.getUsername());
            Profile ownProfile = findProfile(initialProfile.getUsername());
            ownProfile.removeFollowing(profileToUnFollow);
            profileToUnFollow.removeFollower(ownProfile);
            em.merge(ownProfile);
            em.merge(profileToUnFollow);
            return true;
        }
        catch(Exception e)
        {
            throw new UnableToUnFollowException(initialProfile.getUsername() + " was unable to unfollow " + unfollowThisProfile.getUsername() + " because: " + e.getMessage());
        }
    }

    @Override
    public Profile findProfile(String username) throws CouldNotFindProfileException
    {
        try {
            Profile profileToReturn = em.createQuery("SELECT profile FROM Profile profile WHERE profile.username = :username", Profile.class)
                    .setParameter("username", username)
                    .getSingleResult();
            return profileToReturn;
        }
        catch(Exception e)
        {
            throw new CouldNotFindProfileException("profile with id " + username + " not found because: " + e.getMessage());
        }
    }

    @Override
    public void createProfile(String username, Role role) throws ParametersWereEmptyException, AddingToCollectionFailedException {
        if(username == null || username.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(role == null)
        {
            throw new ParametersWereEmptyException("parameter " + role +" was null or empty");
        }
        try{
            Profile profile = new Profile(username, role);
            em.persist(profile);
            //profiles.put(username, new Profile(username, role));
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException(e.getMessage());
        }
    }

    @Override
    public boolean deleteProfile(String username) throws CouldNotFindProfileException, ParametersWereEmptyException, AddingToCollectionFailedException {
        if(username == null || username.isEmpty()) {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        try{
            em.remove(findProfile(username));
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException("could not delete profile because: " + e.getMessage());
        }
        return true;
    }

    @Override
    public boolean updateProfile(String username, String newUsername, String bio, String location, String web) throws CouldNotFindProfileException, ParametersWereEmptyException, CouldNotUpdateProfileException {
        if(username == null || username.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(newUsername == null || newUsername.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + newUsername +" was null or empty");
        }
        if(bio == null || bio.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + bio +" was null or empty");
        }
        if(location == null || location.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + location +" was null or empty");
        }
        if(web == null || web.isEmpty())
        {
            throw new ParametersWereEmptyException("parameter " + web +" was null or empty");
        }
        try
        {
            Profile profile = findProfile(username);
            profile.setUsername(newUsername);
            profile.setBio(bio);
            profile.setLocation(location);
            profile.setWeb(web);

            em.merge(profile);

            return true;
        }
        catch(Exception e)
        {
            throw new CouldNotUpdateProfileException("Could not update profile because: " + e.getMessage());
        }
    }

    @Override
    public List<Kweet> getLatest(String username) throws CouldNotGetLatestKweets, CouldNotFetchLatestKweetFromDatabaseException {
        try
        {
            List<Kweet> profileToReturn = em.createQuery("SELECT Kweet FROM Kweet kweet WHERE kweet.owner.username = :username ", Kweet.class)
                    .setParameter("username", username)
                    .setMaxResults(10).getResultList();
            return profileToReturn;
        }
        catch(Exception e)
        {
            throw new CouldNotFetchLatestKweetFromDatabaseException("Could not fetch latest kweets of profile " + username);
        }


    }

    @Override
    public boolean addRole(String rolename, boolean candelete, boolean canpost, boolean canblacklist, boolean canlike)
    {
        if(!roles.containsKey(rolename))
        {
            Role role = new Role(candelete, canpost, canblacklist, canlike);
            roles.put(rolename, role);
        }
        else{
            return false;
        }
        return true;
    }

    @Override
    public Role getRole(String rolename) throws RoleNotFoundException {
        if(!roles.containsKey(rolename))
        {
            throw new RoleNotFoundException("Role "+ rolename +" was not found");
        }
        return roles.get(rolename);
    }

    @Override
    public void updateRole (String username,String roleName) throws RoleNotFoundException, CouldNotFindProfileException
    {
        Profile profile = findProfile(username);
        if(roles.get(username)!= null)
        {
            Role role = roles.get(username);
            profile.setRole(role);
        }
        else
        {
            throw new RoleNotFoundException("No role with the name " + roleName + " found!");
        }
    }

    @Override
    public List<Profile> getFollowing(String username) throws CouldNotFindProfileException, CouldNotGetListException {
        try
        {
            List<Profile> profilesToReturn = em.createQuery("SELECT profile.following FROM Profile profile WHERE profile.username = :username ", Profile.class)
                    .setParameter("username", username)
                    .getResultList();
            return profilesToReturn;
        }
        catch(NoResultException e)
        {
            return new ArrayList<>();
        }

    }

    @Override
    public List<Profile> getFollower(String username) throws CouldNotFindProfileException, CouldNotGetListException {

        try{
            List<Profile> profilesToReturn = em.createQuery("SELECT profile.followers FROM Profile profile WHERE profile.username = :username ", Profile.class)
                    .setParameter("username", username)
                    .getResultList();
            return profilesToReturn;
        }
        catch(NoResultException e)
        {
            return new ArrayList<>();
        }

    }

    @Override
    public boolean addKweetToProfile(
            String username,
            Kweet kweet) throws CouldNotFindProfileException
    {
        if (findProfile(username) == null)
        {
            throw new CouldNotFindProfileException("Profile not found");
        }
        return findProfile(username).addKweet(kweet);
    }
    @Override
    public boolean removeKweetFromProfile(String username, Kweet kweet) throws CouldNotFindProfileException
    {
        return findProfile(username).removeKweet(kweet);
    }

    @Override
    public List<Profile> getAllProfiles() throws CouldNotGetListException
    {
        try
        {
             return em.createQuery("SELECT profile FROM Profile profile").getResultList();
        }
        catch(Exception e)
        {
            throw new CouldNotGetListException("Could not retrieve all from database");
        }
    }

}































