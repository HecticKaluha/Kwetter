package controller.JsonBodyClasses;

import model.Role;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateProfileBody implements Serializable
{
    private String username;
    private Role role;

    public CreateProfileBody()
    {

    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
