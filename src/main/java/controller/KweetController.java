package controller;

import model.Kweet;
import model.KweetBody;
import model.Profile;
import service.KweetService;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/kweet")
@Produces(MediaType.APPLICATION_JSON)
public class KweetController
{
    @Inject
    private KweetService kweetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(KweetBody kweetBody)
    {
        if(kweetBody == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(kweetService.post(kweetBody.getMessage(), kweetBody.getUsername())).build();
    }

    /*void post(String kweetMessage, Profile profile);
    void update(Long id, String content);
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);*/
}
