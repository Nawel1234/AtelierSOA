package webservices;

import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logement")
public class LogementRessources {

    LogementBusiness help = new LogementBusiness();

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response.status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(help.getLogements())
                .build();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHelloT(@PathParam("name") String name) {
        return Response.status(200)
                .entity("Hello " + name + "!")
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLogement(Logement logement) {
        boolean added = help.addLogement(logement);
        if (added) {
            return Response.status(Response.Status.CREATED)
                    .entity(logement)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erreur d'ajout du logement.")
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLogement(@PathParam("id") int id, Logement logement) {
        boolean updated = help.updateLogement(id, logement);
        if (updated) {
            return Response.status(Response.Status.OK)
                    .entity(logement)
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Erreur de mise à jour du logement.")
                    .build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteLogement(@PathParam("id") int id) {
        boolean deleted = help.deleteLogement(id);
        if (deleted) {
            return Response.status(Response.Status.OK)
                    .entity("Logement supprimé avec succès.")
                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Logement non trouvé ou erreur de suppression.")
                    .build();
        }
    }
}
