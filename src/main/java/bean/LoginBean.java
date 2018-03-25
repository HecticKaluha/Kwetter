package bean;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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

    public String login()
    {
        System.out.print("Handling login");

        FacesContext context = FacesContext.getCurrentInstance();

        System.out.print("Got Context");

        HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();

        System.out.print("got request");

        try{
            System.out.print("Login in...");
            request.login(this.username, this.password);
            System.out.print("Logged in");
        }
        catch(Exception e){
            System.out.print("Something went wrong because: " + e.getMessage());
            context.addMessage(usernameComponent.getClientId(), new FacesMessage("Username may not be correct"));
            context.addMessage(passwordComponent.getClientId(), new FacesMessage("Password may not be correct"));
            return "/error/forbidden.xhtml";
        }
        System.out.print("Login Succesvol");
        return "/pages/admin.xhtml";
    }
}
