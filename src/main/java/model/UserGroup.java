package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@XmlRootElement
public class UserGroup implements Serializable {
    @Id
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups", cascade = CascadeType.ALL)
    private List<Profile> users = new ArrayList<>();

    public UserGroup(String name) {
        this.name = name;
    }

    public UserGroup() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Profile> getUsers() {
        return users;
    }

    public void setUsers(List<Profile> users) {
        this.users = users;
    }

    public void setUser(Profile profile){
        for (UserGroup ug:profile.getGroups()
                ) {
            ug.getUsers().remove(profile);
        }
        for(Profile p: users)
        {
            p.getRole().clear();
            this.users.add(profile);
            p.getRole().add(this);
        }
    }
}
