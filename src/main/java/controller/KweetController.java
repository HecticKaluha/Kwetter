package controller;

import exceptions.CouldNotCreateKweetException;
import exceptions.CouldNotFindProfileException;
import model.Kweet;
import model.KweetBody;
import service.KweetService;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/kweet")
@Produces(MediaType.APPLICATION_JSON)
public class KweetController
{
    @EJB
    private KweetService kweetService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(KweetBody kweetBody)
    {
        try
        {
            Kweet kweet = kweetService.post(kweetBody.getMessage(), kweetBody.getUsername());
            return Response.ok(kweet).build();
        }
        catch(CouldNotFindProfileException | CouldNotCreateKweetException e)
        {
            return Response.ok(e.getMessage()).build();
        }

//        try{
//            if(kweetService == null)
//            {
//                return Response.ok("kweetservice is null").build();
//            }
//            if(kweetBody == null) {
//                return Response.status(Response.Status.NOT_FOUND).entity("poep").build();
//            }
//            if() == null)
//            {

//                return Response.ok("response is null").build();
//            }
//            return Response.ok(kweetService.post(kweetBody.getMessage(), kweetBody.getUsername())).build();
//        }
//        catch(Exception e)
//        {
//            return Response.ok(e.getMessage()).build();
//        }
    }

    /*void post(String kweetMessage, Profile profile);
    void update(Long id, String content);
    void delete(Long id);
    List<Kweet> findAll();
    Kweet find(Long id);*/
}
