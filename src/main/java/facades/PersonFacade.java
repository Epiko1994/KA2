package facades;

import dto.AddressDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import entities.*;

import java.util.ArrayList;
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
    
    public PersonDTO getPersonById(long id){
            EntityManager em = getEntityManager();
        try {
            PersonDTO person = em.find(PersonDTO.class, id);
            return person;
        } finally {
            em.close();
        }
    }

    public Person addPerson(String email, String firstname, String lastname,String address,int zip, List<HobbyDTO> hobbies, List<PhoneDTO> phones) {
        EntityManager em = getEntityManager();
        List<Hobby> hobbyList = new ArrayList<>();
        for (int i = 0; i < hobbies.size(); i++) {
            Hobby tempHobby = new Hobby(hobbies.get(i).getName(), hobbies.get(i).getDescription());
            hobbyList.add(tempHobby);
        }
        List<Phone> phoneList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Phone tempPhone = new Phone(phones.get(i).getNumber(),phones.get(i).getDescritpion());
            phoneList.add(tempPhone);
        }
        CityInfo cI = new CityInfo(zip);
        Address a = new Address(address);
        a.setCityinfo(cI);
 
        Person person = new Person(email, firstname, lastname, a, hobbyList, phoneList);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }
    
    public Person deletePerson(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PersonDTO editPerson(PersonDTO p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Person getPersonByPhone(long phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Person> getPersonByZip(long zip) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<PersonDTO> getHobbyPersons(String hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getHobbyPersonsCount(String hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
