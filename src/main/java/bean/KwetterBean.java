package bean;

import com.mysql.jdbc.StringUtils;
import exceptions.*;
import model.Kweet;
import model.Profile;
import model.UserGroup;
import org.apache.commons.lang3.ObjectUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
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
import java.sql.SQLException;
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

    private List<Kweet> allKweets;
    private List<Profile> allProfiles;
    private List<UserGroup> allRoles;

    public String doLogout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            return "/index.xhtml";
        }
        catch (ServletException e) {
            System.out.print("Something went wrong while logging out: " + e.getMessage());
            return null;
        }
    }

    public List<Kweet> getAllKweets()
    {
        FacesMessage facesMessage;
        try
        {
            if (allKweets == null)
                allKweets = kweetService.findAll();
            return allKweets;
        }
        catch (CouldNotGetListException e) {
            System.out.print("something went wrong when getting all the kweets: " + e.getMessage());
            facesMessage = new FacesMessage("something went wrong when getting all the kweets: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return new ArrayList<>();
        }
    }

    public void setAllKweets(List<Kweet> allKweets) {
        this.allKweets = allKweets;
    }

    public void onKweetCellEdit(CellEditEvent event) {
        String oldMessage = event.getOldValue().toString();
        String newMessage = event.getNewValue().toString();
        if(!oldMessage.equals(newMessage)) {
            Kweet entity = (Kweet) ((DataTable) event.getComponent()).getRowData();
            editKweetMessage(entity.getKweetId(), newMessage);
        }
    }

    public void editKweetMessage(long id, String message) {
        FacesMessage facesMessage = new FacesMessage("");
        try{
            if (!StringUtils.isNullOrEmpty(message)) {
                kweetService.update(id, message);
                facesMessage = new FacesMessage("Updated kweet", message);
            }
        }
        catch(KweetNotFoundException | NoContentToUpdateException e)
        {
            facesMessage = new FacesMessage("Something went wrong updating kweet", message);
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<Profile> getAllProfiles() {
        FacesMessage facesMessage;
        try
        {
            if (allProfiles == null)
                allProfiles = profileService.getAllProfiles();
            return allProfiles;
        }
        catch (CouldNotGetListException e) {
            System.out.print("something went wrong when getting all the profiles: " + e.getMessage());
            facesMessage = new FacesMessage("something went wrong when getting all the profiles: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return new ArrayList<>();
        }
    }

    public void setAllProfiles(List<Profile> allProfiles) {
        this.allProfiles = allProfiles;
    }

    public void onProfileCellEdit(CellEditEvent event) {
        String oldValue = event.getOldValue().toString();
        String newValue = event.getNewValue().toString();
        if(!oldValue.equals(newValue)) {
            Profile profile = (Profile) ((DataTable) event.getComponent()).getRowData();
            editProfileRole(profile.getUsername(), newValue, oldValue);
        }
    }

    public void editProfileRole(String username, String newRole, String oldRole) {
        FacesMessage facesMessage = new FacesMessage("");
        try{
            if(!StringUtils.isNullOrEmpty(username) && !StringUtils.isNullOrEmpty(newRole))
            {
                //TODO: FIx with JAAS ROLES
                profileService.updateRole(username, newRole, oldRole);
                facesMessage = new FacesMessage("Updated profile", username);
            }
        }
        catch(CouldNotFindProfileException | RoleNotFoundException | CouldNotUpdateProfileException e)
        {
            facesMessage = new FacesMessage("Something went wrong updating profile", username);
        }
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    public List<UserGroup> getAllRoles()
    {
        FacesMessage facesMessage;
        try
        {
            if (allRoles == null)
                allRoles = profileService.getAllRoles();
            return allRoles;
        }
        catch (SQLException | RoleNotFoundException e) {
            facesMessage = new FacesMessage("Something went wrong getting all the roles");
            System.out.print("something went wrong when getting all the roles: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            return new ArrayList<>();
        }

    }



}
