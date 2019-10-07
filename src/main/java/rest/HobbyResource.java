package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.HobbyDTO;
import dto.PersonDTO;
import entities.Hobby;
import entities.Person;
import facades.HobbyFacade;
import utils.EMF_Creator;
import facades.PersonFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("hobby")
public class HobbyResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/ka2",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final HobbyFacade FACADE =  HobbyFacade.getHobbyFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get information about a hobby by id",
            tags = {"hobby"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested hobby"),
                @ApiResponse(responseCode = "400", description = "Hobby not found")})
    public HobbyDTO getHobby(@PathParam("id") int id) {
        HobbyDTO h = FACADE.getHobbyById(id);
        return h;
    }

    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Operation(summary = "Edit a hobby", tags = {"hobby"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Hobby edited"),
                    @ApiResponse(responseCode = "400", description = "Arguments missing with the body to edit")
            })
    public String updateHobby(@PathParam("id") long id, String hobby){
        dto.HobbyDTO hDTO = GSON.fromJson(hobby, dto.HobbyDTO.class);
        HobbyDTO h = new HobbyDTO(hDTO.getName(), hDTO.getDescription());
        h.setId(id);
        HobbyDTO hNew = FACADE.editHobby(h);
        return GSON.toJson(hNew);
    }

}