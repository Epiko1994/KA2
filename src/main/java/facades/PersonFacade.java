package facades;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long personCount = (long)em.createQuery("SELECT COUNT(r) FROM Person r").getSingleResult();
            return personCount;
        }finally{  
            em.close();
        }
        
    }
    
    public Person getPersonById(long id){
            EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, id);
            return person;
        } finally {
            em.close();
        }
    }

    public Person deletePerson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person addPerson(String email, String firstname, String lastname,Address address, List<Hobby> hobbies, List<Phone> phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person editPerson(Person p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
