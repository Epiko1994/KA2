package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Hobby;
import entities.Person;
import utils.EMF_Creator;
import facades.PersonFacade;
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

//Todo Remove or change relevant parts before ACTUAL use
@Path("person")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)   
    public Person getPerson(@PathParam("id") int id) {
        Person p = FACADE.getPersonById(id);
        return p;
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
     public String deletePerson(@PathParam("id") long id){
        Person pDeleted = FACADE.deletePerson(id);
        return "Personen er slettet!";
    }
     
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addPerson(String person){
        dto.Person p = GSON.fromJson(person, dto.Person.class);
        Person pNew = FACADE.addPerson(p.getEmail(), p.getFirstname(), p.getLastname(), p.getAddress(), p.getHobbies(), p.getPhone());
        return GSON.toJson(pNew); //return GSON.toJson(new dto.Person(pNew));
    }
    
    @PUT
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updatePerson(@PathParam("id") long id, String person){
        dto.Person pDTO = GSON.fromJson(person, dto.Person.class);
        Person p = new Person(pDTO.getEmail(), pDTO.getFirstname(), pDTO.getLastname(), pDTO.getAddress(), pDTO.getHobbies(), pDTO.getPhone());
        p.setId(id);
        Person pNew = FACADE.editPerson(p);
        return GSON.toJson(pNew);
    }


 
}
