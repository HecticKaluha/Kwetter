package Initialize;

import exceptions.*;
import qualifier.JPA;
import service.KweetService;
import service.ProfileService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class Initialize {

    @Inject
    private ProfileService profileService;

    @Inject
    private KweetService kweetService;

    public Initialize()
    {

    }

    @PostConstruct
    public void initData(){
        try
        {
            profileService.addRole("Admin");
            profileService.addRole("User");

            profileService.createProfile("Hans", "Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
            profileService.createProfile("Klaartje","Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
            profileService.createProfile("Peter", "User", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");

            kweetService.post("Bericht 1", "Klaartje");
            kweetService.post("Bericht 2","Hans");
            kweetService.post("Bericht 3", "Klaartje");
        }
        catch (CouldNotCreateKweetException | RoleNotFoundException | CouldNotFindProfileException |ParametersWereEmptyException | AddingToCollectionFailedException | CouldNotRoleBackException e)
        {
            e.printStackTrace();
        }
    }
}