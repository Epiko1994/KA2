
package rest;

import entities.Phone;
import facades.PhoneFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;
    
@Path("phone")
public class PhoneResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/ka2",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final PhoneFacade FACADE =  PhoneFacade.getFacadeExample(EMF);

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)   
    public Phone getPhone(@PathParam("id") long id) {
        Phone p = FACADE.getPhoneById(id);
        return p;
    }
    
    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
     public String deletePhoneNumber(@PathParam("id") long id){
        Phone phoneDeleted = FACADE.deletePhoneNumber(id);
        return "The Phone-number is deleted!";
    }

 
}