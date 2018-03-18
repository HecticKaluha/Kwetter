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
            profileService.addRole("Admin", true, true, true, true);
            profileService.addRole("User", false, true, false, true);

            //Profiles worden alleen naar de database gestuurd wanneer ze daadwerkelijk een kweet hebben gepost.
            //dit komt doordat nu alleen nog POST JPA implementatie heeft.
            profileService.createProfile("Hans", profileService.getRole("Admin"));
            profileService.createProfile("Peter", profileService.getRole("User"));
            profileService.createProfile("Klaartje", profileService.getRole("Admin"));

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