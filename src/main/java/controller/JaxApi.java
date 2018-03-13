package controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class JaxApi extends Application
{
    /*@Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> restClass = new HashSet<>();
        restClass.add(KweetController.class);
        return restClass;
    }*/
}
