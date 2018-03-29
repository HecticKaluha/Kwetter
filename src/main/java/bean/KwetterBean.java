package bean;

import exceptions.CouldNotGetListException;
import model.Kweet;
import model.Profile;
import org.primefaces.event.RowEditEvent;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestScoped
@Named(value="kwetterBean")
public class KwetterBean implements Serializable{

    @Inject
    private KweetService kweetService;
    @Inject
    private ProfileService profileService;
//profile table
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

    public Profile getSelectedProfile()
    {
        return selectedProfile;
    }
    public void setSelectedProfile(Profile profile)
    {
        this.selectedProfile = profile;
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
            System.out.print("something went wrong when getting all the users: " + e.getMessage());
            return new ArrayList<>();
        }

}
    public List<Kweet> getAllKweets()
    {
        try
        {
            return kweetService.findAll();
        }
        catch (CouldNotGetListException e) {
            System.out.print("something went wrong when getting all the kweets: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Kweet Edited", (((Kweet)event.getObject()).getId()).toString());
        //TODO: servicecall to edit in database;
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", (((Kweet) event.getObject()).getId()).toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
