package facades;

import dto.CityInfoDTO;
import dto.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Person;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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
    
    //TODO Remove/Change this before use
    public long getPersonCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM Person p").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<PersonDTO> getPersonsByHobby(String hobby) {
        EntityManager em = getEntityManager();

        List<PersonDTO> personsDTO = new ArrayList();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.hobbies h WHERE h.name = :hobby", Person.class);
        List<Person> persons = query.setParameter("hobby", hobby).getResultList();

        for (Person person : persons) {
            personsDTO.add(new PersonDTO(person));
        }
        return personsDTO;
    }
    
    public List<PersonDTO> getPersonsByZip(int zip) {
        EntityManager em = getEntityManager();

        List<PersonDTO> personsDTO = new ArrayList();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.address a WHERE a.cityInfo.zip = :zip", Person.class);
        List<Person> persons = query.setParameter("zip", zip).getResultList();

        for (Person person : persons) {
            personsDTO.add(new PersonDTO(person));
        }
        return personsDTO;
    }
    
    public PersonDTO getPersonByPhone(String number) {
        EntityManager em = getEntityManager();

        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p JOIN p.phones ph WHERE ph.number = :number", Person.class);
        Person person = query.setParameter("number", number).getSingleResult();

        PersonDTO pDTO = new PersonDTO(person);
        return pDTO;
    }
    
    public long getPersonCountByHobby(String hobby){
        EntityManager em = emf.createEntityManager();
        try{
            long PersonCount = (long)em.createQuery("SELECT COUNT(p) FROM Person p JOIN p.hobbies h WHERE h.name = :hobby")
                    .setParameter("hobby", hobby)
                    .getSingleResult();
            return PersonCount;
        }finally{  
            em.close();
        }   
    }
//    public List<CityInfo> getZipcodes() {
//        EntityManager em = getEntityManager();
//        
//        List<CityInfo> cityInfos = new ArrayList();
//
//        Query query = em.createQuery("SELECT c FROM CityInfo c");
//        List<CityInfo> zipcodes = query.getResultList();
//        for (CityInfo c : cityInfos) {
//            cityInfos.add(new CityInfo(c));
//        }
//        return cityInfos;
//
//    }   
    
    public List<CityInfoDTO> getAllCityInfos() {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<CityInfoDTO> query = 
                em.createQuery("Select c from CityInfo c", CityInfoDTO.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
    
    public Person getPersonById(long id) {
        EntityManager em = getEntityManager();
        try {
            Person person = em.find(Person.class, id);
            return person;
        } finally {
            em.close();
        }
    }

    public Person addPerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        Address a = new Address(p.getAddress(), "additionalInfo");
        a.setCityInfo(new CityInfo(p.getCity()));
        System.out.println(p.getCity());
        Person person = new Person(p.getEmail(),p.getFirstName(), p.getLastName(), a);
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }
    

}
