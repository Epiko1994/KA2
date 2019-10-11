package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CityInfoDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import javax.ws.rs.core.Response;

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
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPerson(Person entity, @PathParam("id") long id) {
        Person p = FACADE.getPersonById(id);
        return GSON.toJson(new PersonDTO(p));
    }
    
    @Path("hobby/{hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getPersonsByHobby(Person entity, @PathParam("hobby") String hobby) {
        List<PersonDTO> persons = FACADE.getPersonsByHobby(hobby);
        return persons;
    }
    
    @Path("hobby/count/{hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public long getPersonCountByHobby(Person entity, @PathParam("hobby") String hobby) {
        long personCount = FACADE.getPersonCountByHobby(hobby);
        return personCount;
    }
    
    @Path("zip/{zip}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<PersonDTO> getPersonsByZip(Person entity, @PathParam("zip") int zip) {
        List<PersonDTO> persons = FACADE.getPersonsByZip(zip);
        return persons;
    }
    
    @Path("phone/{number}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public PersonDTO getPersonByPhone(Person entity, @PathParam("number") String number) {
        PersonDTO person = FACADE.getPersonByPhone(number);
        return person;
    }
    
    @Path("count")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRenameMeCount() {
        long count = FACADE.getPersonCount();
        //System.out.println("--------------->"+count);
        return "{\"count\":"+count+"}";  //Done manually so no need for a DTO
    }
    
    @Path("cityinfo/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<CityInfoDTO> getAllZips() {
        List<CityInfoDTO> cityInfoList = FACADE.getAllCityInfos();
        return cityInfoList;
    }  
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        Person pNew = FACADE.addPerson(p);
        return GSON.toJson(new PersonDTO(pNew)); //return GSON.toJson(new dto.Person(pNew));
    }
   
    @DELETE
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id){
        PersonDTO pdto = FACADE.deletePerson(id);
        return Response.ok(GSON.toJson(pdto)).build();
    }
    
    
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") long id, String person){
        PersonDTO p = GSON.fromJson(person, PersonDTO.class);
        p.setId(id);
        Person pNew = FACADE.editPerson(p);
        return GSON.toJson(new PersonDTO(pNew));
    }
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAll() {
        List<PersonDTO> persons = FACADE.getAllPersons();
        return GSON.toJson(persons);
    }
    
    @Path("hobby/all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllHobbies() {
        List<HobbyDTO> hobbies = FACADE.getAllHobbies();
        return GSON.toJson(hobbies);
    }
 
}
