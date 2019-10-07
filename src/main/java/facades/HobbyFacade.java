package facades;

import dto.HobbyDTO;
import dto.PersonDTO;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class HobbyFacade {

    private static HobbyFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private HobbyFacade() {}


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static HobbyFacade getHobbyFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HobbyFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public long getHobbyCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long HobbyCount = (long)em.createQuery("SELECT COUNT(r) FROM Hobby r").getSingleResult();
            return HobbyCount;
        }finally{
            em.close();
        }

    }

    public HobbyDTO getHobbyById(long id){
        EntityManager em = getEntityManager();
        try {
            HobbyDTO hobby = em.find(HobbyDTO.class, id);
            return hobby;
        } finally {
            em.close();
        }
    }

    public HobbyDTO editHobby(HobbyDTO h) {
        EntityManager em = getEntityManager();
        try {
            HobbyDTO hobby = em.find(HobbyDTO.class, h.getId());
            hobby.setDescription(h.getDescription());
            return hobby;
        } finally {
            em.close();
        }
    }

}
