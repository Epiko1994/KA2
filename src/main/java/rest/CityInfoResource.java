package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityInfoDTO;
import entities.CityInfo;
import entities.Hobby;
import facades.CityInfoFacade;
import utils.EMF_Creator;
import facades.PersonFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("zip")
public class CityInfoResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/ka2",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final CityInfoFacade FACADE =  CityInfoFacade.getCityInfoFacade(EMF);

//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public String demo() {
//        return "{\"msg\":\"Hello World ZIP\"}";
//    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "Get list of all zipcodes",
            tags = {"cityInfo"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "List of all zipcodes"),
                @ApiResponse(responseCode = "400", description = "Zipcode not found")})
    public List<CityInfoDTO> getAllZips() {
        List<CityInfoDTO> ZipList = FACADE.getAllZips();
        return ZipList;
    }  

}