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

            profileService.createProfile("Hans", "Admin");
            profileService.createProfile("Klaartje","Admin");
            profileService.createProfile("Peter", "User");

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