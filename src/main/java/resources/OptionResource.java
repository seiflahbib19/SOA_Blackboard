package resources;

import entities.Option;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/options")
public class OptionResource {

    private static List<Option> options = new ArrayList<>();


    // 1. Ajouter une option
    @POST
    public Response ajouterOption(Option option) {

        options.add(option);

        return Response
                .status(Response.Status.CREATED)
                .build();
    }


    // 2 + 3. Récupérer toutes les options
    // ou récupérer les options d'un domaine
    @GET
    public List<Option> getOptions(
            @QueryParam("domaine") String domaine) {

        if (domaine == null) {
            return options;
        }

        List<Option> resultat = new ArrayList<>();

        for (Option option : options) {

            if (option.getDomaine().equalsIgnoreCase(domaine)) {
                resultat.add(option);
            }
        }

        return resultat;
    }


    // 4. Supprimer une option
    @DELETE
    @Path("/{codeOption}")
    public Response supprimerOption(
            @PathParam("codeOption") int codeOption) {

        for (Option option : options) {

            if (option.getCodeOption() == codeOption) {

                options.remove(option);

                return Response
                        .status(Response.Status.OK)
                        .build();
            }
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }


    // 5. Modifier une option
    @PUT
    @Path("/{codeOption}")
    public Response modifierOption(
            @PathParam("codeOption") int codeOption,
            Option nouvelleOption) {

        for (int i = 0; i < options.size(); i++) {

            if (options.get(i).getCodeOption() == codeOption) {

                options.set(i, nouvelleOption);

                return Response
                        .ok(nouvelleOption)
                        .build();
            }
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }


    // 6. Récupérer une option par son code
    @GET
    @Path("/{codeOption}")
    public Response getOption(
            @PathParam("codeOption") int codeOption) {

        for (Option option : options) {

            if (option.getCodeOption() == codeOption) {

                return Response
                        .ok(option)
                        .build();
            }
        }

        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }
}