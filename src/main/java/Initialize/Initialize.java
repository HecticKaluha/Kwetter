package Initialize;

import exceptions.*;
import model.Profile;
import model.UserGroup;
import qualifier.JPA;
import service.KweetService;
import service.ProfileService;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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

            //profileService.createProfile("Hans", "Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
            List<UserGroup> adminRole = new ArrayList<>();
            adminRole.add(profileService.getRole("Admin"));

            List<UserGroup> userRole = new ArrayList<>();
            userRole.add(profileService.getRole("User"));

            Profile hans = new Profile("Hans", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", adminRole, "Havikstraat Sliedrecht", "www.facebook.com", "Ik wil graag Stewardess op een UFO worden", "https://pre00.deviantart.net/2fc3/th/pre/f/2016/257/4/8/dead_bird_png_stock_single_4589_by_annamae22-dahel2x.png");
            profileService.createProfile(hans);

            Profile klaartje = new Profile("Klaartje", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", adminRole, "Professor Goossenslaan Tilburg", "www.google.com", "Ik wil graag UFO-Piloot worden", "http://pngimg.com/uploads/birds/birds_PNG116.png");
            profileService.createProfile(klaartje);

            Profile peter = new Profile("Peter", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", userRole, "Havendries 60 Waspik", "www.pinterest.com", "Ik ben een Aliën", "http://pluspng.com/img-png/alien-png-390.png");
            profileService.createProfile(peter);



            //profileService.createProfile("Klaartje","Admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");
            //profileService.createProfile("Peter", "User", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918");

            kweetService.post("Bericht 1", "Klaartje");
            kweetService.post("Bericht 2","Hans");
            kweetService.post("Bericht 3", "Klaartje");
        }
        catch (CouldNotCreateKweetException | RoleNotFoundException | CouldNotFindProfileException | CouldNotCreateProfileException | /*ParametersWereEmptyException |*/ AddingToCollectionFailedException | CouldNotRoleBackException e)
        {
            e.printStackTrace();
        }
    }
}