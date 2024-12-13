package ressources;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/log")
public class LogementRessources {
    LogementBusiness logementBusiness = new LogementBusiness();

  // list
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(){
        return Response.ok().entity(this.logementBusiness.getLogements()).build();
    }


    //creation d'un nouveau logement
    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_XML)
    public Response addLogement(Logement logement){
        if(this.logementBusiness.addLogement(logement)){
            return  Response.status(201).entity("created!").build();
        }else
            return  Response.status(200).entity("error Data").build();
    }
    //update un logement :
    @PUT
    @Path("/update/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateLogement(@PathParam("reference") int reference, Logement logement){
        if(this.logementBusiness.updateLogement(reference, logement)){
            return Response.status(201).entity("updated!").build();
        }else
            return Response.status(200).entity("error DATA").build();
    }
    //delete un logement
    @DELETE
    @Path("/delete/{reference}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("reference") int reference){
        if(this.logementBusiness.deleteLogement(reference)){
            return Response.status(200).entity("deleted!").build();
        }else
            return Response.status(200).entity("error DATA").build();
    }
    //get logement par delegation
    @GET
    @Path("/delegation/{delegation}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDelegation(@PathParam("delegation") String delegation){
        return Response.ok(logementBusiness.getLogementsByDeleguation(delegation)).build();
    }

    //get logement par reference
    @GET
    @Path("/ref/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReference(@PathParam("reference") int reference){
        List<Logement> logements = this.logementBusiness.getLogementsListeByref(reference);
        if (logements.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("aucune logemet trouvé avec ce reference donnée").build();
        }else
            return Response.ok(logements).build();
    }
    //set logement
    @PUT
    @Path("/setLogement")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response setLogement(List<Logement> logements){
        try {
            logementBusiness.setLogements(logements);
            return Response.status(201).entity("set success").build();
        }catch (Exception e){
            return Response.status(500).entity("error lors de mise a jour des listes logement").build();
        }
    }
}
