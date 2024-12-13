package ressource;

import metiers.RendezVousBusiness;

import entities.RendezVous;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rdv")
public class RendezVousRessources {
    RendezVousBusiness rendezVousBusiness = new RendezVousBusiness();

    //ajout rdv
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/new")
    public Response newRendezVous(RendezVous rendezVous) {
        if (this.rendezVousBusiness.addRendezVous(rendezVous)) {
            return Response.status(201).entity("Rendez Vous cree avec success !").build();
        }else
            return Response.status(400).entity("Error d'ajout de RendezVous !").build();
    }

    //get tt les rdvs
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listRendezVous() {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVous();
        return Response.status(200).entity(rendezVousList).build();
    }

    //get rendezVous par reference
    @GET
    @Path("/logement/{reference}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRendezVousBylogementRef(@PathParam("reference") int reference) {
        List<RendezVous> rendezVousList = rendezVousBusiness.getListeRendezVousByLogementReference(reference);
        return Response.status(200).entity(rendezVousList).build();
    }

    //delete rdv
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRendezVous(@PathParam("id") int id) {
        if (this.rendezVousBusiness.deleteRendezVous(id)) {
            return Response.status(200).entity("rendez-vous suppriméé ! ").build();
        }else
            return Response.status(400).entity("Erreur de suppression !").build();
    }


    //update rdv
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous rendezVous) {
        if(rendezVousBusiness.updateRendezVous(id, rendezVous)) {
            return Response.status(200).entity("Rendez-vous updated ! ").build();
        } else {
            return Response.status(400).entity("Rendez-vous non trouvé !").build();
        }
    }

    //setListerendezVous
    @PUT
    @Path("/setList")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response setRendezVousList(List<RendezVous> rendezVousList) {
        rendezVousBusiness.setListeRendezVous(rendezVousList);
        return Response.status(200).entity("Liste à jour avec succès").build();

    }

}
