package model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class KweetBody
{
    private String message;
    private String username;

    public KweetBody()
    {

    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
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
