package bean;

import exceptions.CouldNotGetListException;
import model.Profile;
import service.KweetService;
import service.ProfileService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named(value="kwetterBean")
public class KwetterBean implements Serializable{

    @Inject
    private KweetService kweetService;
    @Inject
    private ProfileService profileService;

    private Profile selectedProfile;
    private String username;
    private String password;
    private UIComponent passwordComponent;
    private UIComponent usernameComponent;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UIComponent getPasswordComponent() {
        return passwordComponent;
    }

    public void setPasswordComponent(UIComponent passwordComponent) {
        this.passwordComponent = passwordComponent;
    }

    public UIComponent getUsernameComponent() {
        return usernameComponent;
    }

    public void setUsernameComponent(UIComponent usernameComponent) {
        this.usernameComponent = usernameComponent;
    }

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            return "/index.xhtml";
        }
        catch (ServletException e) {
            return null;
        }
    }

    public List<Profile> getAllUsers()
    {
        try
        {
            return profileService.getAllProfiles();
        }
        catch(CouldNotGetListException e)
        {
            return null;
        }
    }

    public Profile getSelectedProfile()
    {
        return selectedProfile;
    }
    public void setSelectedProfile(Profile profile)
    {
        this.selectedProfile = profile;
    }
}
