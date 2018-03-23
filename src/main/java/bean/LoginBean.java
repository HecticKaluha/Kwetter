package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named(value="loginBean")
public class LoginBean implements Serializable{

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

    public void login()
    {
        try{
            ExternalContext externalContext = FacesContext.getCurrentInstance()
                    .getExternalContext();
            if(this.username.equals("admin") && this.password.equals("admin")) {
                externalContext.redirect(externalContext.getRequestContextPath()
                        + "/index.xhtml");
            }
            else
            {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(usernameComponent.getClientId(), new FacesMessage("Username may not be correct"));
                context.addMessage(passwordComponent.getClientId(), new FacesMessage("Password may not be correct"));
            }
        }
        catch(Exception e)
        {
            System.out.print("Something went wrong because: " + e.getMessage());
        }
    }

}
