package Initialize;

import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import exceptions.RoleNotFoundException;
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
    public void initData()
    {
        try
        {
            profileService.addRole("Admin", true, true, true, true);
            profileService.addRole("User", false, true, false, true);

            profileService.createProfile("Hans", profileService.getRole("Admin"));
            profileService.createProfile("Peter", profileService.getRole("Admin"));
            profileService.createProfile("Klaartje", profileService.getRole("User"));

            kweetService.post("Bericht 1", "Klaartje");
            kweetService.post("Bericht 2","Hans");
            kweetService.post("Bericht 3", "Klaartje");
        }
        catch (CouldNotCreateKweetException | RoleNotFoundException | CouldNotFindProfileException e)
        {
            e.printStackTrace();
        }
    }
}