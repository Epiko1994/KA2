package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Hobby;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@OpenAPIDefinition(
        info = @Info(
                title = "JJU GRoup",
                version = "1.0",
                description = "Backend of the CA2 project")
        )

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ka2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("count/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get count of people with given hobby",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested hobby"),
                @ApiResponse(responseCode = "400", description = "Hobby not found")})
    public int getHobbyPersonsCount(@PathParam("hobby") String hobby) {
        int hobbyPersonsCount = FACADE.getHobbyPersonsCount(hobby);
        return hobbyPersonsCount;
    }  
    
    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get list of persons by hobby",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested persons"),
                @ApiResponse(responseCode = "400", description = "Hobby not found")})
    public List<PersonDTO> getHobbyPersons(@PathParam("hobby") String hobby) {
        List<PersonDTO> hobbyPersons = FACADE.getHobbyPersons(hobby);
        return hobbyPersons;
    }  
    
    @GET
    @Path("zip/{zip}")
    @Produces(MediaType.APPLICATION_JSON)   
    @Operation(summary = "Get list of persons by zipcode",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested persons"),
                @ApiResponse(responseCode = "400", description = "Zip not found")})
    public List<Person> getPersonByZip(@PathParam("zip") long zip) {
        List<Person> zipPersons = FACADE.getPersonByZip(zip);
        return zipPersons;
    }
    
    @GET
    @Path("phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)   
    @Operation(summary = "Get information about a person by phone",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested person"),
                @ApiResponse(responseCode = "400", description = "Person not found")})
    public Person getPersonByPhone(@PathParam("phone") long phone) {
        Person p = FACADE.getPersonByPhone(phone);
        return p;
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get information about a person by id",
            tags = {"person"},
            responses = {
                @ApiResponse(
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(responseCode = "200", description = "The Requested person"),
                @ApiResponse(responseCode = "400", description = "Person not found")})
    public PersonDTO getPerson(@PathParam("id") long id) {
        PersonDTO p = FACADE.getPersonById(id);
        return p;
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "Delete a person", tags = {"person"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Person deleted"),
                @ApiResponse(responseCode = "400", description = "Arguments missing to delete")
            })
     public String deletePerson(@PathParam("id") long id){
        Person pDeleted = FACADE.deletePerson(id);
        return "Personen er slettet!";
    }
     
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Operation(summary = "Add a new person", tags = {"person"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Person added"),
                @ApiResponse(responseCode = "400", description = "Not all arguments provided with the body")
            })
    public String addPerson(String person){
        dto.PersonDTO p = GSON.fromJson(person, dto.PersonDTO.class);
        PersonDTO pNew = FACADE.addPerson(p.getEmail(), p.getFirstname(), p.getLastname(), p.getAddress(), p.getHobbies(), p.getPhone());
        return GSON.toJson(pNew); //return GSON.toJson(new dto.Person(pNew));
    }
    
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Operation(summary = "Edit a person", tags = {"person"},
            responses = {
                @ApiResponse(responseCode = "200", description = "Person edited"),
                @ApiResponse(responseCode = "400", description = "Arguments missing with the body to edit")
            })
    public String updatePerson(@PathParam("id") long id, String person){
        dto.PersonDTO pDTO = GSON.fromJson(person, dto.PersonDTO.class);
        PersonDTO p = new PersonDTO(pDTO.getEmail(), pDTO.getFirstname(), pDTO.getLastname(), pDTO.getAddress(), pDTO.getHobbies(), pDTO.getPhone());
        p.setId(id);
        PersonDTO pNew = FACADE.editPerson(p);
        return GSON.toJson(pNew);
    }


 
}
