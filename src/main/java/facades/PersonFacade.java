package facades;

import dto.CityInfoDTO;
import dto.HobbyDTO;
import dto.PersonDTO;
import dto.PhoneDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
    
    public List<PersonDTO> getAllPersons() {
        EntityManager em = getEntityManager();
        TypedQuery<Person> tq = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = tq.getResultList();
        List<PersonDTO> personsDTO = new ArrayList<>();
        em.close();
        for (Person person : persons) {
            personsDTO.add(new PersonDTO(person));
        }
        return personsDTO;
    }

    public List<HobbyDTO> getAllHobbies() {
        EntityManager em = getEntityManager();
        TypedQuery<Hobby> tq = em.createQuery("SELECT h FROM Hobby h", Hobby.class);
        List<Hobby> hobbies = tq.getResultList();
        List<HobbyDTO> hobbiesDTO = new ArrayList<>();
        em.close();
        for (Hobby hobby : hobbies) {
            hobbiesDTO.add(new HobbyDTO(hobby));
        }
        return hobbiesDTO;
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
        System.out.println(Integer.valueOf(p.getCity()));
        
        List<Hobby> hobbyList = new ArrayList();
        for (HobbyDTO h : p.getHobbies()) {
            hobbyList.add(new Hobby(h));
        }
        
        List<Phone> phoneList = new ArrayList<>();
        for (PhoneDTO ph : p.getPhones()) {
            System.out.println("NUMMER: " + ph.getNumber());
            phoneList.add(new Phone(ph));
        }
            
        CityInfo ci = new CityInfo(Integer.valueOf(p.getCity()));
        a.setCityInfo(ci);
        Person person = new Person(p.getEmail(),p.getFirstName(), p.getLastName());
        person.setAddress(a);
//        person.setHobbies(hobbyList);

        for (Phone ph : phoneList) {
            person.addPhone(ph);
        }
        
        for (Hobby h : hobbyList) {
            //Hobby mergedHobby = em.merge(h);
            person.addHobby(h);
        }    

        try {
            em.getTransaction().begin();        
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return person;
    }
    
    public PersonDTO deletePerson(Long id){
        EntityManager em = getEntityManager();
        Person p = em.find(Person.class, id);
        Address a = p.getAddress();
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
        return new PersonDTO(p);
    }
        
        public Person editPerson(PersonDTO p) {
        EntityManager em = getEntityManager();
        Person personDB = em.find(Person.class, p.getId());
        
        personDB.setFirstName(p.getFirstName());
        personDB.setLastName(p.getLastName());
        personDB.setEmail(p.getEmail());
        
        List<Phone> phoneList = new ArrayList<>();
        for (PhoneDTO ph : p.getPhones()) {
            System.out.println("NUMMER: " + ph.getNumber());
            phoneList.add(new Phone(ph, personDB));
        }        
        personDB.setPhones(phoneList);
        
        List<Hobby> hobbyList = new ArrayList();
        for (HobbyDTO h : p.getHobbies()) {
            hobbyList.add(new Hobby(h));
        }
        personDB.setHobbies(hobbyList);
//        Address newAddress = new Address(p.getAddress(),"ddInfo");
//        personDB.setAddress(newAddress);
        
        Address add = findAddress(personDB.getAddress().getStreet(), personDB.getAddress().getCityInfo().getZip());
            System.out.println("HER ER ADD: >>>>>>>>>>" + add);
            System.out.println(add.getId() + " " + add.getStreet() + " " + add.getCityInfo().getZip());

//                Address address = findAddress(p.getAddress(), Integer.parseInt(p.getCity()));
//                address = em.merge(address);
//                personDB.setAddress(address);
        add.setStreet(p.getAddress());
//        CityInfo ci = new CityInfo(Integer.parseInt(p.getCity()));
        add.getCityInfo().setZip(Integer.parseInt(p.getCity()));
        em.merge(add);
        
        try {
            em.getTransaction().begin();
            em.merge(personDB);
            em.getTransaction().commit();
        return personDB;
        } finally {
            em.close();
        }
    }
          
        public Address findAddress(String street, int zip){
            System.out.println(street + " " + zip);
        EntityManager em = getEntityManager();
        Address address = null;
            TypedQuery<Address> tq = em.createQuery("SELECT a FROM Address a WHERE a.cityInfo.zip = :zip AND a.street = :street", Address.class);
            tq.setParameter("zip", zip);
            tq.setParameter("street", street);
        try {
            address = tq.getSingleResult();
        } catch (NonUniqueResultException e){
            return tq.getResultList().get(1);
        } catch (NoResultException e) {
            System.out.println("ADDRESSE IKKE FUNDET");
        }
        return address;
    }
}
