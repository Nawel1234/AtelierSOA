package webservices;

import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rendezvous")
public class RendezVousRessources {

    RendezVousBusiness rdvBusiness = new RendezVousBusiness();


    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<RendezVous> liste = rdvBusiness.getListeRendezVous();
        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .entity(liste)
                .build();
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        RendezVous rdv = rdvBusiness.getRendezVousById(id);
        if (rdv != null) {
            return Response.status(Response.Status.OK)
                    .entity(rdv)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("RendezVous non trouvé avec id: " + id)
                .build();
    }


    @GET
    @Path("/logement/{ref}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLogementReference(@PathParam("ref") int ref) {
        List<RendezVous> liste = rdvBusiness.getListeRendezVousByLogementReference(ref);
        return Response.status(Response.Status.OK)
                .entity(liste)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRendezVous(RendezVous rdv) {
        boolean added = rdvBusiness.addRendezVous(rdv);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity(rdv)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erreur lors de l'ajout du RendezVous (logement inconnu).")
                    .build();
        }
    }


    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRendezVous(@PathParam("id") int id, RendezVous updatedRdv) {
        boolean updated = rdvBusiness.updateRendezVous(id, updatedRdv);
        if (updated) {
            return Response.status(Response.Status.OK)
                    .entity(updatedRdv)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RendezVous non trouvé ou logement invalide.")
                    .build();
        }
    }


    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteRendezVous(@PathParam("id") int id) {
        boolean deleted = rdvBusiness.deleteRendezVous(id);
        if (deleted) {
            return Response.status(Response.Status.OK)
                    .entity("RendezVous supprimé avec succès.")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("RendezVous non trouvé.")
                    .build();
        }
    }
}
