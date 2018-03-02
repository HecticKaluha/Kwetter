package dao;

import java.util.List;
import model.Kweet;
import model.Profile;

public interface KwetterManagerDao {
    List<Profile> getProfiles();
    void setProfiles(List<Profile> profiles);
}
