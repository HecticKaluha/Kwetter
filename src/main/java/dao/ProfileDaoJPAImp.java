package dao;

import java.security.Key;
import java.sql.*;
import com.mysql.jdbc.StringUtils;
import exceptions.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Kweet;
import model.Profile;
import model.UserGroup;
import qualifier.JPA;
import util.DateUtil;
import util.PasswordUtils;

import javax.crypto.KeyGenerator;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Date;

@JPA
@Stateless
public class ProfileDaoJPAImp implements ProfileDao
{

    @PersistenceContext(unitName = "KwetterPersistence")
    private EntityManager em;

    @Inject
    private KeyGenerator keyGenerator;
    @Context
    private UriInfo uriInfo;

    @Inject
    private DateUtil dateutil;

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
            System.out.print(profileToReturn.toString());
            return profileToReturn;
        }
        catch(Exception e)
        {
            throw new CouldNotFindProfileException("profile with id " + username + " not found because: " + e.getMessage());
        }
    }

    @Override
    public void createProfile(String username, UserGroup role, String password) throws ParametersWereEmptyException, AddingToCollectionFailedException {
        if(StringUtils.isNullOrEmpty(username))
        {
            throw new ParametersWereEmptyException("parameter " + username +" was null or empty");
        }
        if(role == null)
        {
            throw new ParametersWereEmptyException("parameter " + role +" was null or empty");
        }
        if(StringUtils.isNullOrEmpty(password))
        {
            throw new ParametersWereEmptyException("parameter password was null or empty");
        }
        try{
            List<UserGroup> groups = new ArrayList<>();
            groups.add(role);
            Profile profile = new Profile(username, password, groups);
            em.persist(profile);
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException(e.getMessage());
        }
    }

    @Override
    public void createProfile(Profile profile) throws AddingToCollectionFailedException {
        try{
            em.persist(profile);
        }
        catch(Exception e)
        {
            throw new AddingToCollectionFailedException(e.getMessage());
        }
    }

    /*@Override
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
    }*/

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
            List<Kweet> kweetFromProfileToReturn = em.createQuery("SELECT Kweet FROM Kweet kweet WHERE kweet.owner.username = :username ", Kweet.class)
                    .setParameter("username", username)
                    .getResultList();
            List<Kweet> kweetsToReturn = kweetFromProfileToReturn.subList(Math.max(kweetFromProfileToReturn.size() - 10, 0), kweetFromProfileToReturn.size());
            return kweetsToReturn;
        }
        catch(Exception e)
        {
            throw new CouldNotFetchLatestKweetFromDatabaseException("Could not fetch latest kweets of profile " + username);
        }

    }

    @Override
    public boolean addRole(String rolename)
    {
        if(!roleExists(rolename))
        {
            UserGroup role = new UserGroup(rolename);
            em.persist(role);
        }
        else{
            return false;
        }
        return true;
    }
    @Override
    public boolean roleExists(String rolename)
    {
        try{
            UserGroup userGroup = getRole(rolename);
            return true;
        }
        catch(RoleNotFoundException e)
        {
            return false;
        }
    }
    @Override
    public UserGroup getRole(String rolename) throws RoleNotFoundException {
        try{
            UserGroup userGroup = em.createQuery("SELECT usergroup FROM UserGroup usergroup WHERE usergroup.name = :rolename", UserGroup.class)
                    .setParameter("rolename", rolename)
                    .getSingleResult();
            return userGroup;
        }
        catch(Exception e)
        {
            throw new RoleNotFoundException("Role with name " + rolename + " was not found because: " + e.getMessage());
        }
    }

    @Override
    public void updateRole (String username,String newRoleName, String oldRoleName) throws RoleNotFoundException, CouldNotFindProfileException, CouldNotUpdateProfileException {

        try{
            List<UserGroup> newGroupsForProfile = new ArrayList<>();
            Profile profile = findProfile(username);
            UserGroup newUserGroup = getRole(newRoleName);
            newGroupsForProfile.add(newUserGroup);
            profile.setGroups(newGroupsForProfile);

            //updating the old usergroup
//            UserGroup oldUserGroup = getRole(oldRoleName);
//            oldUserGroup.getUsers().remove(profile);
//
//
//            //updating the new usergroup
//            UserGroup newUserGroup = getRole(newRoleName);
//            newUserGroup.getUsers().add(profile);
//            newGroupsForProfile.add(newUserGroup);
//            profile.setGroups(newGroupsForProfile);
//
//            //updating everything to the database
//            em.merge(oldUserGroup);
//            em.merge(newUserGroup);
            em.merge(profile);
        }
        catch(Exception e)
        {
            throw new CouldNotUpdateProfileException("Could not update role of profile " + username + " because: " + e.getMessage());
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

    @Override
    public List<UserGroup> getAllRoles() throws SQLException, RoleNotFoundException{
        //TODO: Make more JPA like?
        List<UserGroup> roles = new ArrayList<>();
        try (   Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/kwetterdb", "rootuser", "root");
                Statement stmt = conn.createStatement())
        {
            String strSelect = "select name from usergroup";

            ResultSet rset = stmt.executeQuery(strSelect);

            System.out.println("The records selected are:");
            while(rset.next()) {
                String roleName = rset.getString("name");
                UserGroup usergroup = getRole(roleName);
                roles.add(usergroup);
            }
        }
        return roles;
    }

    @Override
    public Kweet getLatestKweet(String username) throws CouldNotFetchLatestKweetFromDatabaseException {
        try
        {
            List<Kweet> kweetFromProfileToReturn = em.createQuery("SELECT Kweet FROM Kweet kweet WHERE kweet.owner.username = :username ", Kweet.class)
                    .setParameter("username", username)
                    .getResultList();

            Kweet kweetToReturn = kweetFromProfileToReturn.get(kweetFromProfileToReturn.size() - 1);
            return kweetToReturn;

        }
        catch(Exception e)
        {
            throw new CouldNotFetchLatestKweetFromDatabaseException("Could not fetch latest kweets of profile " + username + " due to " + e.getMessage());
        }
    }

    @Override
    public List<Kweet> getTimeline(Profile profile) throws CouldNotFetchLatestKweetFromDatabaseException {
        try{
            List<Kweet> ownTimeLine = em.createQuery("SELECT kweet FROM Kweet kweet WHERE kweet.owner.id = :id OR :profile Member of kweet.owner.following  ORDER BY kweet.postDate DESC")
                    .setParameter("profile", profile).setParameter("id", profile.getId())
                    .getResultList();
            /*List<Kweet> ownTimeLine = em.createQuery("Select kweet from Kweet kweet where kweet.owner.id in " +
                    "(SELECT profile.id FROM Profile profile WHERE profile )")
                    .setParameter("id", id)
                    .getResultList();*/
            return ownTimeLine; // select profile id from profile where profile in owner.followers
        }
        catch(Exception e)
        {
            throw new CouldNotFetchLatestKweetFromDatabaseException("Could not fetch latest kweets of profile " + profile.getUsername() + " due to " + e.getMessage());
        }
    }

    @Override
    public void authenticate(String username, String password) throws SecurityException {
        Profile profile = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password", Profile.class)
                .setParameter("login", username).setParameter("password", PasswordUtils.digestPassword(password))
                .getSingleResult();

        if (profile == null)
            throw new SecurityException("Invalid user/password");
    }

    @Override
    public String issueToken(String login) {
        Key key = keyGenerator.generateKey();
        String jwtToken = Jwts.builder()
                .setSubject(login)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(dateutil.toDate(LocalDateTime.now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        System.out.println("#### generating token for a key : " + jwtToken + " - " + key);
        return jwtToken;
    }
}