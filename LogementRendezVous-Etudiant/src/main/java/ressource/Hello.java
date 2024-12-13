package ressource;

import entities.Logement;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class Hello {

    @Path("/sayHello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
        return Response.status(200).entity("hello douraid").build();
    }


}
